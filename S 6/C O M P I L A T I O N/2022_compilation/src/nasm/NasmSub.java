package nasm;

public class NasmSub extends NasmInst {
    
    public NasmSub(NasmOperand label, NasmOperand destination, NasmOperand source, String comment){
	destUse = true;
	destDef = true;
	srcUse = true;
	this.label = label;
	this.destination = destination;
	this.source = source;
	this.comment = comment;
    }

    public <T> T accept(NasmVisitor <T> visitor) {
        return visitor.visit(this);
    }
        
    public String toString(){
	return super.formatInst(this.label, "sub", this.destination, this.source, this.comment);
    }
}
