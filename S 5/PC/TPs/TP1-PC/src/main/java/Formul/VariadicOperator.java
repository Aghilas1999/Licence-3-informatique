package Formul;

public abstract class VariadicOperator implements  Formula{

    public VariadicOperator(Formula[] formulas) {
        this.formulas = formulas;
    }

    public Formula[] formulas;



    public abstract String symbol();

    public abstract double initialValue();

    public abstract double cumulativeValue(double accumulator, double value);
    @Override
    public String asString () {
        StringBuilder stringBuilder = new StringBuilder("(");
        for (int i = 0; i < formulas.length ; i++) {
            stringBuilder.append(formulas[i].asString() );
        }
        stringBuilder.append(")");
        return stringBuilder.toString();

    }
}
