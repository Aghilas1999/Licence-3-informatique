import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;


public class ClientTCP {
    int port;
    InetAddress host;
    public  Socket socket;

    public ClientTCP(int port) throws IOException {
        this.port = port;
        host = InetAddress.getLocalHost();
        socket = new Socket(host.getHostName(), port);
    }

    public void sendMessage(String message) throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out.println(message);
        //String greeting = in.readLine();
        //System.out.println("Message du serveur :" + greeting);
    }


    public static void main(String[] args) throws IOException {
        ClientTCP clientTCP = new ClientTCP(1234);
        Scanner scanner = new Scanner(System.in);
        clientTCP.sendMessage(scanner.nextLine());
    }
}