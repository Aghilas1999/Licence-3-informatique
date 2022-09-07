import java.util.Iterator;

public class Card<T> implements  Iterable<T>{

    private int suit;
    private int rank;
    private  T[][] elements;
    public Card (int suit, int rank) {
        this.suit = suit;
        this.rank = rank;
    }

    @Override
    public String toString (){
        StringBuilder build = new StringBuilder(rank+ " De ");

        switch (suit) {
            case 0:
                build.append("trefle");
                break;
            case 1:
                build.append("Carreau");
                break;
            case 2:
                build.append("coeur");
            case 3:
                build.append("Pique");
                break;
        }
        return build.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
