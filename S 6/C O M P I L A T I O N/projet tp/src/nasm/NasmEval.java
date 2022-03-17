package nasm;


import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

/*
This class emulates pre-nasm for x86-64 architecture.
It handles only integers on 4 bytes and have unlimited register.
 */

public class NasmEval implements NasmVisitor<Integer>{
    private Nasm code;
    private int regNb;
    private HashMap<String, Integer> labelToLine;
    private HashMap<String, Integer> globalVariableToAddress;
    private Memory memory;
    private boolean stop;
    private ArrayList<Integer> output;

    // registers
    private int[] registers;
    
    private int eip = 0;
    private int eax = 0; 
    private int ebx = 0; 
    private int ecx = 0; 
    private int edx = 0; 

    //flags
    // Carry Flag -> not used
    private boolean CF = false;
    // Parity Flag -> not used
    private boolean PF = false;
    // Zero Flag
    private boolean ZF = false;
    // Sign Flag
    private boolean SF = false;
    // Overflow Flag -> not used
    private boolean OF = false;
	
    private int verboseLevel;

    public void PrintGlobalVariables(){
    for (HashMap.Entry<String, Integer> e : globalVariableToAddress.entrySet())
   	System.out.println("[" + e.getValue() + "]\t" + e.getKey() + " = " + memory.readInt(e.getValue()));

    }
    public NasmEval(Nasm code, int stackSize, int verboseLevel){
        this.code = code;
	this.verboseLevel = verboseLevel;
	regNb = this.code.getTempCounter();
        registers = new int[10*regNb];
        eip = 0;
        stop = false;
        output = new ArrayList<>();
        labelToLine = associateLabelToLine();
	int dataSize = 0;
	globalVariableToAddress = new HashMap<>();

	// compute addresses of global variables
	for(int i = 0; i < code.sectionBss.size(); i++){
	    NasmPseudoInst pseudoInst = this.code.sectionBss.get(i);
	    globalVariableToAddress.put(pseudoInst.label.val, dataSize);
	    //	    System.out.println("var :" + pseudoInst.label.val + " address = " + dataSize);
	    dataSize += pseudoInst.nb * pseudoInst.sizeInBytes;
	}

	memory = new Memory(dataSize, stackSize);
	
        while(!stop && eip < code.sectionText.size()){
	    NasmInst inst = this.code.sectionText.get(eip);
	    if(verboseLevel > 0){
		System.out.println("--------------------------------------");

		PrintGlobalVariables();
		System.out.println("eip = " + eip + "\tesp = " + memory.esp + "\t ebp = " + memory.ebp);
		System.out.println("eax = " + eax + "\tebx = " + ebx + "\tecx = " + ecx + "\tedx = " + edx);
		System.out.println("CF = " + CF + "\tPF = " + PF + "\tZF = " + ZF + "\tSF = " + SF + "\tOF = " + OF);
		printRegisters();
		System.out.print("stack : \t");
		memory.printStack();
		System.out.println(inst);
	    }
            eip = inst.accept(this);
        }
	//	displayOutput();
    }

    public void printRegisters(){
	for(int i=0; i < regNb; i++){
	    System.out.print("r" + i + ":" + registers[i] + "\t");
	}
	//	System.out.println();
    }

    public void displayOutput(){
        for(var val : output)
            System.out.println(val);
    }

    public void displayOutput(String outputFile) throws FileNotFoundException {
        var out = new PrintStream(outputFile);
        for(var val : output)
            out.println(val);
    }
    
    private HashMap<String, Integer> associateLabelToLine(){
	HashMap<String, Integer> labelToLine = new HashMap<>();
        var instructions = code.sectionText;
        for(int i = 0; i <instructions.size(); i++){
            if(instructions.get(i).label != null) {
                var label = (NasmLabel)instructions.get(i).label;
                labelToLine.put(label.val, i);
            }
        }
	return labelToLine;
    }

    private void affect(NasmOperand dest, int src){
        if(dest instanceof NasmRegister)
	    affect((NasmRegister) dest, src);
	else if(dest instanceof NasmAddress)
	    affect((NasmAddress) dest, src);
    }

    // write in a register
    
    private void affect(NasmRegister dest, int src){
	//		System.out.println("affect value " + src + " to register");
	if(dest.color == Nasm.REG_EAX)
	    eax = src;
	else if (dest.color == Nasm.REG_EBX)
	    ebx = src;
	else if (dest.color == Nasm.REG_ECX)
	    ecx = src;
	else if (dest.color == Nasm.REG_EDX)
	    edx = src;
	else if (dest.color == Nasm.REG_ESP)
	    memory.esp = src;
	else if (dest.color == Nasm.REG_EBP)
	    memory.ebp = src;
	else
	    registers[dest.val] = src;
    }

    // write in memory
    private void affect(NasmAddress dest, int src){
	if(dest.base instanceof NasmLabel){
	    if(globalVariableToAddress.containsKey(((NasmLabel)dest.base).val)){
		int globalVarAddress = globalVariableToAddress.get(((NasmLabel)dest.base).val);
		if(dest.direction == '+')
		    memory.writeInt(globalVarAddress + dest.offset.accept(this), src);
		else
		    memory.writeInt(globalVarAddress - dest.offset.accept(this), src);
	    }
	}
	else{
	    if(dest.direction == '+')
		memory.writeInt(dest.base.accept(this) + dest.offset.accept(this), src);
	    else
		memory.writeInt(dest.base.accept(this) - dest.offset.accept(this), src);
	}
    }

