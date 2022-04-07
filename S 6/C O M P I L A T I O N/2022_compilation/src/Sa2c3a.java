
/**
 * L’objectif de ce TP est de générer du code trois adresses à partir d’un arbre abstrait. Pour chaque
    nœud visité une ou plusieurs instructions trois adresses sont produites.
 *
 */

import c3a.*;
import sa.*;
import ts.Ts;


public class Sa2c3a extends SaDepthFirstVisitor <C3aOperand> {

    private C3a c3a;

    private int indentation;
    public C3a getC3a(){return this.c3a;}
    
    public Sa2c3a(SaNode root, Ts tableGlobale){
        c3a = new C3a();
        C3aTemp result = c3a.newTemp();
        C3aFunction fct = new C3aFunction(tableGlobale.getFct("main"));
        c3a.ajouteInst(new C3aInstCall(fct, result, ""));
        c3a.ajouteInst(new C3aInstStop(result, ""));
        indentation = 0;
        root.accept(this);
        }
    
        public void defaultIn(SaNode node)
        {
        for(int i = 0; i < indentation; i++){System.out.print(" ");}
        indentation++;
        System.out.println("<" + node.getClass().getSimpleName() + ">");
        }
    
        public void defaultOut(SaNode node)
        {
        indentation--;
            for(int i = 0; i < indentation; i++){System.out.print(" ");}
            System.out.println("</" + node.getClass().getSimpleName() + ">");
        }
    

    public C3aOperand visit(SaProg node) {

	    if (node.getVariables() != null) node.getVariables().accept(this);
        node.getFonctions().accept(this);

        return null;
    }

    public C3aOperand visit(SaDecTab node) {
	    return null;
    }

    public C3aOperand visit(SaDecFonc node){

        C3aInstFBegin star = new C3aInstFBegin(node.tsItem,"entree fonction");
        c3a.ajouteInst(star);

        if(node.getCorps() != null)
            node.getCorps().accept(this);
 
        C3aInstFEnd end = new C3aInstFEnd("");
        
        c3a.ajouteInst(end);
        
        return null;
    }

    public C3aOperand visit(SaExpInt node) {

	    return new C3aConstant(node.getVal());
    }

    public C3aOperand visit(SaExpVar node) {

	    return node.getVar().accept(this);
    }

    public C3aOperand visit(SaExpAppel node) {
        
	    return node.getVal().accept(this);
    }

    public C3aOperand visit(SaExpAdd node) {

	    C3aOperand operand1 = node.getOp1().accept(this);
	    C3aOperand operand = node.getOp2().accept(this);
	    C3aOperand res = c3a.newTemp();

	    c3a.ajouteInst(new C3aInstAdd(operand1, operand, res, ""));
	    
        return res;
    }

    public C3aOperand visit(SaExpSub node) {
        
        C3aOperand operand1 = node.getOp1().accept(this);
        C3aOperand operand = node.getOp2().accept(this);
        C3aOperand temp = c3a.newTemp();

        c3a.ajouteInst(new C3aInstSub(operand1, operand, temp, ""));
      
        return temp;
    }
    
    public C3aOperand visit(SaExpMult node) {
      
        C3aOperand operand1 = node.getOp1().accept(this);
        C3aOperand operand = node.getOp2().accept(this);
        C3aOperand temp = c3a.newTemp();

        c3a.ajouteInst(new C3aInstMult(operand1, operand, temp, ""));
      
        return temp;
    }
    
    public C3aOperand visit(SaExpDiv node) {
        
        C3aOperand operand1 = node.getOp1().accept(this);
        C3aOperand operand = node.getOp2().accept(this);
        C3aOperand temp = c3a.newTemp();

        c3a.ajouteInst(new C3aInstDiv(operand1, operand, temp, ""));
        
        return temp;
    }

    public C3aOperand visit(SaExpInf node) {
        
        C3aLabel label = c3a.newAutoLabel();
        C3aOperand temp = c3a.newTemp();

        C3aOperand operand1 = node.getOp1().accept(this);
        C3aOperand operand = node.getOp2().accept(this);

        c3a.ajouteInst(new C3aInstAffect(c3a.True,temp,""));
        c3a.ajouteInst(new C3aInstJumpIfLess(operand1, operand, label, ""));
        c3a.ajouteInst(new C3aInstAffect(c3a.False,temp,""));
        c3a.addLabelToNextInst(label);

        return temp;
    }

    public C3aOperand visit(SaExpEqual node) {

        C3aLabel labelR = c3a.newAutoLabel();
        C3aOperand temp = c3a.newTemp();

        C3aOperand operand1 = node.getOp1().accept(this);
        C3aOperand operand = node.getOp2().accept(this);

        c3a.ajouteInst(new C3aInstAffect(c3a.True,temp,""));
        c3a.ajouteInst(new C3aInstJumpIfEqual(operand1, operand, labelR, ""));
        c3a.ajouteInst(new C3aInstAffect(c3a.False,temp,""));
        c3a.addLabelToNextInst(labelR);

        return temp;
    }

    public C3aOperand visit(SaExpAnd node) {
        
        C3aLabel labelR = c3a.newAutoLabel();
        C3aLabel labelF = c3a.newAutoLabel();

        C3aOperand temp = c3a.newTemp();

        C3aOperand operand1 = node.getOp1().accept(this);
        C3aOperand operand = node.getOp2().accept(this);

        c3a.ajouteInst(new C3aInstJumpIfEqual(operand1, c3a.False, labelF, ""));
        c3a.ajouteInst(new C3aInstJumpIfEqual(operand, c3a.False, labelF, ""));

        c3a.ajouteInst(new C3aInstAffect(c3a.True,temp,""));
        c3a.ajouteInst(new C3aInstJump(labelR,""));
        
        c3a.addLabelToNextInst(labelF);
        
        c3a.ajouteInst(new C3aInstAffect(c3a.False,temp,""));
        c3a.addLabelToNextInst(labelR);

        return temp;
    }

