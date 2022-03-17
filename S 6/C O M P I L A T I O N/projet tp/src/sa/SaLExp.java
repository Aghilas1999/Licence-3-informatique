package sa;

public class SaLExp implements SaNode{
    private SaExp tete;
    private SaLExp queue;

    public SaLExp(SaExp tete, SaLExp queue){
	this.tete = tete;
	this.queue = queue;
    }

    public SaExp getTete(){return this.tete;}
    public SaLExp getQueue(){return this.queue;}

    public int length(){
	if(this.queue == null) return 1;
	return 1 + this.queue.length() ;
    }
    

    
    public <T> T accept(SaVisitor <T> visitor) {
        return visitor.visit(this);
    }

}
