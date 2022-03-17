package nasm;
import java.util.*;
import java.io.*;
import ts.*;
    
public class Nasm{
    public List<NasmInst> listeInst;
    private int tempCounter;
    Ts tableGlobale;
    public static int REG_EAX = 0;
    public static int REG_EBX = 1;
    public static int REG_ECX = 2;
    public static int REG_EDX = 3;
    public static int REG_ESP = -1;
    public static int REG_EBP = -2;
    public static int REG_UNK = -3;
	

    public Nasm(Ts tableGlobale){
	this.tableGlobale = tableGlobale;
	this.listeInst = new ArrayList<NasmInst>();

    }

    public int getTempCounter(){return this.tempCounter;}
    public int setTempCounter(int c){return this.tempCounter = c;}
    
    
    public void ajouteInst(NasmInst inst){
	if(inst instanceof NasmMov && inst.destination instanceof NasmAddress && inst.source instanceof NasmAddress){
	    NasmRegister newReg = newRegister();
	    this.listeInst.add(new NasmMov(inst.label, newReg, inst.source, inst.comment)); 
	    this.listeInst.add(new NasmMov(null, inst.destination, newReg, "on passe par un registre temporaire"));
	    return;
	}
	
	//	if(inst instanceof NasmCmp && inst.destination instanceof NasmConstant && inst.source instanceof NasmConstant){
	if(inst instanceof NasmCmp
	   && (inst.destination instanceof NasmConstant
	       || (inst.destination instanceof NasmAddress && inst.source instanceof NasmAddress))){
		NasmRegister newReg = newRegister();
		this.listeInst.add(new NasmMov(inst.label, newReg, inst.destination, inst.comment)); 
		this.listeInst.add(new NasmCmp(null, newReg, inst.source, "on passe par un registre temporaire"));
		return;
	    }
	
	this.listeInst.add(inst);
    }

    public NasmRegister newRegister(){
	return new NasmRegister(tempCounter++);
    }


    public void affichePreambule(PrintStream out)
    {
	out.println("%include\t'io.asm'\n");
	/* Variables globales */
	out.println("section\t.bss");
	out.println("sinput:\tresb\t255\t;reserve a 255 byte space in memory for the users input string");


	Set< Map.Entry< String, TsItemVar> > st = tableGlobale.variables.entrySet();    
	for (Map.Entry< String, TsItemVar> me:st){
	    TsItemVar tsItem = me.getValue(); 
	    String identif = me.getKey();
	    out.println(identif + " :\tresd\t" + tsItem.taille * 4);
	}
	out.println("\nsection\t.text");
	out.println("global _start");
	out.println("_start:");
    }


    
    public void affichePre(String baseFileName){
	String fileName;
	PrintStream out = System.out;

	if (baseFileName != null){
	    try {
		baseFileName = baseFileName;
		fileName = baseFileName + ".pre-nasm";
		out = new PrintStream(fileName);
	    }
	    
	    catch (IOException e) {
		System.err.println("Error: " + e.getMessage());
	    }
	}
    	Iterator<NasmInst> iter = this.listeInst.iterator();
    	while(iter.hasNext()){
    	    out.println(iter.next());
    	}
    }

    public void affiche(String baseFileName){
	String fileName;
	PrintStream out = System.out;

	if (baseFileName != null){
	    try {
		baseFileName = baseFileName;
		fileName = baseFileName + ".nasm";
		out = new PrintStream(fileName);
	    }
	    
	    catch (IOException e) {
		System.err.println("Error: " + e.getMessage());
	    }
	}

	this.affichePreambule(out);

	
    	Iterator<NasmInst> iter = this.listeInst.iterator();
    	while(iter.hasNext()){
    	    out.println(iter.next());
    	}
    }
}
