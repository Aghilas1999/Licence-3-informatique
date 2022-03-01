import java.util.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;


public class ClientUDP
{
  public static void main(String[] args)
  {
    // UN peut test pour voir
        // UN peut test pour voir

    InetAddress adr;
    int port;

    try
    {
      if (args.length==2){
        
        
        adr = InetAddress.getByName(args[0]);
        port = Integer.parseInt(args[1]);
      
      }else{
      
        System.out.println("Adresse et/ou port manquant");
        System.out.println("Passer l'adresse en premier agrument et le port en second.");
        return;
      }

      DatagramSocket sock = new DatagramSocket();
      Scanner sc = new Scanner(System.in);
      do
      {
        String msg = sc.nextLine()+'\n';
        byte[] data = msg.getBytes();
        DatagramPacket packet = new DatagramPacket(data, data.length, adr, port);
        sock.send(packet);
      } while (sc.hasNextLine());

      sock.close();
    }
    catch(Exception e)
    {
      System.out.println("Une erreur s'est produite");
      e.printStackTrace();
    }
  }
}
