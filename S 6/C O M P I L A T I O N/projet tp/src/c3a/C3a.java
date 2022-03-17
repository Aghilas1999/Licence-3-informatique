package c3a;
import java.util.*;
import java.io.*;
import ts.*;
import sa.*;

    
public class C3a{
    public List<C3aInst> listeInst;
    // compteur des temporaires, pour générer des noms uniques
    private int tempCounter;
    // étiquette de la prochaine instruction, on retarde l'ajout de l'étiquette
    private C3aLabel nextLabel;
    private int labelCounter;
    public C3aConstant True;
    public C3aConstant False;

    public C3a(){
	this.listeInst = new ArrayList<C3aInst>();
	this.labelCounter = 0;
	this.tempCounter = 0;
	this.nextLabel = null;
	this.True = new C3aConstant(1); // constantes utilisées partout
	this.False = new C3aConstant(0); // constantes utilisées partout
    }
    
    public int getTempCounter(){return this.tempCounter;}
    public void setTempCounter(int value){this.tempCounter = value;}

    public void ajouteInst(C3aInst inst){
	if(this.nextLabel != null){
	    inst.setLabel(this.nextLabel);
	    this.nextLabel = null;
	}
	this.listeInst.add(inst);
    }
    
    public C3aLabel newAutoLabel(){
	return new C3aLabel(this.labelCounter++);
    }

    public C3aTemp newTemp(){
	return new C3aTemp(this.tempCounter++);
    }

    public void addLabelToNextInst(C3aLabel label){
	if(this.nextLabel != null){
      label.affect(this.nextLabel);
	    System.err.println("WARNING : Étiquette précédente ignorée " + this.nextLabel.getNumber());
	}
	this.nextLabel = label;
	//	if(etiquette->oper_type == O_ETIQUETTE){
	//   etiquette->u.oper_etiquette.ligne = c3a->next;
	//}
	//if(etiquette->oper_type == O_FCT){
	//   etiquette->u.oper_fct->adresse = c3a->next;
	//}
    }

    public void affiche(String baseFileName){
	String fileName;
	PrintStream out = System.out;

	if (baseFileName != null){
	    try {
		baseFileName = baseFileName;
		fileName = baseFileName + ".c3a";
		out = new PrintStream(fileName);
	    }
	    
	    catch (IOException e) {
		System.err.println("Error: " + e.getMessage());
	    }
	}
    	Iterator<C3aInst> iter = this.listeInst.iterator();
    	while(iter.hasNext()){
    	    out.println(iter.next());
    	}
    }
	
}

