
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ClientNIO {
    public static void main(String[] args) throws IOException {

        String str = "Hi thereqerg !";
        InetSocketAddress adress = new InetSocketAddress("localhost",1234);
        SocketChannel client = SocketChannel.open(adress);
        byte[] msg = str.getBytes();

            ByteBuffer buffer = ByteBuffer.wrap(msg);
            client.write(buffer);


    }
}