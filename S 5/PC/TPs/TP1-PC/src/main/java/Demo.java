public class Demo {

    // Essai pour voir commment ca marche le String Builder.
    private static final int Result = 100_000;

    public static String produceHtmlTable() {
        StringBuilder builder = new StringBuilder("<table>");

        for (int row = 0; row < 10; row++) {
            builder.append("\t<tr>");
            for (int column = 0; column < 10; column++) {
                builder.append("bio");
                builder.append(row * column);
                builder.append("</td>");
            }
            builder.append("bingo");

        }
        builder.append("LOLoa");
        return builder.toString();
    }


    public static void main(String[] args) {
        long begin = System.currentTimeMillis();

        for (int i = 0; i<Result; i++) {
            produceHtmlTable();
        }
        long end = System.currentTimeMillis();
        System.out.println(" Duration est celle a " + (end - begin) + " ma ");
        System.out.println(" ");
    }
}