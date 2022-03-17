package c3a;

public interface C3aVisitor <T> {
    public T visit(C3aInstAdd inst);
    public T visit(C3aInstCall inst);
    public T visit(C3aInstFBegin inst);
    public T visit(C3aInst inst);
    public T visit(C3aInstJumpIfLess inst);
    public T visit(C3aInstMult inst);
    public T visit(C3aInstRead inst);
    public T visit(C3aInstSub inst);
    public T visit(C3aInstAffect inst);
    public T visit(C3aInstDiv inst);
    public T visit(C3aInstFEnd inst);
    public T visit(C3aInstJumpIfEqual inst);
    public T visit(C3aInstJumpIfNotEqual inst);
    public T visit(C3aInstJump inst);
    public T visit(C3aInstParam inst);
    public T visit(C3aInstReturn inst);
    public T visit(C3aInstWrite inst);
    public T visit(C3aInstStop inst);

    public T visit(C3aConstant oper);
    public T visit(C3aLabel oper);
    public T visit(C3aTemp oper);
    public T visit(C3aVar oper);
    public T visit(C3aFunction oper);
}
