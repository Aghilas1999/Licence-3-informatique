import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class client1 {

    public Socket socket;

    public int i;

    public Client1(int serverPort) throws IOException {

        this.socket = new Socket(InetAddress.getLocalHost(),serverPort);
    }

    public void start1msg (String msg, int i) throws Exception {
        
        PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);

        out.println(msg + " [Envoi par le client " +i+" ]");
    }
    // Pour Stress2
    public void start2msg (String msg, String msg2,int i) throws Exception {
        
        PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
        
        out.println(msg + " [1 st message Envoyé par le  client " +i+" ]");
        
        out.println(msg2 + " [2 th message Envoyé par le client " +i+" ]");
    }
   
}