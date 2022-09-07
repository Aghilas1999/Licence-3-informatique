package Formul;

public class Square implements Formula {
    Formula t1[];

    public Square(Formula[] t1) {
        this.t1 = t1;
    }

    @Override
    public String asString() {
        return  "le squar est :" + Math.pow(asValue(),2 );
    }

    @Override
    public double asValue() {
        return Math.pow(asValue(),2);
    }
}
