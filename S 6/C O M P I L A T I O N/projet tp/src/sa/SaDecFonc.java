package sa;
import ts.*;

public class SaDecFonc implements SaDec{
    private String nom;
    private SaLDec parametres;
    private SaLDec variables;
    private SaInst corps;
    public TsItemFct tsItem;

    public SaDecFonc(String nom, SaLDec parametres, SaLDec variables, SaInst corps){
	this.nom = nom;
	this.parametres = parametres;
	this.variables = variables;
	this.corps = corps;
	this.tsItem = null;
    }

    public String getNom(){return this.nom;}
    public SaLDec getParametres(){return this.parametres;}
    public SaLDec getVariable(){return this.variables;}
    public SaInst getCorps(){return this.corps;}
    
    public <T> T accept(SaVisitor <T> visitor) {
        return visitor.visit(this);
    }
}
