package nasm;

public class NasmEmpty extends NasmInst {
    
    public NasmEmpty(NasmOperand label, String comment){
	this.label = label;
	this.comment = comment;
    }

    public <T> T accept(NasmVisitor <T> visitor) {
        return visitor.visit(this);
    }

    public String toString(){
	return super.formatInst(this.label, "", null, null, this.comment);
    }

}
