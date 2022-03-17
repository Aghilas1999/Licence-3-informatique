package c3a;
import java.util.*;
import java.io.*;
import ts.*;

public class C3aEval implements C3aVisitor <Integer> {
  private C3a c3a;
  private Ts tableGlobale;
    private int nbReg;
  private int[] reg;
  private int[] stack;
  private int[] varGlob;
  private int eip;
  private int ebp;
  private int esp;
  private int stackSize;
  private Map< Integer, Integer> label2index;
  private Map< String, Integer> function2index;
  private boolean debug;
  private boolean stop;
  private ArrayList<String> programOutput = new ArrayList<String>();
  private TsItemFct currentFct;
  private Stack<TsItemFct> stackCurrentFct;

  private C3aOperand storeReturnValue;
  private Stack<C3aOperand> stackStoreReturnValue;

    
    public C3aEval(C3a c3a, Ts tableGlobale, int stackSize, int verboseLevel){
  	debug = true;
  	this.c3a = c3a;
  	stack = new int[stackSize];
	nbReg = c3a.getTempCounter();
  	reg = new int[nbReg];
  	esp = 0; 
  	varGlob = new int[tableGlobale.nbVar()];
  	this.tableGlobale = tableGlobale;
  	this.currentFct = null;
  	storeReturnValue = null;
  	label2index = new HashMap< Integer, Integer>();
  	function2index = new HashMap< String, Integer>();
  	C3aInst c3aInst = null;
	stackCurrentFct = new Stack<>();
	stackStoreReturnValue = new Stack<>();
	eip = 0;
	
  	for(int i = 0; i < c3a.listeInst.size(); i++){
  	    c3aInst = c3a.listeInst.get(i);
  	    if(c3aInst.label != null){
  		label2index.put(c3aInst.label.number, i);
  	    }
	    if(c3aInst instanceof C3aInstFBegin){
		String identif = ((C3aInstFBegin)c3aInst).val.identif;
		function2index.put(identif, i);
	    }
  	}
  	stop = false;
  	while(!stop){
  	    c3aInst = c3a.listeInst.get(eip);
	    //	    System.out.println("<" + c3aInst.getClass().getSimpleName() + ">");
	    if(verboseLevel > 0)
		afficheEtat(c3aInst);

  	    c3aInst.accept(this);
  	}
  }

    public void afficheEtat(C3aInst inst){
	System.out.println("---------------------------------------------");
	System.out.println("eip = " + eip + "\tesp = " + esp + "\tebp = " + ebp);
	System.out.print("PILE : ");
	affichePile();
	System.out.print("REGISTRES : ");
	afficheReg();
	System.out.println(inst);
    }


    
    public void affiche(String baseFileName){
	String fileName;
	PrintStream out = System.out;
	
	if (baseFileName != null){
	    try {
		baseFileName = baseFileName;
		fileName = baseFileName + ".c3aout";
		out = new PrintStream(fileName);
	    }
	    
	    catch (IOException e) {
		System.err.println("Error: " + e.getMessage());
	    }
	}
	for (String line : programOutput)
	    out.println(line);
    }
    
    
    public void affichePileVertical(){
	for(int i = esp-1; i >= 0; i--){
	    System.out.println(i + ":" + stack[i]);
	}
    }

    public void affichePile(){
	for(int i = 0; i < esp; i++){
	    System.out.print(stack[i] + " ");
	}
	    System.out.println("");
	
    }

    public void push(int i){
	if(esp == stackSize){
	    System.out.println("stack overflow !!!");
	    System.exit(1);
	}
	stack[esp] = i;
	esp++;
    }

    public void afficheReg(){
	for(int i=0; i <nbReg; i++)
	    System.out.print(i + ":" + reg[i] + " ");
	System.out.println("");
    }
    
    public void pushReg(){
	//	System.out.println("pushReg " + nbReg + " registers");
	for(int i=0; i <nbReg; i++)
	    push(reg[i]);
    }
    
    public void popReg(){
	//	System.out.println("popReg " + nbReg + " registers");
	for(int i=nbReg-1; i >=0; i--)
	    reg[i] = pop();
    }
    
    public int pop(){
	if(esp == 0){
	    System.out.println("cannot pop empty stack !!!");
	    System.exit(1);
	}
	esp--;
	return stack[esp];
    }
    
    public Integer visit(C3aConstant constant){
	return constant.val;
    }
    
    public Integer visit(C3aTemp temp){
	return reg[temp.num];
    }
 
    public Integer visit(C3aVar var){
	int adresse = var.item.adresse;
	// variable globale
	if(var.item.portee == this.tableGlobale){ 
	    int offset = (var.index != null)? var.index.accept(this) : 0;
	    return varGlob[adresse/4 + offset];
	}
	if(var.item.isParam){ // parametre
	    int nbArgs = this.currentFct.nbArgs;
	    return stack[ebp - 2 - nbArgs + adresse / 4];
	}
	// variable locale
	return stack[ebp + 1 + adresse / 4];
    }

    public void affect(C3aOperand op, int val){
	if(op instanceof C3aVar){
	    C3aVar var = (C3aVar) op;
	    int adresse = var.item.adresse;
	    // variable globale
	    if(var.item.portee == this.tableGlobale){ 
		int offset = (var.index != null)? var.index.accept(this) : 0;
		varGlob[adresse/4 + offset] = val;
	    }
	    else if(var.item.isParam){ // parametre
		int nbArgs = this.currentFct.nbArgs;
		stack[ebp - 2 - nbArgs + adresse/4] = val;
	    }
	    else // variable locale
		stack[ebp + 1 + adresse/4] = val;
	}
	else if(op instanceof C3aTemp){
	    C3aTemp temp = (C3aTemp) op;
	    reg[temp.num] = val;
	}
    }

