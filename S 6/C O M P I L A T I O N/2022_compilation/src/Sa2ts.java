/**
 * L’objectif de ce TP est de constuire pour un programme donné, la table des symboles lui correspondant et de réaliser une série de vérifications sémantiques, qui utilisent cette table. La table est
remplie et consultée lors d’un parcours descendant de l’arbre abstrait qui a été constuit lors du
TP précédent. A l’issue du parcours de l’arbre abstrait, plusieurs tables des symboles ont en fait
été créées. Une table globale, qui contient les variables globales et les fonctions et autant de tables
locales que le programme comporte de fonctions
*
*/

import sa.*;
import ts.Ts;
import ts.TsItemFct;
import ts.TsItemVar;

public class Sa2ts extends SaDepthFirstVisitor<Void> {

    Ts TableGlobale = new Ts();
    Ts TableLocale;
    int param = 0;


    public Sa2ts(SaNode saNode) {
        TableGlobale = new Ts();
        TableLocale = this.TableGlobale;
        saNode.accept(this);
    }

    public Ts getTableGlobale() {
        return TableGlobale;
    }

    /********************************************************************************** */
    public Void visit(SaDecVar node) {

        if(TableLocale.getVar(node.getNom()) == null){
        
            if (node.tsItem == null){
                TableLocale.addVar(node.getNom(),1);
            } else {
        
                if (node.tsItem.isParam){
                    TableLocale.addParam(node.getNom());
                } else {
                    TableLocale.addVar(node.getNom(),1);
                }
            }
        } else {
        
            System.err.println("La variable/le paramètre "+node.getNom()+" existe déjà !");
        }
        
        return null;
    }
    
    /************************************************************************************ */
    public Void visit(SaDecTab node) {
        
        if(TableGlobale.getVar(node.getNom()) != null)
            throw new RuntimeException("Erreur");
        node.tsItem = TableGlobale.addVar(node.getNom(), node.getTaille());
        
        return null;
    }

    /****************************************************************************** */
    public Void visit(SaDecFonc node) {

        Ts newTable = new Ts();
        TableLocale = newTable;

        String nom = node.getNom();
       
        int args;

       
        if(node.getParametres() == null)
            args = 0;
       
        else

            args = node.getParametres().length();
       
            if(TableGlobale.getFct(nom) != null)
            throw new RuntimeException(" erreur");
       
            if(node.getParametres() != null)node.getParametres().accept(this);
            if(node.getCorps() != null) node.getCorps().accept(this);
            if(node.getVariable() != null) node.getVariable().accept(this);
            
        TableLocale = TableGlobale.getTableLocale(node.getNom());

        node.tsItem = TableGlobale.addFct(nom, args, newTable, node);
       
        return null;
    }

    /******************************************************************************* */
    public Void visit(SaVarSimple node) {

       boolean isGlobal = false;
       
       if(TableLocale.getVar(node.getNom()) != null){

           if(TableGlobale.getVar(node.getNom()) != null){
               throw new RuntimeException("erreur");
           }else {
               isGlobal = true;
           }
       }

       TsItemVar tsItemVar;

       if(isGlobal){
           tsItemVar = TableGlobale.getVar(node.getNom());
       }else {
           tsItemVar = TableLocale.getVar(node.getNom());
       }
       node.tsItem = tsItemVar;

       return null;

    }
    /********************************************************************* */
    public Void visit(SaVarIndicee node){

        TsItemVar var = TableGlobale.getVar(node.getNom());
     
        if(var == null)
            System.err.println("introuvable");
        else
            node.tsItem = var;
        return null;
    }

    /***************************************************************************** */
        public Void visit(SaAppel node) {
            String nom = node.getNom();
            int nbArgs;
            if(node.getArguments() == null)
                nbArgs = 0;
            else
                nbArgs = node.getArguments().length();
            TsItemFct tsItemFct = TableGlobale.getFct(nom);
            
            if(TableGlobale.getFct(nom) == null)
                throw new RuntimeException("Erreur");
    
            if(nbArgs != tsItemFct.nbArgs)
                throw new RuntimeException("les args passés");
            node.tsItem = tsItemFct;
           
            return null;
        }

}