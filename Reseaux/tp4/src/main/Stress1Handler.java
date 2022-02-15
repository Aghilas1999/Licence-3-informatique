import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Stress1Handler implements Runnable {
    private final Stress1 stress1;
    public Stress1Handler(Stress1 stress1) {
        this.stress1 = stress1;
    }
  
    public void run() {
        try {
            stress1.startStress1(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}