package c3a;

public class C3aTemp extends C3aOperand{
    public int num;

    public C3aTemp(int num){
	this.num = num;
    }

    public String toString(){
	return "t" + this.num;
    }
    public <T> T accept(C3aVisitor <T> visitor) {
        return visitor.visit(this);
    }
}
