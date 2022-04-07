package sa;

public class SaExpLire implements SaExp{

    public SaExpLire(){
    }

    public <T> T accept(SaVisitor <T> visitor) {
        return visitor.visit(this);
    }
}
