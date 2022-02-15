import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


class EchoServerT {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(1234);
        server.setReuseAddress(true);
        while (true) {
            Socket client = server.accept();
            ClientHandler clientSock = new ClientHandler(client);
            new Thread(clientSock).start();
        }
    }

}
