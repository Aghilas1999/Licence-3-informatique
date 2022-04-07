package nasm;

public class NasmJmp extends NasmInst {
    
    public NasmJmp(NasmOperand label, NasmOperand address, String comment){
	this.label = label;
	this.address = address;
	this.comment = comment;
    }

    public <T> T accept(NasmVisitor <T> visitor) {
        return visitor.visit(this);
    }

    public String toString(){
	return super.formatInst(this.label, "jmp", this.address, null, this.comment);
    }

}
