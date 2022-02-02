import sc.parser.*;
import sc.lexer.*;
import sc.node.*;
import java.io.*;
//import sa.*;
//import ts.*;
//import c3a.*;
//import nasm.*;
//import fg.*;
//import ig.*;


public class Compiler
{
    public static void main(String[] args)
    {
     PushbackReader br = null;
     String baseName = null;
     String inputFileName = null;
     int verboseLevel = 0;
	
	for (int i = 0; i < args.length; i++) {
	    if(args[i].equals("-v")){
		verboseLevel = Integer.parseInt(args[++i]);
	    }
	    else{
		inputFileName = args[i];
	    }
	}
	
	if(inputFileName == null){
	    System.out.println("java Compiler input_file -v verbose_level");
	    System.exit(1);
	}
	
	try {
	    br = new PushbackReader(new FileReader(inputFileName));
	    baseName = removeSuffix(inputFileName, ".l");
	}
	catch (IOException e) {
	    e.printStackTrace();
	} 
	try {
	    Parser p = new Parser(new Lexer(br));
	    System.out.println("[BUILD SC] ");
	    Start tree = p.parse();
	    
	    if(verboseLevel > 1){
		System.out.println("[PRINT SC]");
		tree.apply(new Sc2Xml(baseName));
	    }
	    
	    /*	    System.out.println("[BUILD SA] ");
	    Sc2sa sc2sa = new Sc2sa();
	    tree.apply(sc2sa);
	    SaNode saRoot = sc2sa.getRoot();

	    if(verboseLevel > 1){
		System.out.println("[PRINT SA]");
		new Sa2Xml(saRoot, baseName);
	    }
	    
	    System.out.println("[BUILD TS] ");
	    Ts tableGlobale = new Sa2ts(saRoot).getTableGlobale();

	    if(verboseLevel > 1){
		System.out.println("[PRINT TS]");
		tableGlobale.afficheTout(baseName);
	    }
	    
	    System.out.println("[BUILD C3A] ");
	    C3a c3a = new Sa2c3a(saRoot, tableGlobale).getC3a();

	    if(verboseLevel > 1){
		System.out.println("[PRINT C3A] ");
		c3a.affiche(baseName);
	    }
	    
	    System.out.println("[BUILD PRE NASM] ");
	    Nasm nasm = new C3a2nasm(c3a, tableGlobale).getNasm();
	    if(verboseLevel > 1){
		System.out.println("[PRINT PRE NASM] ");
		nasm.affichePreNasm(baseName);
	    }
	    
	    System.out.println("[BUILD FG] ");
	    Fg fg = new Fg(nasm);
		
	    if(verboseLevel > 1){
		System.out.println("[PRINT FG] ");
		fg.affiche(baseName);
	    }
		
	    System.out.println("[SOLVE FG]");
	    FgSolution fgSolution = new FgSolution(nasm, fg);
	    if(verboseLevel > 1){
		System.out.println("[PRINT FG SOLUTION] ");
		fgSolution.affiche(baseName);
	    }	    
	    System.out.println("[BUILD IG] ");
	    Ig ig = new Ig(fgSolution);
	    
	    if(verboseLevel > 1){
		System.out.println("[PRINT IG] ");
		ig.affiche(baseName);
	    }
	    
	    System.out.println("[ALLOCATE REGISTERS]");
	    ig.allocateRegisters();
				
	    System.out.println("[PRINT NASM]");
	    nasm.afficheNasm(baseName);
	    */
	}
	catch(Exception e){
	    e.printStackTrace();
	    System.out.println(e.getMessage());
	    System.exit(1);
	}
    }


    public static String removeSuffix(final String s, final String suffix)
    {
	if (s != null && suffix != null && s.endsWith(suffix)){
	    return s.substring(0, s.length() - suffix.length());
	}
	return s;
    }
    
}
