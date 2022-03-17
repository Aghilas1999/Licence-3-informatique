package c3a;

public class C3aConstant extends C3aOperand{
    public int val;

    public C3aConstant(int val){
	this.val = val;
    }

    public String toString(){return Integer.toString(this.val);}

    public <T> T accept(C3aVisitor <T> visitor) {
        return visitor.visit(this);
    }
}
