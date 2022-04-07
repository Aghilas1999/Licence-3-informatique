package c3a;

public abstract class C3aOperand {

    public abstract <T> T accept(C3aVisitor <T> visitor);

}
