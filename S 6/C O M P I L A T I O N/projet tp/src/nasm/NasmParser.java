package nasm;

import ts.Ts;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class NasmParser{

    private Nasm code;
    private String[] linesArgs;
    private NasmInst currentInstr;
    private NasmInst complementaryInstr;
    private NasmLabel labelToNextInstr;
    private int IAL = 0; // Indice after label;
    private HashMap<String, NasmRegister> nameToReg;


    /*    (Map.of(
            "eax", new NasmRegister(Nasm.REG_EAX),
            "ebx", new NasmRegister(Nasm.REG_EBX),
            "ecx", new NasmRegister(Nasm.REG_ECX),
            "edx", new NasmRegister(Nasm.REG_EDX),
            "esp", new NasmRegister(Nasm.REG_ESP),
            "ebp", new NasmRegister(Nasm.REG_EBP),
            "unk", new NasmRegister(Nasm.REG_UNK)
	    ));*/

    public NasmParser(){
	code = new Nasm();

	nameToReg = new HashMap<String, NasmRegister>();
	NasmRegister reg_eax = new NasmRegister(-1);
	reg_eax.colorRegister(Nasm.REG_EAX);
	nameToReg.put("eax", reg_eax);
	
	NasmRegister reg_ebx = new NasmRegister(-1);
	reg_ebx.colorRegister(Nasm.REG_EBX);
	nameToReg.put("ebx", reg_ebx);
	
	NasmRegister reg_ecx = new NasmRegister(-1);
	reg_ecx.colorRegister(Nasm.REG_ECX);
	nameToReg.put("ecx", reg_ecx);
	
	NasmRegister reg_edx = new NasmRegister(-1);
	reg_edx.colorRegister(Nasm.REG_EDX);
	nameToReg.put("edx", reg_edx);
	
	NasmRegister reg_esp = new NasmRegister(-1);
	reg_esp.colorRegister(Nasm.REG_ESP);
	nameToReg.put("esp", reg_esp);
	
	NasmRegister reg_ebp = new NasmRegister(-1);
	reg_ebp.colorRegister(Nasm.REG_EBP);
	nameToReg.put("ebp", reg_ebp);
	
    }
    
    public Nasm parse(String filename) throws IOException {
        try(var linesStream = Files.lines(Paths.get(filename))) {
            linesStream.forEachOrdered(this::processLine);
        }
        return code;
    }

    private void processLine(String line){
	//	System.out.println(line);
	if(line.isEmpty()){return;}
	if(line.startsWith("%include")) return;
	if(line.startsWith("section")) return;
	if(line.startsWith("global")) return;
	if(line.startsWith("_start")) return;

        currentInstr = null;
        complementaryInstr = null;
        line = line.replace("dword", "");
        linesArgs = line.split("(,*([ ]|[\t]))+");
        labelToNextInstr = null;
        IAL = 0;
        registerLabel();
        if(line.contains("call"))
	    //            currentInstr = new NasmCall(labelToNextInstr, new NasmAddress(new NasmLabel(linesArgs[IAL + 2])), "");
            currentInstr = new NasmCall(labelToNextInstr, new NasmLabel(linesArgs[IAL + 2]), "");
        else if(line.contains("mov")){
            var leftOp = getRightOperandType(linesArgs[IAL + 2]);
            var rightOp = getRightOperandType(linesArgs[IAL + 3]);
            currentInstr = new NasmMov(labelToNextInstr, leftOp, rightOp, "");
        }
        else if(line.contains("push")){
            var rightOp = getRightOperandType(linesArgs[IAL + 2]);
            currentInstr = new NasmPush(labelToNextInstr, rightOp, "");
        }
        else if(line.contains("pop")){
            var rightOp = getRightOperandType(linesArgs[IAL + 2]);
            currentInstr = new NasmPop(labelToNextInstr, rightOp, "");
        }
        else if(line.contains("add")){
            var leftOp = getRightOperandType(linesArgs[IAL + 2]);
            var rightOp = getRightOperandType(linesArgs[IAL + 3]);
            currentInstr = new NasmAdd(labelToNextInstr, leftOp, rightOp, "");
        }
        else if(line.contains("sub")){
            var leftOp = getRightOperandType(linesArgs[IAL + 2]);
            var rightOp = getRightOperandType(linesArgs[IAL + 3]);
            currentInstr = new NasmSub(labelToNextInstr, leftOp, rightOp, "");
        }
        else if(line.contains("imul")) {
            var leftOp = getRightOperandType(linesArgs[IAL + 2]);
            var rightOp = getRightOperandType(linesArgs[IAL + 3]);
            currentInstr = new NasmMul(labelToNextInstr, leftOp, rightOp, "");
        }
        else if(line.contains("idiv")){
            var rightOp = getRightOperandType(linesArgs[IAL + 2]);
            currentInstr = new NasmDiv(labelToNextInstr, rightOp, "");
        }
        else if(line.contains("and")){
            var leftOp = getRightOperandType(linesArgs[IAL + 2]);
            var rightOp = getRightOperandType(linesArgs[IAL + 3]);
            currentInstr = new NasmAnd(labelToNextInstr, leftOp, rightOp, "");
        }
        else if(linesArgs[IAL + 1].contains("or")){
            var leftOp = getRightOperandType(linesArgs[IAL + 2]);
            var rightOp = getRightOperandType(linesArgs[IAL + 3]);
            currentInstr = new NasmOr(labelToNextInstr, leftOp, rightOp, "");
        }
        else if(line.contains("xor")){
            var leftOp = getRightOperandType(linesArgs[IAL + 2]);
            var rightOp = getRightOperandType(linesArgs[IAL + 3]);
            currentInstr = new NasmXor(labelToNextInstr, leftOp, rightOp, "");
        }
        else if(line.contains("jmp")){
            var address = getRightOperandType(linesArgs[IAL + 2]);
            currentInstr = new NasmJmp(labelToNextInstr, address, "");
        }
        else if(line.contains("je")){
            var address = getRightOperandType(linesArgs[IAL + 2]);
            currentInstr = new NasmJe(labelToNextInstr, address, "");
        }
        else if(line.contains("jne")){
            var address = getRightOperandType(linesArgs[IAL + 2]);
            currentInstr = new NasmJne(labelToNextInstr, address, "");
        }
        else if(line.contains("jg")){
            var address = getRightOperandType(linesArgs[IAL + 2]);
            currentInstr = new NasmJg(labelToNextInstr, address, "");
        }
        else if(line.contains("jge")){
            var address = getRightOperandType(linesArgs[IAL + 2]);
            currentInstr = new NasmJge(labelToNextInstr, address, "");
        }
        else if(line.contains("jl")){
            var address = getRightOperandType(linesArgs[IAL + 2]);
            currentInstr = new NasmJl(labelToNextInstr, address, "");
        }
        else if(line.contains("jle")){
            var address = getRightOperandType(linesArgs[IAL + 2]);
            currentInstr = new NasmJle(labelToNextInstr, address, "");
        }
        else if(line.contains("cmp")){
            var leftOp = getRightOperandType(linesArgs[IAL + 2]);
            var rightOp = getRightOperandType(linesArgs[IAL + 3]);
            currentInstr = new NasmCmp(labelToNextInstr, leftOp, rightOp, "");
        }
        else if(line.contains("ret"))
            currentInstr = new NasmRet(labelToNextInstr, "");

        else if(line.contains("int 0x80"))
            currentInstr = new NasmInt(labelToNextInstr, "");

	else if(line.contains("resd")){
	    int nb = Integer.parseInt(linesArgs[IAL + 2]);
	    code.ajoutePseudoInst(new NasmResd(labelToNextInstr, nb, ""));
	}
	else if(line.contains("resb")){
	    int nb = Integer.parseInt(linesArgs[IAL + 2]);
	    code.ajoutePseudoInst(new NasmResb(labelToNextInstr, nb, ""));
	}
	/*        else
            currentInstr = new NasmEmpty(labelToNextInstr, "");
	*/
        if(complementaryInstr != null)
            code.ajouteInst(complementaryInstr);
        if(currentInstr != null)
	    code.ajouteInst(currentInstr);
    }

    private void registerLabel(){
        if(!linesArgs[0].isEmpty()) {
            labelToNextInstr = new NasmLabel(linesArgs[0]);
            IAL = 1;
        }
    }

    public NasmOperand getRightOperandType(String opName){
        if(isMemoryAccessOperand(opName)) {
           return getRightMemoryOperand(opName);
        }
        else if (isRegister(opName))
            return getNasmRegister(opName);
        else if(isNumber(opName)){
            var numValue = Integer.parseInt(opName);
            return new NasmConstant(numValue);
        }
        else
            return new NasmLabel(opName);
    }

    private boolean isMemoryAccessOperand(String opName){
        return opName.contains("[") && opName.contains("]");
    }

    private NasmOperand getRightMemoryOperand(String opName){
        var simplifiedName = opName.replace("[", "").replace("]", "");
        if (isRelativeAddressing(simplifiedName)) {
            var direction = '+';
            if (simplifiedName.contains("-"))
                direction = '-';
	    // on part d'une principe qu'un adresse relative est  de la forme 'base {+| -} offset'
	    // offset est une constante numérique ou un registre
	    // base est ...
            var baseName = simplifiedName.split("\\" + direction)[0];
            var offset = simplifiedName.split("\\" + direction)[1];
            var baseOp = getRightOperandType(baseName);
            var offsetOp = (NasmOperand)null;
            if(isNumber(offset))
                offsetOp = new NasmConstant(Integer.parseInt(offset));
            else {
                offsetOp = getNasmRegister(offset);
            }
            return new NasmAddress(baseOp, direction, offsetOp);
        }
        else if (isRegister(simplifiedName))
            return new NasmAddress(getNasmRegister(simplifiedName));
        else
        {
            try {
		var address = Integer.parseInt(simplifiedName);
		return new NasmAddress(new NasmConstant(address));
	    } catch(NumberFormatException e){
		return new NasmAddress(new NasmLabel(simplifiedName));
	    }
        }
    }


    private boolean isRelativeAddressing(String simplifiedOpName){
        return simplifiedOpName.contains("-") || simplifiedOpName.contains("+");
    }

    private boolean isNumber(String text){
        try{
            var res = Integer.parseInt(text);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }

    private NasmRegister getNasmRegister(String registerName){
        if(nameToReg.containsKey(registerName))
	    return nameToReg.get(registerName);

	StringBuilder sb = new StringBuilder(registerName);
        sb.deleteCharAt(0);
	int registerNb =  Integer.parseInt(sb.toString());
	NasmRegister reg = new NasmRegister(registerNb);
	code.tempCounter++;
	/*	    if(registerName.equals("eax")) reg.colorRegister(Nasm.REG_EAX);
	    else if(registerName.equals("ebx")) reg.colorRegister(Nasm.REG_EBX);
	    else if(registerName.equals("ecx")) reg.colorRegister(Nasm.REG_ECX);
	    else if(registerName.equals("edx")) reg.colorRegister(Nasm.REG_EDX);
	    else if(registerName.equals("esp")) reg.colorRegister(Nasm.REG_ESP);
	    else if(registerName.equals("ebp")) reg.colorRegister(Nasm.REG_EBP);*/
	    
	nameToReg.put(registerName, reg);
	return reg;
    }

    private boolean isRegister(String opName){
        return (nameToReg.containsKey(opName) || opName.startsWith("r"));
    }

    /*    public static  void main(String[] args){
        var parser = new NasmParser();
        try {
            var stParser = new ts.TsParser();
            var res = parser.parse("fibo.pre-nasm", stParser.parse("fibo.ts"));
            for(var instr : res.sectionText){
                System.out.println(instr);
            }
        }catch(IOException e){
            e.printStackTrace();
        }

       // System.out.println(fdf);
       }*/

}
