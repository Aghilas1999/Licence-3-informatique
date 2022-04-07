package sa;

public class SaProg implements SaNode{
    private SaLDec variables;
    private SaLDec fonctions;

    public SaProg(SaLDec variables, SaLDec fonctions){
	this.variables = variables;
	this.fonctions = fonctions;
    }

    public SaLDec getVariables(){return this.variables;}
    public SaLDec getFonctions(){return this.fonctions;}
    
    public <T> T accept(SaVisitor <T> visitor) {
        return visitor.visit(this);
    }

}
