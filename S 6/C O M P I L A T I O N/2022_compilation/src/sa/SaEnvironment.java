package sa;
import java.util.*;
import ts.*;

public class SaEnvironment {
    private int[] vars;
    private int[] args;
    private int returnValue;

    public SaEnvironment (TsItemFct fct)
    {
	SaLExp lArgs = null;
	Ts localTable = fct.getTable();
	int i = 0;

       	args = new int[localTable.nbArg()];
	vars = new int[localTable.nbVar()];
	returnValue = 0;
	//	System.out.println("allocation d'un nouvel environnement");
	//	System.out.println("nb var = " + localTable.nbVar());
	//	System.out.println("nb arg = " + localTable.nbArg());
	
    }

    public int getVar(int adr){return vars[adr/4];}
    public void setVar(int adr, int val){vars[adr/4] = val;}
    
    public int getArg(int adr){return args[adr/4];}
    public void setArg(int adr, int val){args[adr/4] = val;}
    
    public int getReturnValue(){return returnValue;}
    public void setReturnValue(int val){returnValue = val;}
}
