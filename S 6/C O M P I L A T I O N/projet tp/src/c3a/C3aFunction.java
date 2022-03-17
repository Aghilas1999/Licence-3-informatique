package c3a;
import ts.*;


public class C3aFunction extends C3aOperand{
    public TsItemFct val;

    public C3aFunction(TsItemFct val){
	this.val = val;
    }

    public TsItemFct getValue(){return val;}

    public String toString(){
	return this.val.getIdentif();
    }
    public <T> T accept(C3aVisitor <T> visitor) {
        return visitor.visit(this);
    }
}
