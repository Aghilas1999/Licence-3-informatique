package sa;
import ts.*;

public class SaVarIndicee implements SaVar{
    private String nom;
    private SaExp indice;
    public TsItemVar tsItem;
    
    public SaVarIndicee(String nom, SaExp indice){
	this.nom = nom;
	this.indice = indice;
	this.tsItem = null;
    }

    public String getNom(){return this.nom;}
    public SaExp getIndice(){return this.indice;}
    
    public <T> T accept(SaVisitor <T> visitor) {
        return visitor.visit(this);
    }

}
