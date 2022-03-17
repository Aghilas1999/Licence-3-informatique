package nasm;

public abstract class NasmPseudoInst{
    public NasmLabel label = null;
    public int nb = 1;
    public int sizeInBytes;
    String comment;
    
    void addLabel(String formatInst, NasmOperand label){
	formatInst += label;
    }
    
    public String formatInst(NasmOperand label, String opcode, int nb, String comment){
	String s = "";
	if(label != null)
	    s = s + label + " :";
	s = s + "\t" + opcode;
	s = s + "\t" + nb;
	if(comment != null)
	    s = s + "\t;" + comment;
	return s;
    }

    /*        public <T> T accept(NasmVisitor <T> visitor) {
        return visitor.visit(this);
	}*/

}
