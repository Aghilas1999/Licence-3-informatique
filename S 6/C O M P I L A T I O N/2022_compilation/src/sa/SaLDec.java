package sa;

public class SaLDec implements SaNode{
    private SaDec tete;
    private SaLDec queue;

    public SaLDec(SaDec tete, SaLDec queue){
	this.tete = tete;
	this.queue = queue;
    }

    public SaDec getTete(){return this.tete;}
    public SaLDec getQueue(){return this.queue;}

    public int length(){
	if(this.queue == null) return 1;
	return 1 + this.queue.length() ;
    }
    
    public <T> T accept(SaVisitor <T> visitor) {
        return visitor.visit(this);
    }

}
