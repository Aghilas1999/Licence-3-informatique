package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class sock {
    public static void main(String[] args) throws IOException {
        // ServerSocket socket = new ServerSocket(4444);

        SocketChannel clientChannel = SocketChannel.open(new InetSocketAddress("localhost", 8089));
        Socket client = clientChannel.socket();
        Scanner scanner = new Scanner(System.in);
        sendMessage("walid54",client);
        while (true){
            System.out.println("ENTREZ VOTRE MESSAGE ");
            String msg = scanner.nextLine();
            communicate(msg,client,clientChannel);

        }

        // InputStream to receive data from server
        // OutputStream to send data to server


    }
    public static  void communicate(String message , Socket socket,SocketChannel channel){
        sendMessage(message,socket);
        receiveMessage(channel);
    }
    public static void sendMessage(String message,Socket socket){
        try {
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.println(message);
            printWriter.flush();
            //printWriter.close();

        }catch (Exception e ){
            e.printStackTrace();
        }

    }
    public static void  receiveMessage(SocketChannel client) {
        try {
            ByteBuffer buffer = ByteBuffer.allocate(1000);
            client.read(buffer);
            buffer.flip();
            StringBuilder str = new StringBuilder();
            while (buffer.hasRemaining())
                str.append(Character.toString(buffer.get()));
           // in.close();
            System.out.println(str);
        }catch (Exception e ){
            e.printStackTrace();
        }

    }
}
