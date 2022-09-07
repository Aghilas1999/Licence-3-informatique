public class Main {
    

    public static void main(String[] args) {
        Node a = new leaf("a");
        Node b = new leaf("b");
        Node c = new leaf("c");
        Node d = new leaf("d");
        node ab = new InternalNode(a,b);
        node cd = new InternalNode(c;d);
        Node abcd = new InternalNode(ab , cd);
        system.out.println(abcd.convertToString());
    }
}