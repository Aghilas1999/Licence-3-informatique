// sources : https://crunchify.com/java-nio-non-blocking-io-with-server-client-example-java-nio-bytebuffer-and-channels-selector-java-nio-vs-io/

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class StressNIO {
    public static void main(String[] args) throws IOException {
        float début = System.nanoTime();
        int i =0;
        int n = 1;
        String str = "yooo  salut la  le s";
        while (i<n){
            InetSocketAddress adress = new InetSocketAddress("localhost",1234);
            SocketChannel client = SocketChannel.open(adress);
            byte[] msg = str.getBytes();
            ByteBuffer buffer = ByteBuffer.wrap(msg);
            client.write(buffer);
            client.close();
            i++;
        }
        float fin = System.nanoTime();
        float temps = fin - début;
        System.out.println("le temps mis est  : " + temps/100000000 );
    }
}