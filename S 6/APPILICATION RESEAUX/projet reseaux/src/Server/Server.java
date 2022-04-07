package Server;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Server {

    // this is equivalent to the server socket in the non nio world
    ServerSocketChannel serverSocketChannel;



    // this is the multiplexer which multiplexes the messages received from
    // different clients
    Selector selector;

    public Server() {
        try {

            // get a selector
            selector = Selector.open();

            // get a server socket channel
            serverSocketChannel = ServerSocketChannel.open();

            // we force the socket to be Non-blocking.
            // if it is set to "true" then this socket acts as a normal
            // (blocking) server socket
            serverSocketChannel.configureBlocking(false);

            // port and ip address where the server listens for connections
            InetSocketAddress add = new InetSocketAddress(
                    InetAddress.getLocalHost(), 9999);

            // bind the server socket to the ip/port
            serverSocketChannel.socket().bind(add);

            // register the serverSocketChannel (for incoming connection events)
            // to the selector.
            // The "SelectionKey.OP_ACCEPT" parameter tells the selector that
            // this serverSocketChannel registers
            // itself for incoming (acceptable) connections
            SelectionKey key = serverSocketChannel.register(selector,
                    SelectionKey.OP_ACCEPT);
            System.out.println("serverSocketChannel's registered key is : "
                    + key.channel().toString());

            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.startListening();
    }

    private void startListening() {

        System.out.println("Server is listening on: "
                + serverSocketChannel.socket().getInetAddress()
                .getHostAddress() + ":"
                + serverSocketChannel.socket().getLocalPort());

        while (true) {
            try {

                // this line blocks until some events has occurred in the
                // underlying socket
                selector.select();

                // get the selected keys set
                Set selectedKeys = selector.selectedKeys();

                Iterator iterator = selectedKeys.iterator();

                while (iterator.hasNext()) {

                    SelectionKey key = (SelectionKey) iterator.next();

                    iterator.remove();

                    // a client has asked for a new connection
                    if (key.isAcceptable()) {
                        // only ServerSocketsChannels registered for OP_ACCEPT
                        // are excepted to receive an
                        // "acceptable" key

                        System.out.println("Key ready to perform accept() : "
                                + key.channel().toString());

                        // as usual the accept returns the plain socket towards
                        // the client
                        SocketChannel client = serverSocketChannel.accept();

                        // set the client socket to be non blocking
                        client.configureBlocking(false);

                        // register the client socket with the same selector to
                        // which we have registered the
                        // serverSocketChannel
                        client.register(selector, SelectionKey.OP_READ);
                        // client.register(selector, SelectionKey.OP_WRITE);
                        continue;
                    }

                    // the client has sent something to be read by this server
                    else if (key.isReadable()) {

                        System.out.println("Key ready to perform read() : "
                                + key.channel().toString());

                        // get the underlying socket
                        SocketChannel client = (SocketChannel) key.channel();
                        ByteBuffer bb = ByteBuffer.allocate(1024);

                        // read the msg sent by the client
                        client.read(bb);

                        // display the message
                        bb.flip();
                        byte[] array = new byte[bb.limit()];
                        bb.get(array);
                        System.out.println(new String(array));

                        client.register(selector, SelectionKey.OP_WRITE);


                        continue;
                    }
                    else if(key.isWritable()){

                        SocketChannel client = (SocketChannel) key.channel();

                        String s = "walid lm9owed";
                        ByteBuffer bb1 = ByteBuffer.allocate(s.getBytes().length);

                        byte[] array1 = new byte[bb1.limit()];
                        array1 = s.getBytes();
                        bb1.put(array1);
                        bb1.flip();
                        client.write(bb1);
                        System.out.println("write ");
                        client.register(selector,SelectionKey.OP_CONNECT);
                    }


                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void write() {

    }


}

