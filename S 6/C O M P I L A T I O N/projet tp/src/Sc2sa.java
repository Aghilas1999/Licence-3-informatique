import sa.*;
import sc.analysis.DepthFirstAdapter;
import sc.node.*;

public class Sc2sa extends DepthFirstAdapter {

    private SaNode returnValue;

    public SaNode getRoot() {
        return this.returnValue;
    }

    @Override
    public void caseADecvarldecfoncProgramme(ADecvarldecfoncProgramme node)
    {
        SaLDec variables, fonctions;
        node.getOptdecvar().apply(this);
        variables = (SaLDec) this.returnValue;
        node.getListedecfonc().apply(this);
        fonctions = (SaLDec) this.returnValue;
        this.returnValue = new SaProg(variables, fonctions);
    }

    @Override
    public void caseALdecfoncProgramme(ALdecfoncProgramme node)
    {
        SaLDec fonctions;
        node.getListedecfonc().apply(this);
        fonctions = (SaLDec) this.returnValue;
        this.returnValue = new SaProg(null, fonctions);
	}

    @Override
    public void caseAOptdecvar(AOptdecvar node)
    {
        SaLDec liste;
        node.getListedecvar().apply(this);
        liste = (SaLDec) this.returnValue;
        this.returnValue = liste;
    }

    @Override
    public void caseADecvarldecvarListedecvar(ADecvarldecvarListedecvar node)
    {
        SaDec tete;
        SaLDec queue;
        node.getDecvar().apply(this);
        tete = (SaDec) this.returnValue;
        node.getListedecvarbis().apply(this);
        queue = (SaLDec) this.returnValue;
        this.returnValue = new SaLDec(tete, queue);
	}

    @Override
    public void caseADecvarListedecvar(ADecvarListedecvar node)
    {
        SaDec tete;
        node.getDecvar().apply(this);
        tete = (SaDec) this.returnValue;
        this.returnValue = new SaLDec(tete, null);
	}

    @Override
    public void caseADecvarldecvarListedecvarbis(ADecvarldecvarListedecvarbis node)
    {
        SaDec tete;
        SaLDec queue;
        node.getDecvar().apply(this);
        tete = (SaDec) this.returnValue;
        node.getListedecvarbis().apply(this);
        queue = (SaLDec) this.returnValue;
        this.returnValue = new SaLDec(tete, queue);
	}

    @Override
    public void caseADecvarListedecvarbis(ADecvarListedecvarbis node)
    {
        SaDec tete;
        node.getDecvar().apply(this);
        tete = (SaDec) this.returnValue;
        this.returnValue = new SaLDec(tete, null);
	}

    @Override
    public void caseADecvarentierDecvar(ADecvarentierDecvar node) {
        String nom;
        node.getIdentif().apply(this);
        nom = node.getIdentif().getText();
        this.returnValue = new SaDecVar(nom);
	}

    @Override
    public void caseADecvartableauDecvar(ADecvartableauDecvar node)
    {
        String nom;
        int taille;
        node.getIdentif().apply(this);
        nom = node.getIdentif().getText();
        node.getNombre().apply(this);
        taille = Integer.parseInt(node.getNombre().getText());
        this.returnValue = new SaDecTab(nom, taille);
	}

    @Override
    public void caseALdecfoncrecListedecfonc(ALdecfoncrecListedecfonc node)
    {
        SaDec tete;
        SaLDec queue;
        node.getDecfonc().apply(this);
        tete = (SaDec) this.returnValue;
        node.getListedecfonc().apply(this);
        queue = (SaLDec) this.returnValue;
        this.returnValue = new SaLDec(tete, queue);
	}

    @Override
    public void caseALdecfoncfinalListedecfonc(ALdecfoncfinalListedecfonc node)
    {
        this.returnValue = null;
    }

    @Override
    public void caseADecvarinstrDecfonc(ADecvarinstrDecfonc node)
    {
        String nom;
        SaLDec parametres;
        SaLDec variables;
        SaInst bloc;
        node.getIdentif().apply(this);
        nom = node.getIdentif().getText();
        node.getListeparam().apply(this);
        parametres = (SaLDec) this.returnValue;
        node.getOptdecvar().apply(this);
        variables = (SaLDec) this.returnValue;
        node.getInstrbloc().apply(this);
        bloc = (SaInstBloc) this.returnValue;
        this.returnValue = new SaDecFonc(nom, parametres, variables, bloc);
	}

