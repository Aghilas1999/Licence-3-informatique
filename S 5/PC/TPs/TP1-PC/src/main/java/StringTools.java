import com.sun.tools.javac.comp.Lower;

public class StringTools {

    public static String[] filter(String[] strings, StringFilter filter){
        String[] strings1 = new String[strings.length];
        for (int i = 0 ; i < strings.length ; i++){
            strings1[i] = filter.filter(strings[i]);
        }
        return strings1;
    }

    public static void main(String[] args) {
        String[] tab = {"elies", "aghilas", "Mohammed Ali"};
        UpperCaseFilter upperCaseFilter = new UpperCaseFilter();
        LowerCaseFilter lowerCaseFilter = new LowerCaseFilter();
        String[] s = filter(tab,upperCaseFilter);
        for(String string : s){
            System.out.println(string);
        }
    }
}
