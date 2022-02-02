/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.analysis;

import sc.node.*;

public interface Analysis extends Switch
{
    Object getIn(Node node);
    void setIn(Node node, Object o);
    Object getOut(Node node);
    void setOut(Node node, Object o);

    void caseStart(Start node);
    void caseAProg1Programme(AProg1Programme node);
    void caseAProg2Programme(AProg2Programme node);
    void caseAOuExp(AOuExp node);
    void caseAExp1Exp(AExp1Exp node);
    void caseAEtExp1(AEtExp1 node);
    void caseAExp2Exp1(AExp2Exp1 node);
    void caseAInfExp2(AInfExp2 node);
    void caseAEgalExp2(AEgalExp2 node);
    void caseAExp3Exp2(AExp3Exp2 node);
    void caseAPlusExp3(APlusExp3 node);
    void caseAMoinExp3(AMoinExp3 node);
    void caseAExp4Exp3(AExp4Exp3 node);
    void caseAFoisExp4(AFoisExp4 node);
    void caseADiviseExp4(ADiviseExp4 node);
    void caseAExp5Exp4(AExp5Exp4 node);
    void caseANonExp5(ANonExp5 node);
    void caseAExp6Exp5(AExp6Exp5 node);
    void caseANombreExp6(ANombreExp6 node);
    void caseAParentheseExp6(AParentheseExp6 node);

    void caseTEspaces(TEspaces node);
    void caseTCommentaire(TCommentaire node);
    void caseTParentheseOuvrante(TParentheseOuvrante node);
    void caseTParentheseFermante(TParentheseFermante node);
    void caseTVirgule(TVirgule node);
    void caseTOu(TOu node);
    void caseTEt(TEt node);
    void caseTDivise(TDivise node);
    void caseTPointVergule(TPointVergule node);
    void caseTInferieur(TInferieur node);
    void caseTNon(TNon node);
    void caseTFois(TFois node);
    void caseTEgal(TEgal node);
    void caseTPlus(TPlus node);
    void caseTMoin(TMoin node);
    void caseTNombre(TNombre node);
    void caseTIdentif(TIdentif node);
    void caseEOF(EOF node);
    void caseInvalidToken(InvalidToken node);
}
