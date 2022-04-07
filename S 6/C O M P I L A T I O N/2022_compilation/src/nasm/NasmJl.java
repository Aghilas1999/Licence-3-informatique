package nasm;

public class NasmJl extends NasmInst {
    
    public NasmJl(NasmOperand label, NasmOperand address, String comment){
	this.label = label;
	this.address = address;
	this.comment = comment;
    }

    public <T> T accept(NasmVisitor <T> visitor) {
        return visitor.visit(this);
    }

    public String toString(){
	return super.formatInst(this.label, "jl", this.address, null, this.comment);
    }

}
