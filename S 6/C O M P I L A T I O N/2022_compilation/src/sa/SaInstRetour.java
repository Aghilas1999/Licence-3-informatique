package sa;

public class SaInstRetour implements SaInst{
    private SaExp val;

    public SaInstRetour(SaExp val){
	this.val = val;
    }
    public SaExp getVal(){return this.val;}

    public <T> T accept(SaVisitor <T> visitor) {
        return visitor.visit(this);
    }

}
