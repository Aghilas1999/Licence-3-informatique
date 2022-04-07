package sa;

public class SaInstEcriture implements SaInst{
    private SaExp arg;

    public SaInstEcriture(SaExp arg){
	this.arg = arg;
    }

    public SaExp getArg(){return this.arg;}

    public <T> T accept(SaVisitor <T> visitor) {
        return visitor.visit(this);
    }

}
