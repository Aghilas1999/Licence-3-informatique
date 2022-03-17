package ts;
import sa.*;


public class TsItemFct {
    public String identif;
    public int nbArgs;
    public Ts table;
    public SaDecFonc saDecFonc;
	
    public TsItemFct(String identif, int nbArgs, Ts table, SaDecFonc saDecFonc){
	this.identif = identif;
	this.nbArgs = nbArgs;
	this.table = table;
	this.saDecFonc = saDecFonc;
    }

    public int getNbArgs(){return this.nbArgs;}
    public Ts getTable(){return this.table;}
    public String getIdentif(){return this.identif;}
    public String toString(){
    	return this.identif +  "\tFCT\t" + this.nbArgs;
    }

}

