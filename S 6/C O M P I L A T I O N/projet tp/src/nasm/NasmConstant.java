package nasm;

public class NasmConstant extends NasmOperand {
    public int val;
    public NasmConstant(int val){
	this.val = val;
    }

    public String toString(){
	return Integer.toString(this.val);
    }

    public <T> T accept(NasmVisitor <T> visitor) {
        return visitor.visit(this);
    }
}
