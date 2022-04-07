package c3a;

import c3a.*;
import ts.Ts;
import ts.TsParser;
import ts.TsItemVar;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Stack;


public class C3aParser {
    private enum OperandPosition{LEFT, RIGHT};

    private Ts symbolTable;
    private C3a instructions;
    private String[] lineArgs;
    private C3aInst currentInstr;
    private HashMap<String, C3aLabel> labelNameToLabel;
    private HashMap<String, C3aTemp> tempNameToTemp;
    private String currentFctName;

    public C3a parse(String c3aFilePath, Ts symbolTable){
        this.symbolTable = symbolTable;
        instructions = new C3a();
        labelNameToLabel = new HashMap<>();
        tempNameToTemp = new HashMap<>();
        currentFctName ="";
        try{
            Files.lines(Paths.get(c3aFilePath)).forEachOrdered(this::processLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return instructions;
    }

    private void processLine(String line){
        lineArgs = line.split("([ ]|[\t])+");
        currentInstr = null;
        addLabelToNextInstr(line);
        if(line.contains("call"))
            parseCallInstr();
        else if(line.contains("stop"))
            parseStopInstr();
        else if(line.contains("fbegin"))
            parseFbeginInstr();
        else if(line.contains("=") && !line.contains("if"))
            parseEqualInstr();
        else if(line.contains("write"))
            parseWriteInstr();
        else if(line.contains("fend"))
            parseFendInstr();
        else if(line.contains("if"))
            parseIfInstr();
        else if(line.contains("goto"))
            parseGotoInstr();
       else if(line.contains("ret"))
           parseRetInstr();
       else if(line.contains("param"))
           parseParamInstr();
        instructions.ajouteInst(currentInstr);
    }

    private void addLabelToNextInstr(String line){
        if(!line.contains("fbegin") && !lineArgs[0].equals("")){
            var labelName = lineArgs[0];
            var label = labelNameToLabel.get(labelName);
            if(label == null) {
                label = instructions.newAutoLabel();
                labelNameToLabel.put(lineArgs[0], label);
            }
            instructions.addLabelToNextInst(label);
        }
    }

    private void parseCallInstr(){
        var itemFct = symbolTable.getFct(lineArgs[4]);
        var res = chooseRightOperandType(lineArgs[1], OperandPosition.LEFT);
        currentInstr = new C3aInstCall(new C3aFunction(itemFct), res, " ");
    }

    private void parseStopInstr(){
        var numTemp = Integer.parseInt(lineArgs[2].replace("t", ""));
        currentInstr = new C3aInstStop(new C3aTemp(numTemp), "");
    }

    private void parseFbeginInstr(){
        currentFctName = lineArgs[0];
        var itemFct = symbolTable.getFct(lineArgs[0]);
        currentInstr = new C3aInstFBegin(itemFct, "");
    }

    private void parseEqualInstr() {
        var res = chooseRightOperandType(lineArgs[1], OperandPosition.LEFT);
        var op1 = chooseRightOperandType(lineArgs[3], OperandPosition.RIGHT);
        if (haveInnerMathInstr()) {
            var op2 = chooseRightOperandType(lineArgs[5], OperandPosition.RIGHT);
            switch (lineArgs[4]) {
	    case "+" : {currentInstr = new C3aInstAdd(op1, op2, res, ""); break;}
	    case "-" : {currentInstr = new C3aInstSub(op1, op2, res, ""); break;}
	    case "*" : {currentInstr = new C3aInstMult(op1, op2, res, ""); break;}
	    case "/" : {currentInstr = new C3aInstDiv(op1, op2, res, ""); break;}
            }
        }
        else
            currentInstr = new C3aInstAffect(op1, res, "");
    }

    private boolean haveInnerMathInstr(){
        return lineArgs.length == 6;
    }

    private C3aOperand chooseRightOperandType(String op, OperandPosition pos){
        try{
            return getConstantOp(op);
        } catch(NumberFormatException e) {
            if(isTempOp(op))
                return getTempOp(op, pos);
            else
                return getVarOp(op);
        }
    }

    private C3aConstant getConstantOp(String op) throws NumberFormatException{
        var ctValue = Integer.parseInt(op);
        return new C3aConstant(ctValue);
    }

    private boolean isTempOp(String op){
        return  op.contains("t") && !(op.contains("[") && op.contains("]"));
    }

    private C3aTemp getTempOp(String op, OperandPosition pos){
        if(pos == OperandPosition.RIGHT)
            return tempNameToTemp.get(op);
        else {
            var temp = tempNameToTemp.get(op);
            if(temp == null) {
                temp = instructions.newTemp();
                tempNameToTemp.put(op, temp);
            }
            return temp;
        }
    }

    private C3aVar getVarOp(String op){
        if(isArray(op)){
            var realName = op.split("\\[")[0];
            var startIndex = op.indexOf('[') + 1;
            var endIndex = op.lastIndexOf(']');
            var indexStr = op.substring(startIndex, endIndex);
            var index = chooseRightOperandType(indexStr, OperandPosition.RIGHT);
            var varItem = symbolTable.getVar(realName);
            return new C3aVar(varItem, index);
        }
        else {
            var varItem = symbolTable.getTableLocale(currentFctName).getVar(op);
            if (varItem == null)
                varItem = symbolTable.getVar(op);
            return new C3aVar(varItem ,null);
        }
    }

    private boolean isArray(String op){
        return op.contains("[") && op.contains("]");
    }



    private void parseWriteInstr(){
        var op = chooseRightOperandType(lineArgs[2], OperandPosition.RIGHT);
        currentInstr = new C3aInstWrite(op, "");
    }

    private void parseFendInstr(){
        currentInstr = new C3aInstFEnd(" ");
    }

    private void parseIfInstr(){
        var label = labelNameToLabel.get(lineArgs[6]);
        if(label == null) {
            label = instructions.newAutoLabel();
            labelNameToLabel.put(lineArgs[6], label);
        }

        var op1 = chooseRightOperandType(lineArgs[2], OperandPosition.RIGHT);
        var op2 = chooseRightOperandType(lineArgs[4], OperandPosition.RIGHT);
	if(lineArgs[3].equals("=")){
	    currentInstr = new C3aInstJumpIfEqual(op1, op2, label, "");
	}
	else if(lineArgs[3].equals("<")){
	    currentInstr = new C3aInstJumpIfLess(op1, op2, label, "");
	}
	else if(lineArgs[3].equals("!=")){
	    currentInstr = new C3aInstJumpIfNotEqual(op1, op2, label, "");
	}
	else{
	    throw new IllegalStateException("Unexpected value: " + lineArgs[3]);
	}
	
	/*	
         switch (lineArgs[3]) {
            case "=" : currentInstr = new C3aInstJumpIfEqual(op1, op2, label, "");
            case "<" : currentInstr = new C3aInstJumpIfLess(op1, op2, label, "");
            case "!=" : currentInstr = new C3aInstJumpIfNotEqual(op1, op2, label, "");
            default : throw new IllegalStateException("Unexpected value: " + lineArgs[3]);
	    }*/
    }

    private void parseGotoInstr(){
        var label = labelNameToLabel.get(lineArgs[2]);
        if(label == null) {
            label = instructions.newAutoLabel();
            labelNameToLabel.put(lineArgs[2], label);
        }
        labelNameToLabel.put(lineArgs[2], label);
        currentInstr = new C3aInstJump(label, "");
    }

    private void parseRetInstr(){
        var op = chooseRightOperandType(lineArgs[2], OperandPosition.RIGHT);
        currentInstr = new C3aInstReturn(op, "");
    }

    private void parseParamInstr(){
        var op = chooseRightOperandType(lineArgs[2], OperandPosition.RIGHT);
        currentInstr = new C3aInstParam(op, "");
    }

    public static void main(String[] args){
        var parser = new C3aParser();
        var stParser = new TsParser();
        var st = stParser.parse("tabl.ts");
        var c3a = parser.parse("tabl.c3a", st);
        System.out.println("Info C3A :");
        System.out.println("Temp counter :" + c3a.getTempCounter());
        System.out.println("Code :");
        for(var instr : c3a.listeInst){
            System.out.println("-----------------");
            System.out.println(instr);
        }
    }
}
