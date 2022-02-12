import java.io.IOException;
import java.util.ArrayList;

public class Stress {

    public static void stress1(int n) throws IOException {
        ArrayList<ClientTCP> list = new ArrayList<>();
        ClientTCP clientTCP;
        for (int i = 0; i < n; i++) {
            clientTCP = new ClientTCP(1234);
            list.add(clientTCP);
            clientTCP.sendMessage("message du client numero " +i + "\n" );
            clientTCP.socket.close();       }
        }




    public static void main(String[] args) throws IOException {
        Stress.stress1(10);
    }

}
