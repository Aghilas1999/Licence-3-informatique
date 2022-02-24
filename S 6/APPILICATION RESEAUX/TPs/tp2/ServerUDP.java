import java.util.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;


public class ServerUDP
{
  public static void main(String[] args)
  {
    int port;

    try
    {
      if (args.length==1)
      {
        port = Integer.parseInt(args[0]);
      }
      else
      {
        System.out.println("erreur Port manquant");
        System.out.println("Passer le port en premier argument.");
        return;
      }

      DatagramSocket sock = new DatagramSocket(port);
      byte[] data = new byte[1024];
      DatagramPacket packet = new DatagramPacket(data, data.length);

      do
      {
        sock.receive(packet);
        String msg = new String(packet.getData(), 0, packet.getLength());
        System.out.print(msg);

        String response = "reçu : "+msg;
        byte[] data2 = response.getBytes();
        packet.setData(data2);
        sock.send(packet);
      } while(true);

    }
    catch(Exception e)
    {
      System.out.println("Une erreur s'est produite");
      e.printStackTrace();
    }
  }
}