/**
 * L’objectif de ce TP est de construire un arbre abstrait correspondant au programme en langage
source. Cet arbre abstrait sera utilisé lors des étapes suivantes de la compilation, en particulier
pour la construction des tables de symboles et de la production du code trois adresses. 
*
*
*/

import sa.*;
import sc.analysis.DepthFirstAdapter;
import sc.node.*;

public class Sc2sa extends DepthFirstAdapter {

    private SaNode returnValue;

    public SaNode getRoot() {
        return this.returnValue;
    }

    public void caseADecvarldecfoncProgramme(ADecvarldecfoncProgramme node) {

        SaLDec var, fonc;

        node.getOptdecvar().apply(this);
        var = (SaLDec) this.returnValue;

        node.getListedecfonc().apply(this);
        fonc = (SaLDec) this.returnValue;

        this.returnValue = new SaProg(var, fonc);
    }

    public void caseALdecfoncProgramme(ALdecfoncProgramme node) {

        SaLDec fonc;

        node.getListedecfonc().apply(this);
        fonc = (SaLDec) this.returnValue;

        this.returnValue = new SaProg(null, fonc);
    }

    public void caseAOptdecvar(AOptdecvar node) {

        SaLDec liste;

        node.getListedecvar().apply(this);
        liste = (SaLDec) this.returnValue;

        this.returnValue = liste;
    }

    public void caseADecvarldecvarListedecvar(ADecvarldecvarListedecvar node) {

        SaDec dec;
        SaLDec Ldec;

        node.getDecvar().apply(this);
        dec = (SaDec) this.returnValue;

        node.getListedecvarbis().apply(this);
        Ldec = (SaLDec) this.returnValue;

        this.returnValue = new SaLDec(dec, Ldec);
    }

    public void caseADecvarListedecvar(ADecvarListedecvar node) {

        SaDec dec;

        node.getDecvar().apply(this);
        dec = (SaDec) this.returnValue;

        this.returnValue = new SaLDec(dec, null);
    }

    public void caseADecvarldecvarListedecvarbis(ADecvarldecvarListedecvarbis node) {

        SaDec dec;
        SaLDec Ldec;

        node.getDecvar().apply(this);
        dec = (SaDec) this.returnValue;

        node.getListedecvarbis().apply(this);
        Ldec = (SaLDec) this.returnValue;

        this.returnValue = new SaLDec(dec, Ldec);
    }

    public void caseADecvarListedecvarbis(ADecvarListedecvarbis node) {

        SaDec dec;

        node.getDecvar().apply(this);
        dec = (SaDec) this.returnValue;

        this.returnValue = new SaLDec(dec, null);
    }

    public void caseADecvarentierDecvar(ADecvarentierDecvar node) {
        String nom;

        node.getIdentif().apply(this);
        nom = node.getIdentif().getText();

        this.returnValue = new SaDecVar(nom);
    }

    public void caseADecvartableauDecvar(ADecvartableauDecvar node) {

        String nom;

        int taille;
        node.getIdentif().apply(this);
        nom = node.getIdentif().getText();

        node.getNombre().apply(this);
        taille = Integer.parseInt(node.getNombre().getText());

        this.returnValue = new SaDecTab(nom, taille);
    }

    public void caseALdecfoncrecListedecfonc(ALdecfoncrecListedecfonc node) {

        SaDec dec;
        SaLDec Ldec;

        node.getDecfonc().apply(this);
        dec = (SaDec) this.returnValue;

        node.getListedecfonc().apply(this);
        Ldec = (SaLDec) this.returnValue;

        this.returnValue = new SaLDec(dec, Ldec);
    }

    public void caseALdecfoncfinalListedecfonc(ALdecfoncfinalListedecfonc node) {

        this.returnValue = null;
    }

    public void caseADecvarinstrDecfonc(ADecvarinstrDecfonc node) {

        String nom;
        SaLDec para;
        SaLDec var;
        SaInst bloc;

        node.getIdentif().apply(this);
        nom = node.getIdentif().getText();

        node.getListeparam().apply(this);
        para = (SaLDec) this.returnValue;

        node.getOptdecvar().apply(this);
        var = (SaLDec) this.returnValue;

        node.getInstrbloc().apply(this);
        bloc = (SaInstBloc) this.returnValue;

        this.returnValue = new SaDecFonc(nom, para, var, bloc);
    }

