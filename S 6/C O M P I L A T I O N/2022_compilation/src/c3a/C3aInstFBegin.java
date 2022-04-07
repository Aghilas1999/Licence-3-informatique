package c3a;
import ts.*;

public class C3aInstFBegin extends C3aInst{
    public TsItemFct val;
    public C3aInstFBegin(TsItemFct val, String comment){
	this.val = val;
	this.comment = comment;
    }

    public String toString(){
	//	String s = "";
	//	if(this.label != null)
	//	    s = s + this.label;
	return this.val.getIdentif() + "\tfbegin\t#" + this.comment;
	//	return s;
    }

    public <T> T accept(C3aVisitor <T> visitor) {
        return visitor.visit(this);
    }
}

