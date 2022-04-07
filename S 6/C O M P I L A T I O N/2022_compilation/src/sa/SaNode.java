package sa;

public interface SaNode{
    public <T> T accept(SaVisitor <T> visitor);
}

