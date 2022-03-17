package c3a;

public class C3aInstWrite extends C3aInst{
    public C3aOperand op1;    // opérande 1 (ou null)
    public C3aOperand result; // résultat ou destination du saut (ou NULL)

    public C3aInstWrite(C3aOperand op1, String comment){
	this.op1 = op1;
	this.comment = comment;
    }

    public String toString(){
	String s = "";
	if(this.label != null)
	    s = s + this.label;
	s = s + "\t";
	s = s + "write " + this.op1 + "\t" + this.comment;
	return s;
    }

    public <T> T accept(C3aVisitor <T> visitor) {
        return visitor.visit(this);
    }

}

