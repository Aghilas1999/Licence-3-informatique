public class PrefixStringFilter implements StringFilter {

    private int n;

    public PrefixStringFilter(int n) {
        this.n = n;
    }

    @Override
    public String filter(String string) {
        String result = "";
        for(int i = 0 ; i < n ; i++){
            result += string.charAt(i);
        }
        return result;
    }
}
