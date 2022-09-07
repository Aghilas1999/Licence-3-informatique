public class Main {
    public static void main(String[] args) {
        /*Vector v = new Vector(10);
        //System.out.println(v.getElements());
        v.toString();*/

     /**   Random random = new Random();
        Vector vector = new Vector(2);
        Card card = new Card(random.nextInt()%20,random.nextInt()%10);
        vector.add(card);

        System.out.println(card);*/

     /*Random random = new Random();
     Vector vector = new Vector();

     for ( int i = 0 ; i<3; i++) {
         vector.add(new Card(random.nextInt(4), random.nextInt(14)));
     }

        System.out.println(vector);
    }*/
 /*   Vector _verbose = new VerboseVector();
        _verbose.add(new Card(0 ,0));
        _verbose.add(2);
        _verbose.set(1, 3);
*/
        /**En fait un test pour la classe RangeArray
         * testttt aghilas*/
        /*
        int indexMin = -3;
        int indexMax = 1;
        RangeArray squares = new RangeArray(indexMin, indexMax);
        for (int index =  squares.getIndexMin() ; index <= squares.getIndexMax() ; index++)
            squares.set(index, index*index);
        for (int index =  squares.getIndexMin() ; index <= squares.getIndexMax() ; index++)
            System.out.println(index + " -> " + squares.get(index));
        System.out.println(squares.rangeSize());
        */
        /*RangeArray<String> habitations = new RangeArray<String>(3, 5);
        habitations.set(3, "Maison");
        habitations.set(4, "Immeuble");
        habitations.set(5, "Hutte");
        for (int index =  habitations.getIndexMin();
             index <= habitations.getIndexMax();
             index++)
            System.out.print(habitations.get(index).length() + " ");*/

        RangeArray<String> habitations  = new RangeArray<String>(3,5);
        habitations.set(3,"Maison");
        habitations.set(4,"Immuble");
        habitations.set(5,"Hutte");
        for (String habitation :habitations)
            System.out.println(habitation+" ");
}


}
