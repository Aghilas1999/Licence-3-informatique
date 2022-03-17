package sa;

public class SaLInst implements SaNode{
    private SaInst tete;
    private SaLInst queue;

    public SaLInst(SaInst tete, SaLInst queue){
	this.tete = tete;
	this.queue = queue;
    }

    public SaInst getTete(){return this.tete;}
    public SaLInst getQueue(){return this.queue;}
    
    public <T> T accept(SaVisitor <T> visitor) {
        return visitor.visit(this);
    }

}
