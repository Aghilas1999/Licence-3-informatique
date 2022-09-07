package Formul;

import Formul.Formula;

public class Variable implements Formula {

    private  String str;
    private  double d;

    public Variable(String str, double d) {
        this.str = str;
        this.d = d;
    }

    public double getD() {
        return d;
    }

    @Override
    public String asString() {
        return str;
    }

    @Override
    public double asValue() {
        return d;
    }
}
