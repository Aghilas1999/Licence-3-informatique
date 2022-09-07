package Formul;

import Formul.Formula;

public class Sum implements Formula {

    private Formula x;
    private Formula y;

    public Sum(Formula x, Formula y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String asString() {
        return x.asString() + " + " + y.asString();
    }

    @Override
    public double asValue() {
        double Val = initialValue();

        return x.asValue() + y.asValue();
    }


    public String symbol () {
        return  "+";
    }
    public double initialValue() {
            return  0;
    }
    public double cumulativeValue(double accumulator , double value) {
            return  accumulator + value;
    }
}
