package sa;

public class SaInstBloc implements SaInst{
    private SaLInst val;

    public SaInstBloc(SaLInst val){
	this.val = val;
    }

    public SaLInst getVal(){return this.val;}
    public <T> T accept(SaVisitor <T> visitor) {
        return visitor.visit(this);
    }

}
