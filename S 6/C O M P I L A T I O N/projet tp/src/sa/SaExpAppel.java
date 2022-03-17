package sa;

public class SaExpAppel implements SaExp{
    private SaAppel val;

    public SaExpAppel(SaAppel val){
	this.val = val;
    }

    public SaAppel getVal(){return this.val;}

    public <T> T accept(SaVisitor <T> visitor) {
        return visitor.visit(this);
    }
}