    public void caseAInstrDecfonc(AInstrDecfonc node) {

        String nom;
        SaLDec para;
        SaInst bloc;

        node.getIdentif().apply(this);
        nom = node.getIdentif().getText();

        node.getListeparam().apply(this);
        para = (SaLDec) this.returnValue;

        node.getInstrbloc().apply(this);
        bloc = (SaInstBloc) this.returnValue;

        this.returnValue = new SaDecFonc(nom, para, null, bloc);
    }

    public void caseASansparamListeparam(ASansparamListeparam node) {

        this.returnValue = null;
    }

    public void caseAAvecparamListeparam(AAvecparamListeparam node) {

        SaLDec liste;

        node.getListedecvar().apply(this);
        liste = (SaLDec) this.returnValue;

        this.returnValue = liste;
    }

    public void caseAInstraffectInstr(AInstraffectInstr node) {

        SaInstAffect affectation;

        node.getInstraffect().apply(this);
        affectation = (SaInstAffect) this.returnValue;

        this.returnValue = affectation;
    }

    public void caseAInstrblocInstr(AInstrblocInstr node) {

        SaInstBloc bloc;

        node.getInstrbloc().apply(this);
        bloc = (SaInstBloc) this.returnValue;

        this.returnValue = bloc;
    }

    public void caseAInstrsiInstr(AInstrsiInstr node) {

        SaInstSi si;

        node.getInstrsi().apply(this);
        si = (SaInstSi) this.returnValue;

        this.returnValue = si;
    }

    public void caseAInstrtantqueInstr(AInstrtantqueInstr node) {

        SaInstTantQue tantQue;

        node.getInstrtantque().apply(this);
        tantQue = (SaInstTantQue) this.returnValue;

        this.returnValue = tantQue;
    }

    public void caseAInstrappelInstr(AInstrappelInstr node) {

        SaAppel appel;

        node.getInstrappel().apply(this);
        appel = (SaAppel) this.returnValue;

        this.returnValue = appel;
    }

    public void caseAInstrretourInstr(AInstrretourInstr node) {

        SaInstRetour retour;

        node.getInstrretour().apply(this);
        retour = (SaInstRetour) this.returnValue;

        this.returnValue = retour;
    }

    public void caseAInstrecritureInstr(AInstrecritureInstr node) {

        SaInstEcriture ecriture;

        node.getInstrecriture().apply(this);
        ecriture = (SaInstEcriture) this.returnValue;

        this.returnValue = ecriture;
    }

    public void caseAInstrvideInstr(AInstrvideInstr node) {

        this.returnValue = new SaLInst(null, null);
    }

    public void caseAInstraffect(AInstraffect node) {

        SaVar Sav;
        SaExp Sae;

        node.getVar().apply(this);
        Sav = (SaVar) this.returnValue;

        node.getExp().apply(this);
        Sae = (SaExp) this.returnValue;

        this.returnValue = new SaInstAffect(Sav, Sae);
    }

    public void caseAInstrbloc(AInstrbloc node) {

        SaLInst val;

        node.getListeinst().apply(this);
        val = (SaLInst) this.returnValue;

        this.returnValue = new SaInstBloc(val);
    }

    public void caseALinstrecListeinst(ALinstrecListeinst node) {

        SaInst dec;
        SaLInst Ldec;

        node.getInstr().apply(this);
        dec = (SaInst) this.returnValue;

        node.getListeinst().apply(this);
        Ldec = (SaLInst) this.returnValue;

        this.returnValue = new SaLInst(dec, Ldec);
    }

    public void caseALinstfinalListeinst(ALinstfinalListeinst node) {

        this.returnValue = null;
    }

    public void caseAAvecsinonInstrsi(AAvecsinonInstrsi node) {

        SaExp test;
        SaInst alors, sinon;

        node.getExp().apply(this);
        test = (SaExp) this.returnValue;

        node.getInstrbloc().apply(this);
        alors = (SaInstBloc) this.returnValue;

        node.getInstrsinon().apply(this);
        sinon = (SaInstBloc) this.returnValue;

        this.returnValue = new SaInstSi(test, alors, sinon);
    }

    public void caseASanssinonInstrsi(ASanssinonInstrsi node) {

        SaExp test;
        SaInst alors;

        node.getExp().apply(this);
        test = (SaExp) this.returnValue;

        node.getInstrbloc().apply(this);
        alors = (SaInstBloc) this.returnValue;

        this.returnValue = new SaInstSi(test, alors, null);
    }