    public C3aOperand visit(SaExpOptTer node) {
        C3aOperand labelR = c3a.newAutoLabel();
        C3aOperand labelT = c3a.newAutoLabel();

        C3aOperand temp = c3a.newTemp();
        
        C3aOperand test = node.getTest().accept(this);
        C3aOperand oui = node.getOui().accept(this);
        C3aOperand non = node.getNon().accept(this);

        c3a.ajouteInst(new C3aInstJumpIfNotEqual(test, c3a.False, labelT, ""));
        c3a.ajouteInst(new C3aInstJumpIfNotEqual(oui, c3a.False, labelT, ""));
        //c3a.ajouteInst(new C3aInstJumpIfNotEqual(non, c3a.False resul, ""));

        return temp;
    }

    public C3aOperand visit(SaExpOr node) {

        C3aLabel labelR = c3a.newAutoLabel();
        C3aLabel labelT = c3a.newAutoLabel();

        C3aOperand temp = c3a.newTemp();

        C3aOperand operand1 = node.getOp1().accept(this);
        C3aOperand operand = node.getOp2().accept(this);

        c3a.ajouteInst(new C3aInstJumpIfNotEqual(operand1, c3a.False, labelT, ""));
        c3a.ajouteInst(new C3aInstJumpIfNotEqual(operand, c3a.False, labelT, ""));

        c3a.ajouteInst(new C3aInstAffect(c3a.False,temp,""));
        c3a.ajouteInst(new C3aInstJump(labelR,""));
        
        c3a.addLabelToNextInst(labelT);
        
        c3a.ajouteInst(new C3aInstAffect(c3a.True,temp,""));
        
        c3a.addLabelToNextInst(labelR);

        return temp;
    }

    public C3aOperand visit(SaExpNot node) {

        C3aLabel labelR = c3a.newAutoLabel();

        C3aOperand temp = c3a.newTemp();
        C3aOperand operand1 = node.getOp1().accept(this);

        c3a.ajouteInst(new C3aInstAffect(c3a.True,temp,""));
        c3a.ajouteInst(new C3aInstJumpIfEqual(operand1, c3a.False, labelR, ""));
        c3a.ajouteInst(new C3aInstAffect(c3a.False,temp,""));
        c3a.addLabelToNextInst(labelR);

        return temp;
    }

    public C3aOperand visit(SaExpLire node) {
    
        C3aOperand temp = c3a.newTemp();
        c3a.ajouteInst(new C3aInstRead(temp,""));
        
        return temp;
    }

    public C3aOperand visit(SaInstRetour node) {
	
        c3a.ajouteInst(new C3aInstReturn(node.getVal().accept(this),""));
	    c3a.ajouteInst(new C3aInstFEnd(""));
	    
        return null;
    }	


    public C3aOperand visit(SaInstEcriture node) {

        c3a.ajouteInst(new C3aInstWrite(node.getArg().accept(this),""));
        return null;
    }

    public C3aOperand visit(SaInstTantQue node) {
        
        C3aLabel labelTest = c3a.newAutoLabel();
        C3aLabel labFin = c3a.newAutoLabel();

        c3a.addLabelToNextInst(labelTest);
        C3aOperand test = node.getTest().accept(this);
        c3a.ajouteInst(new C3aInstJumpIfEqual(test,c3a.False,labFin,""));
        
        node.getFaire().accept(this);
        
        c3a.ajouteInst(new C3aInstJump(labelTest,""));
        c3a.addLabelToNextInst(labFin);

        return null;
    }

    public C3aOperand visit(SaInstAffect node) {
       
        c3a.ajouteInst(new C3aInstAffect(
            node.getRhs().accept(this),
            node.getLhs().accept(this),""));
        return null;
    }


    public C3aOperand visit(SaVarSimple node) {
	   
        return new C3aVar(node.tsItem,null);
    }


    public C3aOperand visit(SaAppel node) {

        if (node.getArguments() != null) {
        
            SaLExp arg = node.getArguments();
        
            while (arg != null) {
                C3aOperand param = arg.getTete().accept(this);
                c3a.ajouteInst(new C3aInstParam(param, ""));
                arg = arg.getQueue();
            }
        }
        return new C3aFunction(node.tsItem);
    }


    public C3aOperand visit(SaInstSi node) {

        C3aOperand test = node.getTest().accept(this);

        C3aLabel labSinon = null;
        if (node.getSinon() != null) labSinon = c3a.newAutoLabel();
        C3aLabel labFin = c3a.newAutoLabel();

        C3aLabel labJump = (node.getSinon() != null) ? labSinon : labFin;
        c3a.ajouteInst(new C3aInstJumpIfEqual(test,c3a.False,labJump,""));
        node.getAlors().accept(this);

        if (node.getSinon() != null) {
            c3a.ajouteInst(new C3aInstJump(labFin,""));
            c3a.addLabelToNextInst(labSinon);
            node.getSinon().accept(this);
        }
        c3a.addLabelToNextInst(labFin);

        return null;
    }

    public C3aOperand visit(SaLExp node) {

	    SaLExp saLExp = node;

        while (saLExp != null) {
            C3aOperand arg = saLExp.getTete().accept(this);
            c3a.ajouteInst(new C3aInstParam(arg,""));
            saLExp = saLExp.getQueue();
        }

        return null;
    }

    public C3aOperand visit(SaVarIndicee node) {

	    return new C3aVar(node.tsItem,node.getIndice().accept(this));
    }
    
}