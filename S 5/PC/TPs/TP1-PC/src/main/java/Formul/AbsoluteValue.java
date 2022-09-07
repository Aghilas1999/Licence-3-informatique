package Formul;

public class AbsoluteValue implements Formula {
    public Formula formula;


    public AbsoluteValue(Formula formula) {
        this.formula = formula;
    }

    @Override
    public String asString() {
        return " la valeur absolute est |" +formula.asValue() + "|" ;
    }

    /* une Methode pour voir si la valeur est positive ou non*/
    @Override
    public double asValue() {
        if(formula.asValue() > 0) {
            return formula.asValue();
        }else {
            return  -formula.asValue()
;        }

    }
}