    /* visit operand -> return its value*/
    @Override
    public Integer visit(NasmAddress operand) {
        if(operand.direction == '+')
            return memory.readInt(operand.base.accept(this) + operand.offset.accept(this));
        else
            return memory.readInt(operand.base.accept(this) - operand.offset.accept(this));
    }

    
    @Override
    public Integer visit(NasmRegister operand) {
	if(operand.color == Nasm.REG_EAX)
	    return eax;
	if (operand.color == Nasm.REG_EBX)
	    return ebx;
	if (operand.color == Nasm.REG_ECX)
	    return ecx;
	if (operand.color == Nasm.REG_EDX)
	    return edx;
	if (operand.color == Nasm.REG_ESP)
	    return memory.esp;
	if (operand.color == Nasm.REG_EBP)
	    return memory.ebp;
	else
	    return registers[operand.val];
    }

    @Override
    public Integer visit(NasmConstant operand) {
	
        return operand.val;
    }

    @Override
    public Integer visit(NasmLabel operand) {
	// if label corresponds to a line of code, return the line number
        if(labelToLine.containsKey(operand.val))
            return labelToLine.get(operand.val);
	
	// if label corresponds to a global variable, return it address
        if(globalVariableToAddress.containsKey(operand.val)){
	    int globalVarAddress = globalVariableToAddress.get(operand.val);
	    //	    System.out.println("-->var = " + operand.val + " address = " + globalVarAddress + " value = " + memory.readInt(globalVarAddress));
            return globalVarAddress;
	    //            return memory.readInt(globalVarAddress);
	}
	throw new RuntimeException("label " + operand.val + "does not correspond to existing label or global variable");
    }

    /* arithmetic operations */
    
    @Override
    public Integer visit(NasmAdd inst) {
	affect(inst.destination, inst.source.accept(this) + inst.destination.accept(this));
        return eip + 1;
    }

    @Override
    public Integer visit(NasmSub inst) {
        affect(inst.destination, inst.destination.accept(this) - inst.source.accept(this));
	return eip + 1;
    }

    @Override
    public Integer visit(NasmMul inst) {
        affect(inst.destination, inst.source.accept(this) * inst.destination.accept(this));
	return eip + 1;
    }

    @Override
    public Integer visit(NasmDiv inst) {
        var divisor  = inst.source.accept(this);
        var temp = eax;
        eax = temp / divisor;
        edx = temp % divisor;
	return eip + 1;
    }

    /* logical operations */
    @Override
    public Integer visit(NasmOr inst) {
        affect(inst.destination, inst.source.accept(this) | inst.destination.accept(this));
        return eip + 1;
    }

    @Override
    public Integer visit(NasmNot inst) {
        affect(inst.destination, ~ inst.destination.accept(this));
        return eip + 1;
    }

    @Override
    public Integer visit(NasmXor inst) {
        affect(inst.destination, inst.source.accept(this) ^ inst.destination.accept(this));
	return eip + 1;
    }

    @Override
    public Integer visit(NasmAnd inst) {
        affect(inst.destination, inst.source.accept(this) & inst.destination.accept(this));
	return eip + 1;
    }

    /* function call */
    @Override
    public Integer visit(NasmCall inst) {
        if(inst.address instanceof NasmLabel && ((NasmLabel)inst.address).val.equals("iprintLF")){
            output.add(eax);
	    return eip + 1;
	}
	memory.pushInt(eip);
	return inst.address.accept(this);
    }

    /* comparison */
    @Override
    public Integer visit(NasmCmp inst) {
        int valSrc = inst.source.accept(this);
        int valDest = inst.destination.accept(this);
        ZF = (valDest == valSrc)? true : false;
	SF = (valDest < valSrc)? true : false;
        return eip + 1;
    }
    
    /* jumps */
    @Override
    public Integer visit(NasmJe inst) {
        return (ZF)? inst.address.accept(this) : eip + 1;
    }

    @Override
    public Integer visit(NasmJle inst) {
        return (ZF || SF)? inst.address.accept(this) : eip + 1;
    }

    @Override
    public Integer visit(NasmJne inst) {
	//        return (!ZF && SF)? inst.address.accept(this) : eip + 1;
        return (!ZF)? inst.address.accept(this) : eip + 1;
    }

    @Override
    public Integer visit(NasmJge inst) {
        return (ZF || !SF)? inst.address.accept(this) : eip + 1;
    }

    @Override
    public Integer visit(NasmJl inst) {
	return (!ZF && SF)? inst.address.accept(this) : eip + 1;
    }

    @Override
    public Integer visit(NasmJg inst) {
        return (!ZF || SF)? inst.address.accept(this) : eip + 1;
    }

    @Override
    public Integer visit(NasmJmp inst) {
        return inst.address.accept(this);
    }

    @Override
    public Integer visit(NasmPop inst) {
        affect(inst.destination, memory.popInt());
	return eip + 1;
    }

    @Override
    public Integer visit(NasmPush inst) {
        memory.pushInt(inst.source.accept(this));
        return eip + 1;
    }

    @Override
    public Integer visit(NasmRet inst) {
        return memory.popInt() + 1;
    }

    @Override
    public Integer visit(NasmMov inst) {
        affect(inst.destination, inst.source.accept(this));
	return eip + 1;
    }

    @Override
    public Integer visit(NasmInt inst) {
        if(eax == 1)
            stop = true;
        return eip + 1;
    }

    @Override
    public Integer visit(NasmInst inst) {
        return 0;
    }

    @Override
    public Integer visit(NasmEmpty inst) {
        return eip + 1;
    }

    public Integer visit(NasmResb pseudoInst){return 0;}
    public Integer visit(NasmResw pseudoInst){return 0;}
    public Integer visit(NasmResd pseudoInst){return 0;}
    public Integer visit(NasmResq pseudoInst){return 0;}
    public Integer visit(NasmRest pseudoInst){return 0;}



}
