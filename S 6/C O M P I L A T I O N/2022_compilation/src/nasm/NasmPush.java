package nasm;

public class NasmPush extends NasmInst {
    
    public NasmPush(NasmOperand label, NasmOperand source, String comment){
	//	srcUse = true;
	this.label = label;
	this.source = source;
	this.comment = comment;
    }

    public <T> T accept(NasmVisitor <T> visitor) {
        return visitor.visit(this);
    }

    public String toString(){
	return super.formatInst(this.label, "push", this.source, null, this.comment);
    }

}
