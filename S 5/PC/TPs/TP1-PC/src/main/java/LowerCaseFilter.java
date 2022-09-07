public class LowerCaseFilter implements StringFilter {


    @Override
    public String filter(String string) {
        return string.toLowerCase();
    }
}
