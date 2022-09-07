import java.util.Arrays;

public class CompositeStringFilter implements StringFilter {

    private StringFilter[] stringFilters;

    public CompositeStringFilter(StringFilter[] stringFilters) {
        this.stringFilters = stringFilters;
    }


    @Override
    public String filter(String string) {
        String[] str = {string};
        for (StringFilter stringfilter : stringFilters) {
            System.out.println(Arrays.toString(StringTools.filter(str, stringfilter)));
        }
        return str[0];
    }
}
