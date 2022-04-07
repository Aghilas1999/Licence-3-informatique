package c3a;
import ts.*;

public class C3aVar extends C3aOperand{
    public TsItemVar item;
    public C3aOperand index;

    public C3aVar(TsItemVar item, C3aOperand index){
	this.item = item;
	this.index = index;
    }

    public String toString(){
	if(this.index != null)
	    //	    return this.item.getIdentif() + "[" + this.item.getTaille() + "]";
	    return this.item.getIdentif() + "[" + this.index + "]";
	else
	    return this.item.getIdentif();
    }
    
    public <T> T accept(C3aVisitor <T> visitor) {
        return visitor.visit(this);
    }
}
