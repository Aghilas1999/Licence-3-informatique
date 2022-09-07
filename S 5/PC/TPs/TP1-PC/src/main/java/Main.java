import Formul.*;

public class Main {

    public static void main(String[] args) {

        Variable x = new Variable("Smail ", 2.5);
        Variable y = new Variable("aghilas ", 4);
        Variable z = new Variable("Smail ", 7);

        //Formula f = new Sum(x, new Product(y, new Sum(x, y)));
        //System.out.println(f.asString());
        //System.out.println(f.asValue());
        Formula[] formulas = new Formula[3];
        formulas[0] = x;
        formulas[1] = y;
        formulas[2] = z;
        VariadicOperator V = new VariadicOperator(formulas) {
            @Override
            public String symbol() {
                return null;
            }

            @Override
            public double initialValue() {
                return 0;
            }

            @Override
            public double cumulativeValue(double accumulator, double value) {
                return 0;
            }

            @Override
            public double asValue() {
                return 0;
            }
        };
        System.out.println(V.asString());
        //System.out.println(new formula.Minimum(formulas).asString());
      /*  UpperCaseFilter upperCaseFilter = new UpperCaseFilter();
        LowerCaseFilter lowerCaseFilter = new LowerCaseFilter();
        PrefixStringFilter prefixStringFilter = new PrefixStringFilter(2);
        PostfixStringFilter postfixStringFilter = new PostfixStringFilter(2);
        AsciiStringFilter asciiStringFilter = new AsciiStringFilter();
        StringFilter[] stringFilter = {upperCaseFilter, lowerCaseFilter, prefixStringFilter, postfixStringFilter, asciiStringFilter};
        CompositeStringFilter compositeStringFilter = new CompositeStringFilter(stringFilter);
        compositeStringFilter.filter("test"); */
    }
}
