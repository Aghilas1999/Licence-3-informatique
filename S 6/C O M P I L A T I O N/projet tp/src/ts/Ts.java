package ts;
import java.io.*;
import java.util.*;
import sa.*;

public class Ts
{
    public Map< String, TsItemFct> fonctions;
    public Map< String, TsItemVar> variables;
    private int adrVarCourante;
    private int adrArgCourant;

    public int getAdrVarCourante(){return adrVarCourante;}

    // les adresses sont en octets, les entiers sont stockés sur 4 octets, d'où cette division par 4
    // ce n'est pas très beau, il faudrait changer ça
    public int nbVar(){return adrVarCourante / 4;}
    public int nbArg(){return adrArgCourant / 4;}
    
    
    public Ts(){
	this.fonctions = new HashMap< String, TsItemFct>();
	this.variables = new HashMap< String, TsItemVar>();
	this.adrVarCourante = 0;
	this.adrArgCourant = 0;
    }
    
    public TsItemVar addVar(String identif, int taille)
    {
	TsItemVar item = new TsItemVar(identif, taille);
	item.portee = this;
	item.adresse = this.adrVarCourante;
	this.adrVarCourante += item.getTaille();
	this.variables.put(identif, item);
	return item;
    }

    public TsItemVar addParam(String identif)
    {
	//	TsItemVar item = new TsItemVar(identif, 1);
	// on compte en octets, un entier est codé sur 4 octets
	TsItemVar item = new TsItemVar(identif, 4);
	item.portee = this;
	item.adresse = this.adrArgCourant;
	item.isParam = true;
	this.adrArgCourant += item.getTaille();
	this.variables.put(identif, item);
	return item;
    }

    public TsItemFct addFct(String identif, int nbArgs, Ts table, SaDecFonc saDecFonc)
    {
	TsItemFct item = new TsItemFct(identif, nbArgs, table, saDecFonc);
	this.fonctions.put(identif, item);
	return item;
    }

    public TsItemVar getVar(String identif)
    {
	return variables.get(identif);
    }
    
    public TsItemFct getFct(String identif)
    {
	return fonctions.get(identif);
    }
    
    public Ts getTableLocale(String identif){
	TsItemFct item = this.fonctions.get(identif);
	if(item == null)
	    return null;
	else
	    return item.getTable();
    }
    
	 
    public void affiche(PrintStream out){
	ArrayList< Map.Entry< String, TsItemVar> > stVar = new ArrayList<Map.Entry< String, TsItemVar>>(this.variables.entrySet());
	Collections.sort(stVar, new Comparator<Map.Entry< String, TsItemVar>>() {
		@Override
		public int compare(Map.Entry< String, TsItemVar> entry1, Map.Entry< String, TsItemVar> entry2)
		{
		    return entry1.getKey().compareTo(entry2.getKey());
		}
	    });
	for (Map.Entry< String, TsItemVar> me:stVar){ 
	    out.println(me.getValue());
	    //	    out.println(me.getKey() + ":\t" + me.getValue());
	}
	
	ArrayList< Map.Entry< String, TsItemFct> > stFct = new ArrayList<Map.Entry< String, TsItemFct>>(this.fonctions.entrySet());
	Collections.sort(stFct, new Comparator<Map.Entry< String, TsItemFct>>() {
		@Override
		public int compare(Map.Entry< String, TsItemFct> entry1, Map.Entry< String, TsItemFct> entry2)
		{
		    return entry1.getKey().compareTo(entry2.getKey());
		}
	    });
	for (Map.Entry< String, TsItemFct> me:stFct){ 
	    out.println(me.getValue());
	    //	    out.println(me.getKey() + ":\t" + me.getValue());
	}
    }
	
    public void afficheTablesLocales(PrintStream out){
	ArrayList< Map.Entry< String, TsItemFct> > st = new ArrayList<Map.Entry< String, TsItemFct>>(this.fonctions.entrySet());
	Collections.sort(st, new Comparator<Map.Entry< String, TsItemFct>>() {
		@Override
		public int compare(Map.Entry< String, TsItemFct> entry1, Map.Entry< String, TsItemFct> entry2)
		{
		    return entry1.getKey().compareTo(entry2.getKey());
		}
	    });
	for (Map.Entry< String, TsItemFct> me:st){
	    if(me.getValue().getTable() != null){
		out.println("TABLE LOCALE : " + me.getKey());
		me.getValue().getTable().affiche(out);
	    }
	}
    }
    
    public void afficheTout(String baseFileName){
	String fileName;
	PrintStream out = System.out;
	
	if (baseFileName != null){
	    try {
		baseFileName = baseFileName;
		fileName = baseFileName + ".ts";
		out = new PrintStream(fileName);
	    }
	    
	    catch (IOException e) {
		System.err.println("Error: " + e.getMessage());
	    }
	}
       	out.println("TABLE GLOBALE");
	this.affiche(out);
	this.afficheTablesLocales(out);
    }

}
