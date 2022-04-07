package c3a;

public class C3aInstFEnd extends C3aInst{
    public C3aInstFEnd(String comment){
	this.comment = comment;
    }

    public String toString(){
	String s = "";
	if(this.label != null)
	    s = s + this.label;
	s = s + "\t";
	s = s + "fend";
	return s;
    }

    public <T> T accept(C3aVisitor <T> visitor) {
        return visitor.visit(this);
    }
}

