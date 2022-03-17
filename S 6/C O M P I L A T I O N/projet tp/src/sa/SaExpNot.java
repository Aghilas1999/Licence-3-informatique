package sa;

public class SaExpNot implements SaExp{
    private SaExp op1;

    public SaExpNot(SaExp op1){
	this.op1 = op1;
    }

    public SaExp getOp1(){return this.op1;}
    public SaExp getOp2(){return null;}
    
    public <T> T accept(SaVisitor <T> visitor) {
        return visitor.visit(this);
    }
}
