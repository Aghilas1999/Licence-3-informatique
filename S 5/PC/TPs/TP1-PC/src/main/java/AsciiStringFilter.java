public class AsciiStringFilter implements StringFilter {

    @Override
    public String filter(String string) {
        String result = "";
        for (int count = 0 ; count < string.length() ; count ++){
            if (string.charAt(count) < 128){
                result += string.charAt(count);
            }
        }
        return result;
    }

}
