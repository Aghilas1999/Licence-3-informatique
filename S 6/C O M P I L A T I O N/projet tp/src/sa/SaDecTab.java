package sa;
import ts.*;

public class SaDecTab implements SaDec{
    private String nom;
    private int taille;
    public TsItemVar tsItem;

    public SaDecTab(String nom, int taille){
	this.nom = nom;
	this.taille = taille;
	this.tsItem = null;
    }

    public String getNom(){return this.nom;}
    public int getTaille(){return this.taille;}

    public <T> T accept(SaVisitor <T> visitor) {
        return visitor.visit(this);
    }

}
