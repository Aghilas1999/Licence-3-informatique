package nasm;

public class NasmJle extends NasmInst {
    
    public NasmJle(NasmOperand label, NasmOperand address, String comment){
	this.label = label;
	this.address = address;
	this.comment = comment;
    }

    public <T> T accept(NasmVisitor <T> visitor) {
        return visitor.visit(this);
    }

    public String toString(){
	//	String formatInst
	//	addLabel(label);

	//return formatInst;

	
	return super.formatInst(this.label, "jle", this.address, null, this.comment);
    }

}
