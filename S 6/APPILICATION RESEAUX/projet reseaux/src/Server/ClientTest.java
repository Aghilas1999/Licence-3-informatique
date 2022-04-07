package Server;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientTest {
    static String IP = "localhost"; // server IP address

    public static void main(String[] args) throws UnknownHostException,
            IOException {
        // TODO Auto-generated method stub

        String msg;
        Socket socket = new Socket(IP, 9999);

        System.out.println("client: complete making socket");
        Scanner scan = new Scanner(System.in);
        System.out.println("client: input data to send");

        while (true) {
            // read
            System.out.print(">>>");
            msg = scan.nextLine();

            OutputStream out = socket.getOutputStream();
            DataOutputStream dou = new DataOutputStream(out);
            dou.writeUTF(msg);

            // write from server

            InputStream in = socket.getInputStream();
            DataInputStream din = new DataInputStream(in);
            String remsg = din.readUTF();
            System.out.println("client: data from server" + remsg);

            if (remsg.equalsIgnoreCase("END")) {
                System.out.println("SOCKET END");
                socket.close();
                break;

            }
        }
    }
}
