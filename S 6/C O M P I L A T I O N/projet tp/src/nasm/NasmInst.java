package nasm;

public abstract class NasmInst{
    public NasmOperand label = null;
    public NasmOperand destination = null;
    public NasmOperand source = null;
    public NasmOperand address = null;
    public boolean destUse = false;
    public boolean destDef = false;
    public boolean srcUse = false;
    public boolean srcDef = false;
    String comment;
    
    void addLabel(String formatInst, NasmOperand label){
	formatInst += label;
    }
    
    public String formatInst(NasmOperand label, String opcode, NasmOperand arg1, NasmOperand arg2, String comment){
	String s = "";
	if(label != null)
	    s = s + label + " :";
	s = s + "\t" + opcode;
	if(arg1 != null)
	    if(arg1 instanceof NasmAddress)
		s = s + "\tdword [" + arg1 + "]";
	    else
		s = s + "\t" + arg1;
	if(arg2 != null)
	    if(arg2 instanceof NasmAddress)
		s = s + ",\tdword [" + arg2 + "]";
	    else
		s = s + ",\t" + arg2;
	if(comment != null)
	    s = s + "\t;" + comment;
	return s;
    }

        public <T> T accept(NasmVisitor <T> visitor) {
        return visitor.visit(this);
    }

}
