import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
            ServerSocket s = new ServerSocket(1234);
            Socket c = s.accept();
            PrintWriter out = new PrintWriter(c.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
            int x = 0;
            String y;
            while ((y = in.readLine()) != null) {
                    if (y == null) {
                        c.close();
                        s.close();
                        break;
                    }
                    System.out.println(y);
                    out.println(y);
                    x++;
            }        
            System.out.println("le nombre des paquet recu est : " + x);
    }
}