import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class CLientTCP {
    public static void main(String[] argv){
        sendToServer(argv[0], Integer.parseInt(argv[1]));

    }
    public static void sendToServer(String ip, int port){
        try {
            Scanner scanner = new Scanner(System.in);
            Socket socket = new Socket(ip,port);
            String line;
            while (scanner.hasNextLine()){
                line = scanner.nextLine();
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                out.writeUTF(line);
            }
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}