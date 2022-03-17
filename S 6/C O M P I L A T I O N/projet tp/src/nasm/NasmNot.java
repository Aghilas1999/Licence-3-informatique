package nasm;

public class NasmNot extends NasmInst {
    
    public NasmNot(NasmOperand label, NasmOperand destination, String comment){
	destUse = true;
	destDef = true;
	this.label = label;
	this.destination = destination;
	this.comment = comment;
    }

    public <T> T accept(NasmVisitor <T> visitor) {
        return visitor.visit(this);
    }

    public String toString(){
	return super.formatInst(this.label, "not", this.destination, null, this.comment);
    }

}
