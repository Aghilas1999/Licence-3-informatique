import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTCP {
    
    public static void main(String[] args) throws IOException{
     ServerSocket serverSocket;
      Socket clientSocket;
      serverSocket = new ServerSocket(1234);
      while (true) {
          clientSocket = serverSocket.accept();
          ClientHandler clientHandler = new ClientHandler(clientSocket);
          System.out.print("new connection " + clientSocket.getInetAddress());
          new Thread(clientHandler).start();
      }
      }
}
