package nasm;

public class NasmInt extends NasmInst {
    
    public NasmInt(NasmOperand label, String comment){
	this.label = label;
	this.comment = comment;
    }

    public <T> T accept(NasmVisitor <T> visitor) {
        return visitor.visit(this);
    }

    public String toString(){
	return super.formatInst(this.label, "int 0x80", null, null, this.comment);
    }

}
