import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EchoServerV {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(1234);
        server.setReuseAddress(true);
        while (true) {
            Socket client = server.accept();
            ClientHandler clientSock = new ClientHandler(client);
            ExecutorService executor =  Executors.newWorkStealingPool();
            executor.execute(clientSock);
        }
    }
}
