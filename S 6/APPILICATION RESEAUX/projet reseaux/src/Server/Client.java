package Server;

import DataHandler.JsonHandler;
import DataHandler.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.text.ParseException;
import java.util.Scanner;


public class Client {

    public Client() {
        boolean log = HaveAnAccount();
        try {
            String username = auth(log);
            System.out.printf("Hello %s%n", username);
            String msg = username;
            SocketChannel client = SocketChannel.open(new InetSocketAddress("localhost", 8089));
            sendMessage(username, client.socket());
            Scanner scanner = new Scanner(System.in);
            displayCommunication(username);
            while ((msg = scanner.nextLine()) != null) {
                communicate(msg, client.socket(), client);
                displayCommunication(username);
                if (msg.equalsIgnoreCase("EXIT ")) {
                    client.close();
                }
            }
            client.close();
            System.out.println("Server.Client connection closed");

        } catch (Exception e) {
            System.out.println("Connexion au server impossible ");

        }
    }


    public static boolean HaveAnAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("DO YOU HAVE AN ACCOUNT ON THIS SERVER ? TYPE YES OR NO  ");
        String res = scanner.nextLine().toUpperCase();
        if (res.equalsIgnoreCase("YES")) {
            System.out.println("LETS LOGIN THEN ");
            return true;
        } else if (res.equalsIgnoreCase("NO")) {
            System.out.println("LETS CREATE AN ACCOUNT THEN ");
            return false;
        } else {
            HaveAnAccount();
        }
        return false;
    }

    public static void displayCommunication(String username) {
        System.out.println("TYPE YOUR REQUEST ");
        System.out.println("@" + username + ": ") ;

    }

    public static String login() {
        String[] logs = User.getLogs();
        String username = logs[0];
        String password = logs[1];
        if (!JsonHandler.login(username, password)) {
            System.out.println("ERROR ON THE USERNAME OR PASSWORD ");
            login();
        }
        return username;
    }

    public static String register() {
        try {

            String[] logs = User.getLogs();
            String username = logs[0];
            String password = logs[1];
            JsonHandler.pushNewUser(username, password);
            System.out.println("ACCOUNT CREATED SUCCESSFULLY! ");
            return username;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String auth(boolean IsMember) {
        try {
            if (IsMember) {
                return login();
            } else {
                return register();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void communicate(String message, Socket socket, SocketChannel channel) {
        sendMessage(message, socket);
        receiveMessage(channel);
    }

    public static void sendMessage(String message, Socket socket) {
        try {
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.println(message);
            printWriter.flush();
            //printWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void receiveMessage(SocketChannel client) {
        try {
            ByteBuffer buffer = ByteBuffer.allocate(10000);
            client.read(buffer);
            buffer.flip();
            StringBuilder str = new StringBuilder();
            while (buffer.hasRemaining())
                str.append(Character.toString(buffer.get()));
            // in.close();
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException, ParseException, org.json.simple.parser.ParseException {

        Client client = new Client();


    }
}
