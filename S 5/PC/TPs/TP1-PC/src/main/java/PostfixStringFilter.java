public class PostfixStringFilter implements StringFilter{

    private int n;

    public PostfixStringFilter(int n) {
        this.n = n;
    }

    @Override
    public String filter(String string) {
        String result = "";
        n = string.length() - n;
        for(int i = n -1 ; i < string.length() ; i++){
            result += string.charAt(i);
        }
        return result;
    }
}
