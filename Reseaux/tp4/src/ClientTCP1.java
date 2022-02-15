import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class ClientTCP1 {

    public static void main(String[] args) {


        try {
            String port = "1234";
            // Create a connection to the server socket on the server application.
            InetAddress host = InetAddress.getLocalHost();
            Socket socket = new Socket(host.getHostName(), Integer.valueOf(port));
            System.out.println("Entre ton text ici !");
            Scanner console = new Scanner(System.in);
            PrintWriter out  = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String string = " ";
            while (console.hasNextLine()) {
                string = console.nextLine();
                out.println(string);
                String greeting = in.readLine();
                System.out.println("Message du serveur :" +greeting);
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}