package nasm;

public class NasmResq extends NasmPseudoInst {
    
    public NasmResq(NasmLabel label, int nb, String comment){
	this.label = label;
	this.nb = nb;
	this.sizeInBytes = 8;
	this.comment = comment;
    }

    public <T> T accept(NasmVisitor <T> visitor) {
        return visitor.visit(this);
    }
    
    public String toString(){
	return super.formatInst(this.label, "resq", this.nb, this.comment);
    }


}
