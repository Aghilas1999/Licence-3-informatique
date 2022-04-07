package Server;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import REQUEST.*;

import DataHandler.*;

public class NonBlockingServer {

    private static final HashMap<SelectableChannel, String> clients = new HashMap<>();
    private static final HashMap<User, ArrayList<String>> userMessages = new HashMap<>();
    private static Selector selector;
    private static final int nbClient = 0;

    ServerSocket serverSocket;

    NonBlockingServer() throws IOException {
    }

    private static void handleAccept(ServerSocketChannel mySocket,
                                     SelectionKey key) throws IOException {

        // Accept the connection and set non-blocking mode
        SocketChannel client = mySocket.accept();
        client.configureBlocking(false);
        client.register(selector, SelectionKey.OP_READ);
        System.out.println("CLIENT ACCEPTED ");


    }

    private static String getRequest(SelectionKey key) throws IOException {
        SocketChannel client = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        client.read(buffer);
        String data = (new String(buffer.array())).trim();

        return data;
    }

    public static void addClient(SelectionKey key){


        try {
            if (!clients.containsKey(key.channel())) {
                clients.put(key.channel(), getRequest(key));
            }
        }catch (IOException e ){
            e.printStackTrace();
        }
    }

    private static void handleRead(SelectionKey key) throws IOException  {
        SocketChannel client = (SocketChannel) key.channel();
        String Request_Sent = getRequest(key);
        String user = clients.get(key.channel());
        //System.out.println( user + " : " + Request_Sent);
        //System.out.println("le client a ecrit " + Request_Sent);
        String Response = REQUEST.handelGenRequest(Request_Sent,user);

        writeToTheClient(client, Response);


    }

    public static void writeToTheClient(SocketChannel socketChannel, String message) {
        try {
            socketChannel.write(ByteBuffer.wrap(message.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }





    public static void closeConnection(SocketChannel client) throws IOException {
        client.close();
        System.out.println("Connection closed...");
    }


    public static void main(String[] args) throws IOException {
        NonBlockingServer nonBlockingServer = new NonBlockingServer();
        nonBlockingServer.launchServer();
    }

    void launchServer() {
        try {
            selector = Selector.open();
            // on cree un selector pour notre server
            // We have to set connection host, port and non-blocking mode
            ServerSocketChannel socket = ServerSocketChannel.open();
            serverSocket = socket.socket();

            serverSocket.bind(new InetSocketAddress("localhost", 8089));
            socket.configureBlocking(false);
            int ops = socket.validOps();
            socket.register(selector, ops, null);
            while (true) {
                selector.select();
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> i = selectedKeys.iterator();
                while (i.hasNext()) {
                    SelectionKey key = i.next();
                    if (key.isAcceptable()) {
                        // New client has been accepted
                        handleAccept(socket, key);
                    } else if (key.isReadable()) {
                        if (clients.get(key.channel()) == null) addClient(key);
                        else {
                            handleRead(key);
                        }
                    }
                }
                i.remove();
            }
        } catch (Exception e) {

            e.printStackTrace();
            return;
        }
    }


}
 
