import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketPermission;
import java.util.Scanner;

public class ClientTCP {
    public static void main(String[] args) throws IOException {

            Scanner sc = new Scanner(System.in);
            Socket c = new Socket("localhost",1234);
            PrintWriter out = new PrintWriter(c.getOutputStream(),true);
            while (sc.hasNext()) {
                String x = sc.nextLine() + " " ;
                out.println(x);
            }
    }
}