package nasm;

public class NasmResw extends NasmPseudoInst {
    
    public NasmResw(NasmLabel label, int nb, String comment){
	this.label = label;
	this.nb = nb;
	this.sizeInBytes = 2;
	this.comment = comment;
    }

    public <T> T accept(NasmVisitor <T> visitor) {
        return visitor.visit(this);
    }
    
    public String toString(){
	return super.formatInst(this.label, "resw", this.nb, this.comment);
    }


}
