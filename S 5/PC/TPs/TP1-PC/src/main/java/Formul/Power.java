package Formul;

public class Power implements Formula{


    private  Formula formula;
    private  int pow;


    public  Power (Formula formula, int pow) {
        this.formula = formula;
        this.pow = pow;
    }
    @Override
    public String asString() {
        return "le power est : " + asString() + "** " + this.pow;
    }


    @Override
    public double asValue() {
        return Math.pow(formula.asValue(), this.pow);
    }
}
