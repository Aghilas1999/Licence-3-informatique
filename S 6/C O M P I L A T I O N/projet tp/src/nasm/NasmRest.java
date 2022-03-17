package nasm;

public class NasmRest extends NasmPseudoInst {
    
    public NasmRest(NasmLabel label, int nb, String comment){
	this.label = label;
	this.nb = nb;
	this.sizeInBytes = 10;
	this.comment = comment;
    }

    public <T> T accept(NasmVisitor <T> visitor) {
        return visitor.visit(this);
    }
    
    public String toString(){
	return super.formatInst(this.label, "rest", this.nb, this.comment);
    }


}
