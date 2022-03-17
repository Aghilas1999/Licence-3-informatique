package ts;

public class TsItemVar {
    public Ts portee;
    public int adresse;
    public String identif;
    public int taille;
    public boolean isParam;

    public TsItemVar(String identif, int taille){
	this.identif = identif;
	this.taille = taille;
	this.adresse = 0;
	this.portee = null;
	this.isParam = false;
    }

    public int getTaille(){return this.taille;}
    public int getAdresse(){return this.adresse;}
    public String getIdentif(){return this.identif;}
    public Ts getPortee(){return this.portee;}
    
    public String toString(){
	if(this.isParam)
	    return this.identif + "\tPARAM\t" + this.taille + "\t" + this.adresse;
	else
	    return this.identif + "\tVAR  \t" + this.taille + "\t" + this.adresse;
    }
    
}

