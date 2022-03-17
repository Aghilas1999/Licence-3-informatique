package c3a;

public class C3aInstJumpIfEqual extends C3aInst{
    public C3aOperand op1;    // opérande 1 (ou null)
    public C3aOperand op2;    // opérande 2 (ou null)
    public C3aOperand result; // résultat ou destination du saut (ou NULL)

    public C3aInstJumpIfEqual(C3aOperand op1, C3aOperand op2, C3aOperand result, String comment){
	this.op1 = op1;
	this.op2 = op2;
	this.result = result;
	this.comment = comment;
    }

    public String toString(){
	String s = "";
	if(this.label != null)
	    s = s + this.label;
	s = s + "\tif " + this.op1 + " = " + this.op2 + " goto " + this.result;
	return s;
    }

    public <T> T accept(C3aVisitor <T> visitor) {
        return visitor.visit(this);
    }
}

