import ts.*;
import sa.*;

import javax.swing.text.DefaultCaret;

import c3a.*;

public class Sa2c3a extends SaDepthFirstVisitor <C3aOperand> {
    
	private C3a c3a;
    int indentation;
    private Ts tableGlobale;
	private Ts TableLocal;

	// Constructeur de la classe Sa2c3a.
	public Sa2c3a(SaNode root, Ts tableGlobale){
		
		c3a = new C3a();
		// table globale 
		this.tableGlobale = tableGlobale;
		C3aTemp result = c3a.newTemp();
		C3aFunction fct = new C3aFunction(tableGlobale.getFct("main"));
		c3a.ajouteInst(new C3aInstCall(fct, result, ""));
		c3a.ajouteInst(new C3aInstStop(result, ""));
		indentation = 0;
		// Le parcours de l'instructions.
		root.accept(this);
		}
	

	public C3a getC3a(){return this.c3a;}
    
    
	// les 2 methode de cours.
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

	// Les expression

	// Methode de cours sur SaExpAdd.
	// L'addition.
	
	public C3aOperand visit(SaExpAdd node) {
		defaultIn(node);
		C3aOperand op1 = node.getOp1().accept(this);
		C3aOperand op2 = node.getOp2().accept(this);
		C3aOperand result = c3a.newTemp();

		c3a.ajouteInst(new c3a.C3aInstAdd(op1, op2, result,""));
		defaultOut(node);
		return result;
	}


	// La soustraction.

	public C3aOperand visit(SaExpSub node) {
		defaultIn(node);
		C3aOperand op1 = node.getOp1().accept(this);
		C3aOperand op2 = node.getOp2().accept(this);
		C3aOperand result = c3a.newTemp();

		c3a.ajouteInst(new c3a.C3aInstSub(op1,op2, result,""));
		defaultOut(node);
		return result;
	}
    
	// La multiplication.
	public C3aOperand visit(SaExpMult node) {
		defaultIn(node);
		C3aOperand op1 = node.getOp1().accept(this);
		C3aOperand op2 = node.getOp2().accept(this);
		C3aOperand result = c3a.newTemp();

		c3a.ajouteInst(new c3a.C3aInstMult(op1,op2, result,""));
		defaultOut(node);
		return result;
	} 

	// La Division.

	public C3aOperand visit(SaExpDiv node) {
		defaultIn(node);
		C3aOperand op1 = node.getOp1().accept(this);
		C3aOperand op2 = node.getOp2().accept(this);
		C3aOperand result = c3a.newTemp();

		c3a.ajouteInst(new c3a.C3aInstDiv(op1,op2, result,""));
		defaultOut(node);
		return result;
	} 


    
}
