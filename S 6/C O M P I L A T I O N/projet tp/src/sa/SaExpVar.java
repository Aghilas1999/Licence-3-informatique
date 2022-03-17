package sa;

public class SaExpVar implements SaExp{
    private SaVar var;

    public SaExpVar(SaVar var){
	this.var = var;
    }

    public SaVar getVar(){return this.var;}

    public <T> T accept(SaVisitor <T> visitor) {
        return visitor.visit(this);
    }
}
