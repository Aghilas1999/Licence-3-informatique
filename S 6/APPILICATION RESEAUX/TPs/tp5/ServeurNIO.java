import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ServeurNIO {
    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel serveurChannel = ServerSocketChannel.open();
        InetSocketAddress adresse = new InetSocketAddress("localhost", 1234);
        serveurChannel.bind(adresse);
        serveurChannel.configureBlocking(false);
        int op = serveurChannel.validOps();
        SelectionKey selectionKey = serveurChannel.register(selector,SelectionKey.OP_ACCEPT);
        while (true){
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                if (key.isAcceptable()){
                    SocketChannel clientChannel = serveurChannel.accept();
                    clientChannel.configureBlocking(false);
                    clientChannel.register(selector,SelectionKey.OP_READ);
                    ByteBuffer bf = ByteBuffer.allocate(100000);
                    clientChannel.read(bf);
                    String result = new String(bf.array()).trim();
                    System.out.println(result);
                }
                iterator.remove();
            }
        }
    }
}