package c3a;
import java.util.*;
import ts.*;
import sa.*;

public class Sa2c3a extends SaDepthFirstVisitor <C3aOperand> {
    private C3a c3a;
    int indentation;
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
    
    
}
