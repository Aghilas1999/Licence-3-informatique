import org.apache.xerces.parsers.*;
import org.w3c.dom.*;
import sa.*;
import ts.*;


public class SaVM {
    public static String getType(Node node){
	return ((Element)node).getAttribute("type");
    }

    public static String getNom(Node node){
        return ((Element)node).getAttribute("nom");
    }

    public static String getTaille(Node node){
        return ((Element)node).getAttribute("taille");
    }
    
    public static String getVal(Node node){
        return ((Element)node).getAttribute("val");
    }
    
    public static SaProg processSaProg(Node node){
	SaLDec variables = null;
	SaLDec fonctions = null;

	for(Node child = node.getFirstChild(); child != null; child = child.getNextSibling()){
	    if(child.getNodeName().equals("variables"))
		variables = processSaLDec(child);
	    else if(child.getNodeName().equals("fonctions"))
		fonctions = processSaLDec(child);
	}
	return new SaProg(variables, fonctions);
    }

    /* les listes */
    
    public static SaLDec processSaLDec(Node node){
	SaDec tete = null;
	SaLDec queue = null;
	
	for(Node child = node.getFirstChild(); child != null; child = child.getNextSibling()){
	    if(child.getNodeName().equals("tete"))
		tete = processSaDec(child);
	    else if(child.getNodeName().equals("queue"))
		queue = processSaLDec(child);
	}
	return new SaLDec(tete, queue);
    }

    public static SaLExp processSaLExp(Node node){
	SaExp tete = null;
	SaLExp queue = null;
	
	for(Node child = node.getFirstChild(); child != null; child = child.getNextSibling()){
	    if(child.getNodeName().equals("tete"))
		tete = processSaExp(child);
	    else if(child.getNodeName().equals("queue"))
		queue = processSaLExp(child);
	}
	return new SaLExp(tete, queue);
    }

    public static SaLInst processSaLInst(Node node){
	SaInst tete = null;
	SaLInst queue = null;
	for(Node child = node.getFirstChild(); child != null; child = child.getNextSibling()){
	    if(child.getNodeName().equals("tete"))
		tete = processSaInst(child);
	    else if(child.getNodeName().equals("queue"))
		queue = processSaLInst(child);
	}
	return new SaLInst(tete, queue);
    }
    

    /* declarations */
    
    public static SaDec processSaDec(Node node){

	String decType = getType(node);
	if(decType.equals("SaDecFonc"))
	    return processSaDecFonc(node);
	if(decType.equals("SaDecVar"))
	    return processSaDecVar(node);
	if(decType.equals("SaDecTab"))
	    return processSaDecTab(node);
	return null;
    }
    
    public static SaDecVar processSaDecVar(Node node){
	return new SaDecVar(getNom(node));
    }
    
    public static SaDecTab processSaDecTab(Node node){
	return new SaDecTab(getNom(node),Integer.parseInt(getTaille(node)));
    }

    public static SaDecFonc processSaDecFonc(Node node){
	String nom = getNom(node);
	SaLDec parametres = null;
	SaLDec variables = null;
	SaInst corps = null;
	
	for(Node child = node.getFirstChild(); child != null; child = child.getNextSibling()){
	    if(child.getNodeName().equals("parametres"))
		parametres = processSaLDec(child);
	    else if(child.getNodeName().equals("variables"))
		variables = processSaLDec(child);
	    else if(child.getNodeName().equals("corps"))
		corps = processSaInst(child);
	}
	return new SaDecFonc(nom, parametres, variables, corps);
    }

    /* instructions */

    
    public static SaInst processSaInst(Node node){

	String instType = getType(node);
	if(instType.equals("SaInstAffect"))
	    return processSaInstAffect(node);
	if(instType.equals("SaInstTantQue"))
	    return processSaInstTantQue(node);
	if(instType.equals("SaInstBloc"))
	    return processSaInstBloc(node);
	if(instType.equals("SaInstEcriture"))
	    return processSaInstEcriture(node);
	if(instType.equals("SaInstRetour"))
	    return processSaInstRetour(node);
	if(instType.equals("SaInstSi"))
	    return processSaInstSi(node);
	if(instType.equals("SaAppel"))
	    return processSaAppel(node);
	return null;
    }

    public static SaInstAffect processSaInstAffect(Node node){
	SaVar lhs = null;
	SaExp rhs = null;
	for(Node child = node.getFirstChild(); child != null; child = child.getNextSibling()){
	    if(child.getNodeName().equals("lhs"))
		lhs = processSaVar(child);
	    else if(child.getNodeName().equals("rhs"))
		rhs = processSaExp(child);
	}
	return new SaInstAffect(lhs, rhs);
    }  


    public static SaInstTantQue processSaInstTantQue(Node node){
	SaExp test = null;
	SaInst faire = null;
	
	for(Node child = node.getFirstChild(); child != null; child = child.getNextSibling()){
	    if(child.getNodeName().equals("test"))
		test = processSaExp(child);
	    else if(child.getNodeName().equals("faire"))
		faire = processSaInst(child);
	}
	return new SaInstTantQue(test, faire);
    }   
    
