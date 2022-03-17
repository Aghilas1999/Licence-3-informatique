package sa;
import ts.*;

public class SaVarSimple implements SaVar{
    public String nom;
    public TsItemVar tsItem;

    public SaVarSimple(String nom){
	this.nom = nom;
	this.tsItem = null;
    }

    public String getNom(){return this.nom;}
    
    public <T> T accept(SaVisitor <T> visitor) {
        return visitor.visit(this);
    }

}
