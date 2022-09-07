public class UpperCaseFilter implements StringFilter{

    @Override
    public String filter(String string) {
        return string.toUpperCase();
    }


}