    @Override
    public void caseAInstrDecfonc(AInstrDecfonc node)
    {
        String nom;
        SaLDec parametres;
        SaInst bloc;
        node.getIdentif().apply(this);
        nom = node.getIdentif().getText();
        node.getListeparam().apply(this);
        parametres = (SaLDec) this.returnValue;
        node.getInstrbloc().apply(this);
        bloc = (SaInstBloc) this.returnValue;
        this.returnValue = new SaDecFonc(nom, parametres, null, bloc);
	}

    @Override
    public void caseASansparamListeparam(ASansparamListeparam node)
    {
        this.returnValue = null;
    }

    @Override
    public void caseAAvecparamListeparam(AAvecparamListeparam node)
    {
        SaLDec liste;
        node.getListedecvar().apply(this);
        liste = (SaLDec) this.returnValue;
        this.returnValue = liste;
	}

    @Override
    public void caseAInstraffectInstr(AInstraffectInstr node)
    {
        SaInstAffect affect;
        node.getInstraffect().apply(this);
        affect = (SaInstAffect) this.returnValue;
        this.returnValue = affect;
	}

    @Override
    public void caseAInstrblocInstr(AInstrblocInstr node)
    {
        SaInstBloc bloc;
        node.getInstrbloc().apply(this);
        bloc = (SaInstBloc) this.returnValue;
        this.returnValue = bloc;
	}

    @Override
    public void caseAInstrsiInstr(AInstrsiInstr node)
    {
        SaInstSi si;
        node.getInstrsi().apply(this);
        si = (SaInstSi) this.returnValue;
        this.returnValue = si;
    }

    @Override
    public void caseAInstrtantqueInstr(AInstrtantqueInstr node)
    {
        SaInstTantQue tantQue;
        node.getInstrtantque().apply(this);
        tantQue = (SaInstTantQue) this.returnValue;
        this.returnValue = tantQue;
	}

    @Override
    public void caseAInstrappelInstr(AInstrappelInstr node)
    {
        SaAppel appel;
        node.getInstrappel().apply(this);
        appel = (SaAppel) this.returnValue;
        this.returnValue = appel;
	}

    @Override
    public void caseAInstrretourInstr(AInstrretourInstr node)
    {
        SaInstRetour retour;
        node.getInstrretour().apply(this);
        retour = (SaInstRetour) this.returnValue;
        this.returnValue = retour;
	}

    @Override
    public void caseAInstrecritureInstr(AInstrecritureInstr node)
    {
        SaInstEcriture ecriture;
        node.getInstrecriture().apply(this);
        ecriture = (SaInstEcriture) this.returnValue;
        this.returnValue = ecriture;
	}

    @Override
    public void caseAInstrvideInstr(AInstrvideInstr node) {
        this.returnValue = new SaLInst(null, null);
	}

    @Override
    public void caseAInstraffect(AInstraffect node)
    {
        SaVar lhs;
        SaExp rhs;
        node.getVar().apply(this);
        lhs = (SaVar) this.returnValue;
        node.getExp().apply(this);
        rhs = (SaExp) this.returnValue;
        this.returnValue = new SaInstAffect(lhs, rhs);
	}

    @Override
    public void caseAInstrbloc(AInstrbloc node)
    {
        SaLInst val;
        node.getListeinst().apply(this);
        val = (SaLInst) this.returnValue;
        this.returnValue = new SaInstBloc(val);
	}

    @Override
    public void caseALinstrecListeinst(ALinstrecListeinst node)
    {
        SaInst tete;
        SaLInst queue;
        node.getInstr().apply(this);
        tete = (SaInst) this.returnValue;
        node.getListeinst().apply(this);
        queue = (SaLInst) this.returnValue;
        this.returnValue = new SaLInst(tete, queue);
	}

    @Override
    public void caseALinstfinalListeinst(ALinstfinalListeinst node)
    {
        this.returnValue = null;
    }