    public static SaInstBloc processSaInstBloc(Node node){
	SaLInst val = null;
	for(Node child = node.getFirstChild(); child != null; child = child.getNextSibling()){
	    if(child.getNodeName().equals("val"))
		val = processSaLInst(child);
	}
	return new SaInstBloc(val);
    }      


    public static SaInstEcriture processSaInstEcriture(Node node){
	SaExp arg = null;
	for(Node child = node.getFirstChild(); child != null; child = child.getNextSibling()){
	    if(child.getNodeName().equals("arg"))
		arg = processSaExp(child);
	}
	return new SaInstEcriture(arg);
    }
    
    public static SaInstRetour processSaInstRetour(Node node){
	SaExp val = null;

	for(Node child = node.getFirstChild(); child != null; child = child.getNextSibling()){
	    if(child.getNodeName().equals("val"))
		val = processSaExp(child);
	}
	return new SaInstRetour(val);
    }

    public static SaInstSi processSaInstSi(Node node){
	SaExp test = null;
	SaInst alors = null;
	SaInst sinon = null;
	
	for(Node child = node.getFirstChild(); child != null; child = child.getNextSibling()){
	    if(child.getNodeName().equals("test"))
		test = processSaExp(child);
	    else if(child.getNodeName().equals("alors"))
		alors = processSaInst(child);
	    else if(child.getNodeName().equals("sinon"))
		sinon = processSaInst(child);
	}
	return new SaInstSi(test, alors, sinon);
    }
    
    /* appel de fonction */
    
    public static SaAppel processSaAppel(Node node){
	String nom = getNom(node);
	SaLExp arguments = null;

	for(Node child = node.getFirstChild(); child != null; child = child.getNextSibling()){
	    if(child.getNodeName().equals("arguments"))
		arguments = processSaLExp(child);
	}
	return new SaAppel(nom, arguments);
    }

    /* expressions */

        
    public static SaExp processSaExp(Node node){

	String expType = getType(node);
	if(expType.equals("SaExpAdd"))
	    return processSaExpAdd(node);
	if(expType.equals("SaExpSub"))
	    return processSaExpSub(node);
	if(expType.equals("SaExpMult"))
	    return processSaExpMult(node);
	if(expType.equals("SaExpDiv"))
	    return processSaExpDiv(node);

	if(expType.equals("SaExpAnd"))
	    return processSaExpAnd(node);
	if(expType.equals("SaExpOr"))
	    return processSaExpOr(node);
	if(expType.equals("SaExpNot"))
	    return processSaExpNot(node);

	
	if(expType.equals("SaExpInf"))
	    return processSaExpInf(node);
	if(expType.equals("SaExpEqual"))
	    return processSaExpEqual(node);

	if(expType.equals("SaExpVar"))
	    return processSaExpVar(node);
	if(expType.equals("SaExpInt"))
	    return processSaExpInt(node);
	if(expType.equals("SaExpLire"))
	    return processSaExpLire(node);
	if(expType.equals("SaExpAppel"))
	    return processSaExpAppel(node);

	return null;
    }



    public static SaExpInf processSaExpInf(Node node){
	SaExp op1 = null;
	SaExp op2 = null;
	for(Node child = node.getFirstChild(); child != null; child = child.getNextSibling()){
	    if(child.getNodeName().equals("op1"))
		op1 = processSaExp(child);
	    else if(child.getNodeName().equals("op2"))
		op2 = processSaExp(child);
	}
	return new SaExpInf(op1, op2);
    }
    
    public static SaExpVar processSaExpVar(Node node){
	SaVar var = null;

	for(Node child = node.getFirstChild(); child != null; child = child.getNextSibling()){
	    if(child.getNodeName().equals("var"))
		var = processSaVar(child);
	}
	return new SaExpVar(var);
    }      
    
    public static SaExpInt processSaExpInt(Node node){
	return new SaExpInt(Integer.parseInt(getVal(node)));
    }
    
    public static SaExpAdd processSaExpAdd(Node node){
	SaExp op1 = null;
	SaExp op2 = null;
	for(Node child = node.getFirstChild(); child != null; child = child.getNextSibling()){
	    if(child.getNodeName().equals("op1"))
		op1 = processSaExp(child);
	    else if(child.getNodeName().equals("op2"))
		op2 = processSaExp(child);
	}
	return new SaExpAdd(op1, op2);
    }
    
    
    public static SaExpAnd processSaExpAnd(Node node){
	SaExp op1 = null;
	SaExp op2 = null;
	for(Node child = node.getFirstChild(); child != null; child = child.getNextSibling()){
	    if(child.getNodeName().equals("op1"))
		op1 = processSaExp(child);
	    else if(child.getNodeName().equals("op2"))
		op2 = processSaExp(child);
	}
	return new SaExpAnd(op1, op2);
    }

