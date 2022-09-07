package Formul;

public class Minimum  implements  Formula {

    public Formula formulas[];

    public Minimum(Formula[] formulas) {
        this.formulas = formulas;
    }

    @Override
    public String asString() {
        return "Le Minimum est " + asValue();


    }
    @Override
    public double asValue () {
        double min = formulas[0].asValue();

        for (int i = 1 ; i < formulas.length ; i++) {
            if (formulas[i].asValue() > min)
                min = formulas[i].asValue();

        }
        return  min;
    }
}