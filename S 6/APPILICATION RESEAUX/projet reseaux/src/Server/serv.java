package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class serv {
    public static class server{
        public static void main(String[] args) throws IOException {
            //1. create a server socket
            ServerSocket server = new ServerSocket(8089);

            //2. wait for a connection
            System.out.println("Waiting for the client.....");
            Socket conn = server.accept();
            System.out.println("client connected!!");


            //3. Get the socket I/O stream and perform the processing
            //3.1 --> InputStream; to receive information from client
            //3.2 --> OutputStream; to send information to the client
            BufferedReader in;
            PrintWriter out;
            int i = 0;
            while (true) {
                 in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                String str = in.readLine();
                System.out.println("client: " + str);

                out = new PrintWriter(conn.getOutputStream());
                out.println("hi client message " + i++ +" sent" );
                out.flush();

            }
            //4. Close the connection
           // in.close();
           // out.close();
            //conn.close();

        }
    }
}