    @Override
    public void caseAAvecsinonInstrsi(AAvecsinonInstrsi node)
    {
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

    @Override
    public void caseASanssinonInstrsi(ASanssinonInstrsi node)
    {
        SaExp test;
        SaInst alors;
        node.getExp().apply(this);
        test = (SaExp) this.returnValue;
        node.getInstrbloc().apply(this);
        alors = (SaInstBloc) this.returnValue;
        this.returnValue = new SaInstSi(test, alors, null);
	}

    @Override
    public void caseAInstrsinon(AInstrsinon node)
    {
        SaInst sinon;
        node.getInstrbloc().apply(this);
        sinon = (SaInstBloc) this.returnValue;
        this.returnValue = sinon;
	}

    @Override
    public void caseAInstrtantque(AInstrtantque node)
    {
        SaExp test;
        SaInst tantque;
        node.getExp().apply(this);
        test = (SaExp) this.returnValue;
        node.getInstrbloc().apply(this);
        tantque = (SaInst) this.returnValue;
        this.returnValue = new SaInstTantQue(test, tantque);
	}

    @Override
    public void caseAInstrappel(AInstrappel node)
    {
        SaInst appel;
        node.getAppelfct().apply(this);
        appel = (SaAppel) this.returnValue;
        this.returnValue = appel;
	}

    @Override
    public void caseAInstrretour(AInstrretour node)
    {
        SaExp val;
        node.getExp().apply(this);
        val = (SaExp) this.returnValue;
        this.returnValue = new SaInstRetour(val);
	}

    @Override
    public void caseAInstrecriture(AInstrecriture node)
    {
        SaExp arg;
        node.getExp().apply(this);
        arg = (SaExp) this.returnValue;
        this.returnValue = new SaInstEcriture(arg);
	}

    @Override
    public void caseAInstrvide(AInstrvide node)
    {
        this.returnValue = null;
    }

    @Override
    public void caseAOuExp(AOuExp node)
    {
        SaExp op1;
        SaExp op2;
        node.getExp().apply(this);
        op1 = (SaExp) this.returnValue;
        node.getExp1().apply(this);
        op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpOr(op1, op2);
	}

    @Override
    public void caseAExp1Exp(AExp1Exp node)
    {
        SaExp exp;
        node.getExp1().apply(this);
        exp = (SaExp) this.returnValue;
        this.returnValue = exp;
	}

    @Override
    public void caseAEtExp1(AEtExp1 node)
    {
        SaExp op1;
        SaExp op2;
        node.getExp1().apply(this);
        op1 = (SaExp) this.returnValue;
        node.getExp2().apply(this);
        op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpAnd(op1, op2);
	}

    @Override
    public void caseAExp2Exp1(AExp2Exp1 node)
    {
        SaExp exp;
        node.getExp2().apply(this);
        exp = (SaExp) this.returnValue;
        this.returnValue = exp;
	}

    @Override
    public void caseAInfExp2(AInfExp2 node)
    {
        SaExp op1;
        SaExp op2;
        node.getExp2().apply(this);
        op1 = (SaExp) this.returnValue;
        node.getExp3().apply(this);
        op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpInf(op1, op2);

	}

    @Override
    public void caseAEgalExp2(AEgalExp2 node)
    {
        SaExp op1;
        SaExp op2;
        node.getExp2().apply(this);
        op1 = (SaExp) this.returnValue;
        node.getExp3().apply(this);
        op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpEqual(op1, op2);
	}

    @Override
    public void caseAExp3Exp2(AExp3Exp2 node)
    {
        SaExp exp;
        node.getExp3().apply(this);
        exp = (SaExp) this.returnValue;
        this.returnValue = exp;
	}

    @Override
    public void caseAPlusExp3(APlusExp3 node)
    {
        SaExp op1;
        SaExp op2;
        node.getExp3().apply(this);
        op1 = (SaExp) this.returnValue;
        node.getExp4().apply(this);
        op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpAdd(op1, op2);
	}

    @Override
    public void caseAMoinsExp3(AMoinsExp3 node)
    {
        SaExp op1;
        SaExp op2;
        node.getExp3().apply(this);
        op1 = (SaExp) this.returnValue;
        node.getExp4().apply(this);
        op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpSub(op1, op2);
	}

    @Override
    public void caseAExp4Exp3(AExp4Exp3 node)
    {
        SaExp exp;
        node.getExp4().apply(this);
        exp = (SaExp) this.returnValue;
        this.returnValue = exp;
	}

    @Override
    public void caseAFoisExp4(AFoisExp4 node)
    {
        SaExp op1;
        SaExp op2;
        node.getExp4().apply(this);
        op1 = (SaExp) this.returnValue;
        node.getExp5().apply(this);
        op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpMult(op1, op2);
	}

    @Override
    public void caseADiviseExp4(ADiviseExp4 node)
    {
        SaExp op1;
        SaExp op2;
        node.getExp4().apply(this);
        op1 = (SaExp) this.returnValue;
        node.getExp5().apply(this);
        op2 = (SaExp) this.returnValue;
        this.returnValue = new SaExpDiv(op1, op2);
	}

    @Override
    public void caseAExp5Exp4(AExp5Exp4 node)
    {
        SaExp exp;
        node.getExp5().apply(this);
        exp = (SaExp) this.returnValue;
        this.returnValue = exp;
	}

    @Override
    public void caseANonExp5(ANonExp5 node)
    {
        SaExp op1;
        node.getExp5().apply(this);
        op1 = (SaExp) this.returnValue;
        this.returnValue = new SaExpNot(op1);
	}

    @Override
    public void caseAExp6Exp5(AExp6Exp5 node)
    {
        SaExp exp;
        node.getExp6().apply(this);
        exp = (SaExp) this.returnValue;
        this.returnValue = exp;
	}

    @Override
    public void caseANombreExp6(ANombreExp6 node)
    {
        int nombre;
        node.getNombre().apply(this);
        nombre = Integer.parseInt(node.getNombre().getText());
        this.returnValue = new SaExpInt(nombre);
	}

    @Override
    public void caseAAppelfctExp6(AAppelfctExp6 node)
    {
        SaAppel appel;
        node.getAppelfct().apply(this);
        appel = (SaAppel) this.returnValue;
        this.returnValue = new SaExpAppel(appel);
	}

    @Override
    public void caseAVarExp6(AVarExp6 node)
    {
        SaVar var;
        node.getVar().apply(this);
        var = (SaVar) this.returnValue;
        this.returnValue = new SaExpVar(var);
	}

    @Override
    public void caseAParenthesesExp6(AParenthesesExp6 node)
    {
        SaExp exp;
        node.getExp().apply(this);
        exp = (SaExp) this.returnValue;
        this.returnValue = exp;
    }

    @Override
    public void caseALireExp6(ALireExp6 node)
    {
        SaExpLire lire;
        node.getLire().apply(this);
        lire = (SaExpLire) this.returnValue;
        this.returnValue = lire;
	}

    @Override
    public void caseAVartabVar(AVartabVar node)
    {
        String nom;
        SaExp indice;
        node.getIdentif().apply(this);
        nom = node.getIdentif().getText();
        node.getExp().apply(this);
        indice = (SaExp) this.returnValue;
        this.returnValue = new SaVarIndicee(nom, indice);
	}

    @Override
    public void caseAVarsimpleVar(AVarsimpleVar node)
    {
        String nom;
        node.getIdentif().apply(this);
        nom = node.getIdentif().getText();
        this.returnValue = new SaVarSimple(nom);
	}

    @Override
    public void caseARecursifListeexp(ARecursifListeexp node)
    {
        SaExp tete;
        SaLExp queue;
        node.getExp().apply(this);
        tete = (SaExp) this.returnValue;
        node.getListeexpbis().apply(this);
        queue = (SaLExp) this.returnValue;
        this.returnValue = new SaLExp(tete, queue);
	}

    @Override
    public void caseAFinalListeexp(AFinalListeexp node)
    {
        this.returnValue = null;
    }

    @Override
    public void caseARecursifListeexpbis(ARecursifListeexpbis node)
    {
        SaExp tete;
        SaLExp queue;
        node.getExp().apply(this);
        tete = (SaExp) this.returnValue;
        node.getListeexpbis().apply(this);
        queue = (SaLExp) this.returnValue;
        this.returnValue = new SaLExp(tete, queue);
	}

    @Override
    public void caseAFinalListeexpbis(AFinalListeexpbis node)
    {
        this.returnValue = null;
    }

    @Override
    public void caseAAppelfct(AAppelfct node)
    {
        String nom;
        SaLExp exps;
        node.getIdentif().apply(this);
        nom = node.getIdentif().getText();
        node.getListeexp().apply(this);
        exps = (SaLExp) this.returnValue;
        this.returnValue = new SaAppel(nom, exps);
	}
}