    public Integer visit(C3aFunction fct){
	return null;
    }

    public Integer visit(C3aLabel label){
	return label.number;
    }
    /*--------------------------------------------------------------------------------------------------------------*/
    
    public Integer visit(C3aInstStop inst){
	stop = true;
	return null;
    }

    public Integer visit(C3aInstAdd inst){
	//if(debug){afficheEtat(inst);}
	affect(inst.result, inst.op1.accept(this) + inst.op2.accept(this));
	eip++;
	return null;
    }

    public Integer visit(C3aInstSub inst){
	//if(debug){afficheEtat(inst);}
	affect(inst.result, inst.op1.accept(this) - inst.op2.accept(this));
	eip++;
	return null;
    }

    public Integer visit(C3aInstMult inst){
	//if(debug){afficheEtat(inst);}
	affect(inst.result, inst.op1.accept(this) * inst.op2.accept(this));
	eip++;
	return null;
    }

    public Integer visit(C3aInstDiv inst){
	//if(debug){afficheEtat(inst);}
	affect(inst.result, inst.op1.accept(this) / inst.op2.accept(this));
	eip++;
	return null;
    }

    public Integer visit(C3aInstJumpIfLess inst){
	//if(debug){afficheEtat(inst);}
	if(inst.op1.accept(this) < inst.op2.accept(this))
	    eip = label2index.get(inst.result.accept(this));
	else
	    eip++;
	return null;
    }

    public Integer visit(C3aInstJumpIfEqual inst){
	//if(debug){afficheEtat(inst);}
	if(inst.op1.accept(this) == inst.op2.accept(this))
	    eip = label2index.get(inst.result.accept(this));
	else
	    eip++;
	return null;
    }

    public Integer visit(C3aInstJumpIfNotEqual inst){
	//if(debug){afficheEtat(inst);}
	if(inst.op1.accept(this) != inst.op2.accept(this))
	    eip = label2index.get(inst.result.accept(this));
	else
	    eip++;
	return null;
    }
    
    public Integer visit(C3aInstJump inst){
	//if(debug){afficheEtat(inst);}
	eip =  label2index.get(inst.result.accept(this));
	return null;
    }

    public Integer visit(C3aInstAffect inst){
	//if(debug){afficheEtat(inst);}
	affect(inst.result, inst.op1.accept(this));
	eip++;
	return null;
    }

    public Integer visit(C3aInstParam inst){
	//if(debug){afficheEtat(inst);}
	push(inst.op1.accept(this));
	eip++;
	return null;
    }

    public Integer visit(C3aInstReturn inst){
	//if(debug){afficheEtat(inst);}
	stack[ebp - 2] = (inst.op1 != null)? inst.op1.accept(this) : 0;
	eip++;
	return null;
    }

    public Integer visit(C3aInstWrite inst){
	//if(debug){afficheEtat(inst);}
	programOutput.add(inst.op1.accept(this).toString());
	eip++;
	return null;
    }

    public Integer visit(C3aInstCall inst){
	//if(debug){afficheEtat(inst);}
	//	storeReturnValue = inst.result;
	stackStoreReturnValue.push(inst.result);
	// allocation mémoire pour la valeur de retour
	//if(debug){System.out.println("allocation mémoire pour la valeur de retour, esp++");}
	esp++;
	//if(debug){System.out.println("sauvegarde de l'index de l'instruction à effectuer après l'appel : push(eip + 1)");}
	push(eip + 1);
	stackCurrentFct.push(currentFct);
	eip = function2index.get(inst.op1.val.identif);
	return null;
   }

    public Integer visit(C3aInstFBegin inst){
	//if(debug){afficheEtat(inst);}
	currentFct = inst.val;
	int nbVarLoc = currentFct.getTable().getAdrVarCourante();
	
	//if(debug){System.out.println("sauvegarde de l'ancienne valeur de ebp");}
	push(ebp);
	//if(debug){System.out.println("nouvelle valeur de ebp");}
	ebp = esp - 1;
	//if(debug){System.out.println("allocation des variables locales");}
	esp = esp + nbVarLoc;
	pushReg();
	eip++;
	return null;
    }

    public Integer visit(C3aInstFEnd inst){
	//if(debug){afficheEtat(inst);}
	int nbVarLoc = currentFct.getTable().getAdrVarCourante();
	int nbParam = currentFct.nbArgs;
	popReg();
	esp = esp - nbVarLoc;
	ebp = pop();
	eip = pop();
	int rv = pop();
	storeReturnValue = stackStoreReturnValue.pop();
	if(storeReturnValue != null){
	    affect(storeReturnValue, rv);
	    storeReturnValue = null;
	}
	currentFct = stackCurrentFct.pop();
	esp = esp - nbParam;
	return null;
    }

    public Integer visit(C3aInstRead inst){
	/*	Int label = (inst.label != null) ? inst.label.accept(this) : null;
	Int result  = inst.result.accept(this);
	NasmRegister reg_eax = nasm.newRegister();
	reg_eax.colorRegister(Nasm.REG_EAX);

	nasm.ajouteInst(new NasmMov (label, reg_eax, new NasmLabel("sinput"), "Read 1"));
	nasm.ajouteInst(new NasmCall(null, new NasmLabel("readline"), "Read 2"));
	nasm.ajouteInst(new NasmMov (null, reg_eax, new NasmLabel("sinput"), "Read 3"));
	nasm.ajouteInst(new NasmMov (null, result, reg_eax , "Read 4"));*/
	eip++;
	return null;
    }


    public Integer visit(C3aInst inst){
	return null;}
    
}
