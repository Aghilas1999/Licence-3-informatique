package nasm;

public class NasmRet extends NasmInst {
    
    public NasmRet(NasmOperand label, String comment){
	this.label = label;
	this.comment = comment;
    }

    public <T> T accept(NasmVisitor <T> visitor) {
        return visitor.visit(this);
    }

    public String toString(){
	return super.formatInst(this.label, "ret", null, null, this.comment);
    }

}
