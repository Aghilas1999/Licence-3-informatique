package Formul;

public class SquareRoot implements  Formula{

    Formula []t1 ;

    public SquareRoot(Formula[] t1) {
        this.t1 = t1;
    }



    @Override
    public String asString() {
        return "le SquareRoot de formula est :" +Math.sqrt(asValue());
    }

    @Override
    public double asValue() {
        return Math.sqrt(asValue());
    }
}
