package sa;

public class SaInstSi implements SaInst{
    private SaExp test;
    private SaInst alors;
    private SaInst sinon;

    public SaInstSi(SaExp test, SaInst alors, SaInst sinon){
	this.test = test;
	this.alors = alors;
	this.sinon = sinon;
    }

    public SaExp getTest(){return this.test;}
    public SaInst getAlors(){return this.alors;}
    public SaInst getSinon(){return this.sinon;}
    
    public <T> T accept(SaVisitor <T> visitor) {
        return visitor.visit(this);
    }

}
