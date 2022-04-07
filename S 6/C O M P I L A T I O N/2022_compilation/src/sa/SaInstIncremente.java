package sa;

public class SaInstIncremente implements SaInst{
    private SaVar lhs;
    private SaExp rhs;

    public SaInstIncremente(SaVar lhs, SaExp rhs){
	this.lhs = lhs;
	this.rhs = rhs;
    }
    public SaVar getLhs(){return this.lhs;}
    public SaExp getRhs(){return this.rhs;}
    
    public <T> T accept(SaVisitor <T> visitor) {
        return visitor.visit(this);
    }

}
