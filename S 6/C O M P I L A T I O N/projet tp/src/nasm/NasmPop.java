package nasm;

public class NasmPop extends NasmInst {
    
    public NasmPop(NasmOperand label, NasmOperand destination, String comment){
	destDef = true;
	this.label = label;
	this.destination = destination;
	this.comment = comment;
    }

    public <T> T accept(NasmVisitor <T> visitor) {
        return visitor.visit(this);
    }

    public String toString(){
	return super.formatInst(this.label, "pop", this.destination, null, this.comment);
    }

}
