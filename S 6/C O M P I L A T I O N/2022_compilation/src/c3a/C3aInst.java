package c3a;

public class C3aInst{
    public C3aLabel label ;   // étiquette optionnelle, si la ligne est cible d'un saut
    public String comment;    // commentaire explicatif - utile pour le débogage

    public void setLabel(C3aLabel label){
	this.label = label;
    }

    public <T> T accept(C3aVisitor <T> visitor) {
        return visitor.visit(this);
    }

}

