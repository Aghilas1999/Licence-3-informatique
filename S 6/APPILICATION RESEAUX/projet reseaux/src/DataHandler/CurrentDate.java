package DataHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CurrentDate {
    public static String getDATE() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

}

