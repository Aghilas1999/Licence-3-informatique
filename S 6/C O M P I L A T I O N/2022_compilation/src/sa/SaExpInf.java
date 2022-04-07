package sa;

public class SaExpInf implements SaExp{
    private SaExp op1;
    private SaExp op2;

    public SaExpInf(SaExp op1, SaExp op2){
	this.op1 = op1;
	this.op2 = op2;
    }

    public SaExp getOp1(){return this.op1;}
    public SaExp getOp2(){return this.op2;}
    
    public <T> T accept(SaVisitor <T> visitor) {
        return visitor.visit(this);
    }
}
