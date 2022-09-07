package Formul;

public class Maximum implements  Formula{
    Formula formulas[] ;

    public Maximum(Formula[] formulas) {
            this.formulas = formulas;
    }

    @Override
    public String asString() {
        return "le max " + asValue();

    }

    @Override
    public double asValue() {
        double max = formulas[0].asValue();

        for(int i = 1 ; i < formulas.length; i++) {

            if (formulas[i].asValue() > max)
                max = formulas[i].asValue();
        }
        return max;
    }
}
