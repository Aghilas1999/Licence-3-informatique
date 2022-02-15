import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }
  
    public void run() {
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String line;
            line = in.readLine();
            System.out.printf("Message: %s\n", line);
            out.println(line);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}