package sa;

interface SaVisitor <T> {
    public T visit(SaLInst node);
    public T visit(SaDecTab node);
    public T visit(SaInstEcriture node);
    public T visit(SaInstTantQue node);
    public T visit(SaInstBloc node);
    public T visit(SaInstSi node);
    public T visit(SaInstAffect node);
    public T visit(SaInstRetour node);
    public T visit(SaDecFonc node);
    public T visit(SaDecVar node);
    public T visit(SaLDec node);
    public T visit(SaProg node);
    public T visit(SaVarSimple node);
    public T visit(SaAppel node);
    public T visit(SaExp node);
    public T visit(SaExpLire node);
    public T visit(SaExpInt node);
    public T visit(SaExpVar node);
    public T visit(SaExpAppel node);
    public T visit(SaExpAdd node);
    public T visit(SaExpSub node);
    public T visit(SaExpMult node);
    public T visit(SaExpDiv node);
    public T visit(SaExpInf node);
    public T visit(SaExpEqual node);
    public T visit(SaExpAnd node);
    public T visit(SaExpOr node);
    public T visit(SaExpNot node);
    public T visit(SaLExp node);
    public T visit(SaVarIndicee node);
}
