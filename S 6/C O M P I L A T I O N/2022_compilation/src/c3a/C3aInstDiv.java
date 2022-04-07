package c3a;

public class C3aInstDiv extends C3aInst{
    public C3aOperand op1;    // opérande 1 (ou null)
    public C3aOperand op2;    // opérande 2 (ou null)
    public C3aOperand result; // résultat ou destination du saut (ou NULL)

    public C3aInstDiv(C3aOperand op1, C3aOperand op2, C3aOperand result, String comment){
	this.op1 = op1;
	this.op2 = op2;
	this.result = result;
	this.comment = comment;
    }

    public String toString(){
	if(this.label != null)
	    return this.label + "\t" + this.result + " = " + this.op1 + " / " + this.op2 + this.comment;
	else
	    return              "\t" + this.result + " = " + this.op1 + " / " + this.op2 + this.comment;
    }

    public <T> T accept(C3aVisitor <T> visitor) {
        return visitor.visit(this);
    }

}

