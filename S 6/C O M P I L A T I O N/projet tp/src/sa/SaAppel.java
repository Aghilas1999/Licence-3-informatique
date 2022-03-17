package sa;
import ts.*;


public class SaAppel implements SaExp, SaInst{
    private String nom;
    private SaLExp arguments;
    public TsItemFct tsItem;

    public SaAppel(String nom, SaLExp arguments){
	this.nom = nom;
	this.arguments = arguments;
	this.tsItem = null;
    }

    public String getNom(){return this.nom;}
    public SaLExp getArguments(){return this.arguments;}
    
    public <T> T accept(SaVisitor <T> visitor) {
        return visitor.visit(this);
    }
}
