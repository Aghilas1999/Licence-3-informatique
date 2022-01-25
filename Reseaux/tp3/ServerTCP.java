import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTCP {
    
    public static void main(String[] args) throws IOException{
     ServerSocket serverSocket;
      Socket clientSocket;
     PrintWriter out;
      BufferedReader in;
      serverSocket = new ServerSocket(1234);
      clientSocket = serverSocket.accept();
      out = new PrintWriter(clientSocket.getOutputStream(), true);
      in = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));
      while (true) {
         String greeting = in.readLine();
         System.out.println("Message de client :" + greeting);
         out.print(greeting+">");
     }
    }
}