    public void caseAInstrsinon(AInstrsinon node) {

        SaInst sinon;

        node.getInstrbloc().apply(this);
        sinon = (SaInstBloc) this.returnValue;

        this.returnValue = sinon;
    }

    public void caseAInstrtantque(AInstrtantque node) {

        SaExp test;
        SaInst tantque;

        node.getExp().apply(this);
        test = (SaExp) this.returnValue;

        node.getInstrbloc().apply(this);
        tantque = (SaInst) this.returnValue;

        this.returnValue = new SaInstTantQue(test, tantque);
    }

    public void caseAInstrappel(AInstrappel node) {

        SaInst appel;

        node.getAppelfct().apply(this);
        appel = (SaAppel) this.returnValue;

        this.returnValue = appel;
    }

    public void caseAInstrretour(AInstrretour node) {

        SaExp val;

        node.getExp().apply(this);
        val = (SaExp) this.returnValue;

        this.returnValue = new SaInstRetour(val);
    }

    public void caseAInstrecriture(AInstrecriture node) {

        SaExp arg;

        node.getExp().apply(this);
        arg = (SaExp) this.returnValue;

        this.returnValue = new SaInstEcriture(arg);
    }

    public void caseAInstrvide(AInstrvide node) {

        this.returnValue = null;
    }


    public void caseANonnullExp6(ANonnullExp6 node) {

        SaExp test;
        SaExp oui;
        SaExp non;

        node.getExp6().apply(this);
        test = (SaExp) this.returnValue;

        node.getExp6().apply(this);
        oui = (SaExp) this.returnValue;

        node.getExp6().apply(this);
        non = (SaExp) this.returnValue;

        this.returnValue = new SaExpOptTer(test, oui, non);
    }

    public void caseAOuExp(AOuExp node) {

        SaExp op1;
        SaExp op2;

        node.getExp().apply(this);
        op1 = (SaExp) this.returnValue;

        node.getExp1().apply(this);
        op2 = (SaExp) this.returnValue;

        this.returnValue = new SaExpOr(op1, op2);
    }

    public void caseAExp1Exp(AExp1Exp node) {

        SaExp exp;

        node.getExp1().apply(this);
        exp = (SaExp) this.returnValue;

        this.returnValue = exp;
    }

    public void caseAEtExp1(AEtExp1 node) {

        SaExp op1;
        SaExp op2;

        node.getExp1().apply(this);
        op1 = (SaExp) this.returnValue;

        node.getExp2().apply(this);
        op2 = (SaExp) this.returnValue;

        this.returnValue = new SaExpAnd(op1, op2);
    }

    public void caseAExp2Exp1(AExp2Exp1 node) {

        SaExp exp;

        node.getExp2().apply(this);
        exp = (SaExp) this.returnValue;

        this.returnValue = exp;
    }

    public void caseAInfExp2(AInfExp2 node) {

        SaExp op1;
        SaExp op2;

        node.getExp2().apply(this);
        op1 = (SaExp) this.returnValue;

        node.getExp3().apply(this);
        op2 = (SaExp) this.returnValue;

        this.returnValue = new SaExpInf(op1, op2);

    }

    public void caseAEgalExp2(AEgalExp2 node) {

        SaExp op1;
        SaExp op2;

        node.getExp2().apply(this);
        op1 = (SaExp) this.returnValue;

        node.getExp3().apply(this);
        op2 = (SaExp) this.returnValue;

        this.returnValue = new SaExpEqual(op1, op2);
    }

    public void caseAExp3Exp2(AExp3Exp2 node) {

        SaExp exp;

        node.getExp3().apply(this);
        exp = (SaExp) this.returnValue;

        this.returnValue = exp;
    }

    public void caseAPlusExp3(APlusExp3 node) {

        SaExp op1;
        SaExp op2;

        node.getExp3().apply(this);
        op1 = (SaExp) this.returnValue;

        node.getExp4().apply(this);
        op2 = (SaExp) this.returnValue;

        this.returnValue = new SaExpAdd(op1, op2);
    }

    public void caseAMoinsExp3(AMoinsExp3 node) {

        SaExp op1;
        SaExp op2;

        node.getExp3().apply(this);
        op1 = (SaExp) this.returnValue;

        node.getExp4().apply(this);
        op2 = (SaExp) this.returnValue;

        this.returnValue = new SaExpSub(op1, op2);
    }

