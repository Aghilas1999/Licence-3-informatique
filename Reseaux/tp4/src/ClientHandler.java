import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientHandler implements Runnable {
    Socket clientSocket;
    ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }
    @Override
    public void run() {
        PrintWriter out;
        BufferedReader in;
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));
            while (true) {
                String greeting = in.readLine();
                if (greeting != null) {
                    System.out.println("Message de client :" + greeting);
                    out.print(greeting + ">");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

