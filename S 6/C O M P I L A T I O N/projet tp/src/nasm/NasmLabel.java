package nasm;

public class NasmLabel extends NasmOperand {
    public String val;

    public NasmLabel(String val){
	this.val = val;
    }
    public String toString(){
	return this.val;
    }
    public <T> T accept(NasmVisitor <T> visitor) {
        return visitor.visit(this);
    }
}
