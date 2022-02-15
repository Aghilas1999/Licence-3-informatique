import java.net.*;
import java.util.concurrent.*;
import java.util.logging.Handler;
import java.io.*;

public class Server {

    // Demmarrage et delégation des connexions entrantes.
    public void demarrer(int port) {
        ServerSocket ssocket; // socket d'ecoute utilisée par le seveur.
        Socket csocket;

        System.out.print("Lancement du serveur sur le port " +port);
        try 
          {
              ssocket = new ServerSocket(port);
              ssocket.setReuseAddress(true); /* rend le port réutilisable rapidement */
              while (true)
              {
                  csocket = ssocket.accept();
                  Handler ch = new Handler(csocket);
                  Thread thread = new Thread(ch);
                  thread.start(); 
              }
          } catch (IOException ex)
          {
              System.out.println("Arret anormal de serveur." + ex);
              return;
          }           
    }
    
    public static void main(String[] args) {
        int args = args.length;
        Server serveur;
        // Traitement des arguments

        if (args == 1 )
        {
            try{
                serveur = new Server();
                serveur.demarrer(Integer.parseInt(args[0]));
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }else
        {
            System.out.println("Usage : java server port");
        }
        return;
    }

    
}
