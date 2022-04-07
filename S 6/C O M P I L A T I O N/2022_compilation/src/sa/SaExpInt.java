package sa;

public class SaExpInt implements SaExp{
    private int val;

    public SaExpInt(int val){
	this.val = val;
    }

    public int getVal(){
	return this.val;
    }
    
    public <T> T accept(SaVisitor <T> visitor) {
        return visitor.visit(this);
    }
}
