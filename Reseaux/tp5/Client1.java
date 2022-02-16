import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client1 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Socket c = new Socket("localhost" , 1234);
        PrintWriter out = new PrintWriter(c.getOutputStream(),true);
        BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
        while (sc.hasNext()) {
            String x = sc.nextLine() + " ";
            out.println(x);
            String y = in.readLine();
            System.out.println("En a reçu ceci merci ! : " + y); 
        }
    }
}