    public static SaExpLire processSaExpLire(Node node){
	return new SaExpLire();
    }     

    public static SaExpAppel processSaExpAppel(Node node){
	SaAppel val = null;

	for(Node child = node.getFirstChild(); child != null; child = child.getNextSibling()){
	    if(child.getNodeName().equals("val"))
		val = processSaAppel(child);
	}
	return new SaExpAppel(val);
    }
    
    public static SaExpNot processSaExpNot(Node node){
	SaExp op1 = null;
	for(Node child = node.getFirstChild(); child != null; child = child.getNextSibling()){
	    if(child.getNodeName().equals("op1"))
		op1 = processSaExp(child);
	}
	return new SaExpNot(op1);
    }
    
    public static SaExpDiv processSaExpDiv(Node node){
	SaExp op1 = null;
	SaExp op2 = null;
	for(Node child = node.getFirstChild(); child != null; child = child.getNextSibling()){
	    if(child.getNodeName().equals("op1"))
		op1 = processSaExp(child);
	    else if(child.getNodeName().equals("op2"))
		op2 = processSaExp(child);
	}
	return new SaExpDiv(op1, op2);
    }
    
    public static SaExpOr processSaExpOr(Node node){
	SaExp op1 = null;
	SaExp op2 = null;
	for(Node child = node.getFirstChild(); child != null; child = child.getNextSibling()){
	    if(child.getNodeName().equals("op1"))
		op1 = processSaExp(child);
	    else if(child.getNodeName().equals("op2"))
		op2 = processSaExp(child);
	}
	return new SaExpOr(op1, op2);
    }
    
    public static SaExpMult processSaExpMult(Node node){
	SaExp op1 = null;
	SaExp op2 = null;
	for(Node child = node.getFirstChild(); child != null; child = child.getNextSibling()){
	    if(child.getNodeName().equals("op1"))
		op1 = processSaExp(child);
	    else if(child.getNodeName().equals("op2"))
		op2 = processSaExp(child);
	}
	return new SaExpMult(op1, op2);
    }
    
    public static SaExpEqual processSaExpEqual(Node node){
	SaExp op1 = null;
	SaExp op2 = null;
	for(Node child = node.getFirstChild(); child != null; child = child.getNextSibling()){
	    if(child.getNodeName().equals("op1"))
		op1 = processSaExp(child);
	    else if(child.getNodeName().equals("op2"))
		op2 = processSaExp(child);
	}
	return new SaExpEqual(op1, op2);
    }
    
    public static SaExpSub processSaExpSub(Node node){
	SaExp op1 = null;
	SaExp op2 = null;
	for(Node child = node.getFirstChild(); child != null; child = child.getNextSibling()){
	    if(child.getNodeName().equals("op1"))
		op1 = processSaExp(child);
	    else if(child.getNodeName().equals("op2"))
		op2 = processSaExp(child);
	}
	return new SaExpSub(op1, op2);
    }


    /* acces aux variables */
    
    public static SaVar processSaVar(Node node){
	String varType = getType(node);
	if(varType.equals("SaVarIndicee"))
	    return processSaVarIndicee(node);
	if(varType.equals("SaVarSimple"))
	    return processSaVarSimple(node);
	return null;
    }

    public static SaVarIndicee processSaVarIndicee(Node node){
	String nom = getNom(node);
	SaExp indice = null;
	for(Node child = node.getFirstChild(); child != null; child = child.getNextSibling()){
	    if(child.getNodeName().equals("indice"))
		indice = processSaExp(child);
	}
	return new SaVarIndicee(nom, indice);
    }

    public static SaVarSimple processSaVarSimple(Node node){
	return new SaVarSimple(getNom(node));
    }
    
  public static void main(String[] args) {
    Document document = null;
    DOMParser parser = null;
    int verboseLevel = 0;
    String saFileName = null;

    try {
	for (int i = 0; i < args.length; i++) {
	    if(args[i].equals("-v"))
		verboseLevel = Integer.parseInt(args[++i]);
	    else if(args[i].equals("-sa"))
		saFileName = args[++i];
	}
	if(saFileName == null){
	    System.out.println("java -cp \".:../xerces-2_12_1/*\" saVM -sa saFile -v verboseLevel");
	    System.exit(1);
	}
	
	
      parser = new DOMParser();
      if(verboseLevel > 0)
	  System.err.println("parsing xml document");
      parser.parse(saFileName);
      document = parser.getDocument();
      Node rootNode=document.getDocumentElement();
      if(verboseLevel > 0)
	  System.err.println("building sa tree");
      
      SaProg prog = processSaProg(rootNode);
      if(verboseLevel > 0)
	  new Sa2Xml(prog, null);
      
      if(verboseLevel > 0)
	  System.err.println("building symbol table");
      Ts tableGlobale = new Sa2ts(prog).getTableGlobale();

      if(verboseLevel > 0)
	  System.err.println("evaluating sa tree");
      SaEval saEval = new SaEval(prog, tableGlobale);
      saEval.affiche(null);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
