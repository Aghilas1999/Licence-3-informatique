package c3a;

public class C3aInstJump extends C3aInst{
    public C3aOperand result; // résultat ou destination du saut (ou NULL)

    public C3aInstJump(C3aOperand result, String comment){
	this.result = result;
	this.comment = comment;
    }

    public String toString(){
	String s = "";
	if(this.label != null)
	    s = s + this.label;
	s = s + "\t";
	s = s + "goto " + this.result;
	return s;
    }

    public <T> T accept(C3aVisitor <T> visitor) {
        return visitor.visit(this);
    }
}

