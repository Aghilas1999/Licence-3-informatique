import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Stress2 {
    List<Client> client = new ArrayList<>();

    public void startStress2(int n) throws Exception {
        String msg, msg2;
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

        
        for (int i = 0; i < n; i++) {
            client.add(new Client(1234));
          
            msg = "message N°:" + Integer.toString(i);
            msg2 = "message N°:" + Integer.toString(i);
            client.get(i).start2msg(msg, msg2, i);
            client.get(i).socket.close();
        }
    }
    public static void main(String[] args) throws Exception {
        int[] n = {1, 2, 10, 100, 1000, 5000};

        FileWriter file = new FileWriter("TempsD.csv");
       
        long start, end, tempsE;
       
        file.append("1; 2; 10; 100; 1000; 5000 \n");
       
        for (int i : n){
        
            System.out.println("Test pour "+i+" commance");
            Stress2 stress2 = new Stress2();
            start = System.nanoTime();
            stress2.startStress2(i);
            end = System.nanoTime();
            tempsE = (end-start)/1000000;
            System.out.println(tempsE + "ms");
            System.out.println("Test pour "+i+" Fini");
        
        file.append(Double.toString(tempsE)+"ms; ");
    
    }
        file.close();
    }
}

