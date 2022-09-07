package Formul;

import Formul.Formula;

public class Product implements Formula {
    private Formula x;

    private Formula y;
    private Formula [] t1;
    public Product(Formula [] t1) {
        this.t1 =t1;
    }



    @Override
    public String asString() {
        String string = "";
        for (int i = 0 ; i < t1.length-1; i++) {
            string = string + t1[i].asString() + "+";

        }
        string += t1[t1.length].asString();
        return string;
    }

    @Override
    public double asValue() {
        double result = initialValue();
        for (int i = 0 ; i < t1.length; i++) {
            result = result * t1[i].asValue();
        }
        return result;
    }
    public String symbol () {
        return "*";
    }

    public double initialValue() {
        return  1;
    }
    public double cumulativeValue(double accumulator , double value) {
        return  accumulator * value;
    }

}