    public void caseAExp4Exp3(AExp4Exp3 node) {

        SaExp exp;

        node.getExp4().apply(this);
        exp = (SaExp) this.returnValue;

        this.returnValue = exp;
    }

    public void caseAFoisExp4(AFoisExp4 node) {

        SaExp op1;
        SaExp op2;

        node.getExp4().apply(this);
        op1 = (SaExp) this.returnValue;

        node.getExp5().apply(this);
        op2 = (SaExp) this.returnValue;

        this.returnValue = new SaExpMult(op1, op2);
    }

    public void caseADiviseExp4(ADiviseExp4 node) {

        SaExp op1;
        SaExp op2;

        node.getExp4().apply(this);
        op1 = (SaExp) this.returnValue;

        node.getExp5().apply(this);
        op2 = (SaExp) this.returnValue;

        this.returnValue = new SaExpDiv(op1, op2);
    }

    public void caseAExp5Exp4(AExp5Exp4 node) {

        SaExp exp;

        node.getExp5().apply(this);
        exp = (SaExp) this.returnValue;

        this.returnValue = exp;
    }

    public void caseANonExp5(ANonExp5 node) {

        SaExp op1;

        node.getExp5().apply(this);
        op1 = (SaExp) this.returnValue;

        this.returnValue = new SaExpNot(op1);
    }

    public void caseAExp6Exp5(AExp6Exp5 node) {

        SaExp exp;

        node.getExp6().apply(this);
        exp = (SaExp) this.returnValue;

        this.returnValue = exp;
    }

    public void caseANombreExp6(ANombreExp6 node) {

        int nombre;

        node.getNombre().apply(this);
        nombre = Integer.parseInt(node.getNombre().getText());

        this.returnValue = new SaExpInt(nombre);
    }

    public void caseAAppelfctExp6(AAppelfctExp6 node) {

        SaAppel appel;

        node.getAppelfct().apply(this);
        appel = (SaAppel) this.returnValue;

        this.returnValue = new SaExpAppel(appel);
    }

    public void caseAVarExp6(AVarExp6 node) {

        SaVar var;

        node.getVar().apply(this);
        var = (SaVar) this.returnValue;

        this.returnValue = new SaExpVar(var);
    }

    public void caseAParenthesesExp6(AParenthesesExp6 node) {

        SaExp exp;

        node.getExp().apply(this);
        exp = (SaExp) this.returnValue;

        this.returnValue = exp;
    }

    public void caseALireExp6(ALireExp6 node) {

        SaExpLire lire;

        node.getLire().apply(this);
        lire = (SaExpLire) this.returnValue;

        this.returnValue = lire;
    }

    public void caseAVartabVar(AVartabVar node) {

        String nom;
        SaExp indice;

        node.getIdentif().apply(this);
        nom = node.getIdentif().getText();

        node.getExp().apply(this);
        indice = (SaExp) this.returnValue;

        this.returnValue = new SaVarIndicee(nom, indice);
    }

    public void caseAVarsimpleVar(AVarsimpleVar node) {

        String nom;

        node.getIdentif().apply(this);
        nom = node.getIdentif().getText();

        this.returnValue = new SaVarSimple(nom);
    }

    public void caseARecursifListeexp(ARecursifListeexp node) {

        SaExp dec;
        SaLExp Ldec;

        node.getExp().apply(this);
        dec = (SaExp) this.returnValue;

        node.getListeexpbis().apply(this);
        Ldec = (SaLExp) this.returnValue;

        this.returnValue = new SaLExp(dec, Ldec);
    }

    public void caseAFinalListeexp(AFinalListeexp node) {

        this.returnValue = null;
    }

    public void caseARecursifListeexpbis(ARecursifListeexpbis node) {
       
        SaExp dec;
        SaLExp Ldec;
       
        node.getExp().apply(this);
        dec = (SaExp) this.returnValue;
       
        node.getListeexpbis().apply(this);
        Ldec = (SaLExp) this.returnValue;
       
        this.returnValue = new SaLExp(dec, Ldec);
    }

    public void caseAFinalListeexpbis(AFinalListeexpbis node) {
       
        this.returnValue = null;
    }

    public void caseAAppelfct(AAppelfct node) {
        
        String nom;
        SaLExp exps;
        
        node.getIdentif().apply(this);
        nom = node.getIdentif().getText();
        
        node.getListeexp().apply(this);
        exps = (SaLExp) this.returnValue;
        
        this.returnValue = new SaAppel(nom, exps);
    }
}