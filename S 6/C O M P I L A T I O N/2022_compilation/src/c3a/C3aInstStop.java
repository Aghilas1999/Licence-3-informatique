package c3a;

public class C3aInstStop extends C3aInst{
    public C3aOperand returnValue; 

    public C3aInstStop(C3aOperand returnValue, String comment){
	this.returnValue = returnValue;
	this.comment = comment;
    }

    public String toString(){
	String s = "";
	if(this.label != null)
	    s = s + this.label;
	s = s + "\t";
	s = s + "stop ";
	if(this.returnValue != null)
	    s = s + this.returnValue;
	s = s + this.comment;
	return s;
    }

    public <T> T accept(C3aVisitor <T> visitor) {
        return visitor.visit(this);
    }
}

