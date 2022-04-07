package sa;

public class SaExpOptTer implements SaExp{
    private SaExp test;
    private SaExp oui;
    private SaExp non;


    public SaExpOptTer(SaExp test, SaExp oui, SaExp non){
	this.test = test;
	this.oui = oui;
	this.non = non;
    }

    public SaExp getTest(){return this.test;}
    public SaExp getOui(){return this.oui;}
    public SaExp getNon(){return this.non;}
    
    public <T> T accept(SaVisitor <T> visitor) {
        return visitor.visit(this);
    }

}
