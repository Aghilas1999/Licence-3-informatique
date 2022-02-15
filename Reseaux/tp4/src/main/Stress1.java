import java.awt.*;
import java.awt.geom.Line2D;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Stress1 {

    List<Client> client = new ArrayList<>();
    public void startStress1(int n) throws Exception {
        String msg;
        for (int i = 0; i < n; i++) {
            client.add(new Client(1234));
            msg = "message N°:"+Integer.toString(i);
            client.get(i).start1msg(msg, i);
            client.get(i).socket.close();
        }

    }

    public static void main(String[] args) throws Exception {
        
        System.out.println((end-start)/1000000 + "ms");*/

        int[] n = {1, 2, 10, 100, 1000, 5000};
        FileWriter file = new FileWriter("TempsV.csv");
        long start, end, tempsE;

        file.append("1; 2; 10; 100; 1000; 5000 \n");
        
        for (int i : n){
        
            System.out.println("Test pour "+i+);
            Stress1 stress1 = new Stress1();
            start = System.nanoTime();
            stress1.startStress1(i);
            end = System.nanoTime();
            tempsE = (end-start)/1000000;
            System.out.println(tempsE+ "ms");
            System.out.println("Test pour "+i+" Fini");
           file.append(Long.toString(tempsE) + "ms; ");
        
        }
        file.close();
    }
}
