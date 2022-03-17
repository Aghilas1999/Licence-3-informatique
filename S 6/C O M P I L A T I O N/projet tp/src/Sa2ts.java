import sa.*;
import ts.*;

public class Sa2ts extends SaDepthFirstVisitor<Void> {
    enum Context {
        LOCAL,
        GLOBAL,
        PARAM
        }
        
        // Initialisation de la table globale.
        private Ts tableGlobale;
        
        // Initialisation de la table locale.
        private Ts tableLocaleCourante;
    
    
        private Context context;
    //Constructeur
    public Sa2ts (SaNode node) {

        tableGlobale = new Ts();
        context = Context.GLOBAL;
        node.accept(this);

        }

    //Récupération de la table globale
    public Ts getTableGlobale(){
        tableGlobale.affiche(System.out);
        return tableGlobale;
    }

    //Récupérer la table active
    private Ts getTableActive() {
        return (tableLocaleCourante == null) ? tableGlobale : tableLocaleCourante;
    }

    //Ajout d'une variable
    public Void visit(SaDecVar node){
        //Récupération de la table active
        Ts tableActive = getTableActive();
        //Vérification existance variable ayant même nom dans la table
        if (tableActive.getVar(node.getNom()) == null)
            //Vérification si la variable est un paramètre
            if (context.equals(Context.PARAM))
                //Ajout de la variable en tant que paramètre
                node.tsItem = tableActive.addParam(node.getNom());
            else
                //Ajout de la variable dans la table locale
                node.tsItem = tableActive.addVar(node.getNom(),1);
        return null;
    }
 //Ajout d'une variable de type tableau (toujours variable globale)
    
 public Void visit(SaDecTab node){
    //Vérification s'il n'existe pas une variable ayant le même nom
    // et que la taille du tableau est supérieure à 1
    if (tableGlobale.getVar(node.getNom()) == null && node.getTaille() > 1)
        //Ajout du tableau dans la table globale
        node.tsItem = tableGlobale.addVar(node.getNom(),node.getTaille());
    return null;
}

  //Ajout d'une fonction
  public Void visit(SaDecFonc node){
    //Vérification que la fonction n'existe pas
    if (tableGlobale.getFct(node.getNom()) == null){
        //Initialisation nouvelle table locale pour la fonction
        tableLocaleCourante = new Ts();
        //Récupération des paramètres de la fonction
        SaLDec params = node.getParametres();
        //Nombre de paramètres
        int nombreParams = (params == null) ? 0 : params.length();
        //Si la fonction contient des paramètres
        if (nombreParams > 0) {
            //Variable de type paramètre
            context= Context.PARAM;
            //Parcours des paramètres
            params.accept(this);
        }
        
        //Parcours des instructions du corps de la fonction
        if (node.getCorps() != null) node.getCorps().accept(this);
        //Ajout de la fonction à la table globale
        node.tsItem = tableGlobale.addFct(node.getNom(),nombreParams,tableLocaleCourante,node);
        //Passage de la table locale à null
        tableLocaleCourante = null;
    } else {
        //Affichage d'un message d'erreur
        System.err.println("Fonction déjà existante.");
    }
    return null;
}

    //Appel de variable
    public Void visit(SaVarSimple node){
        //Définition d'une variable
        TsItemVar variable;
        //Vérification de la portée de la variable
        if (tableLocaleCourante.getVar(node.getNom()) != null) {
            //Récupération de la variable liée
            variable = tableLocaleCourante.getVar(node.getNom());
            //Si variable liée introuvable
            if (variable == null)
                //Vérification si paramètre
                if (context.equals(Context.PARAM))
                    //Affichage message erreur
                    System.err.println("Paramètre introuvable.");
                //Variable locale
                else
                    //Affichage message erreur
                    System.err.println("Variable introuvable.");
            else
                //Récupération des informations de la variable liée
                node.tsItem = variable;
        } else {
            //Récupération de la variable liée dans la table globale
            variable = tableGlobale.getVar(node.getNom());
            //Variable introuvable ?
            if(variable == null)
                //Affichage message erreur
                System.err.println("Variable introuvable.");
            else
                //Récupération des informations de la variable liée
                node.tsItem = variable;
        }
        return null;
    }

   

    //Récupération d'une variable à partir d'un indice dans un tableau
    @Override
    public Void visit(SaVarIndicee node){
        //Récupération variable dans la table globale
        TsItemVar var = tableGlobale.getVar(node.getNom());
        //Si variable introuvable
        if(var == null)
            //Affichage message d'erreur
            System.err.println("Tableau introuvable.");
        else
            //Récupération des informations de la variable liée
            node.tsItem = var;
        return null;
    }

  
    //Appel d'une fonction
    public Void visit(SaAppel node){
        //Récupéraiton de la fonction dans la table globale
        TsItemFct fonction = tableGlobale.getFct(node.getNom());
        //Si la fonction est introuvable
        if(fonction == null)
            //Affichage message d'erreur
            System.err.println("Fonction introuvable.");
        else {
            //Récupération du nombre de paramètres
            int nombreParametres = (node.getArguments() == null) ? 0 : node.getArguments().length();
            //Vérification du nombre de paramètres
            if (nombreParametres == fonction.getNbArgs())
                //Récupération des informations de la fonction liée
                node.tsItem = fonction;
            else
                //Affichage message d'erreur
                System.err.println("Nombre d'arguments invalide fonction.");
        }
        return null;
    }
}