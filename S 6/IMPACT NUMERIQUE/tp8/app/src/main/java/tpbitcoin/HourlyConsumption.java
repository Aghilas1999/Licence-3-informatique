package tpbitcoin;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class HourlyConsumption {
    private final long value; //in Mwh
    private final LocalDateTime startTime; // ISO_LOCAL format
    private final LocalDateTime endTime;

    public HourlyConsumption(long value, LocalDateTime startTime, LocalDateTime endTime) {
        this.value = value;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    //return consumption in kwh
    public long getConsumption(){
        return value * 1000;
    }


    // TODO
    public static long getFinnishConsumptionLast24h(String apiKey){
        return 10L;
    }

    // TODO
    public static List<HourlyConsumption> getFinnishConsumption(LocalDateTime startTime, LocalDateTime endTime, String apiKey){
        return null;
    }

    // TODO
    private static String queryFinnishAPI(LocalDateTime startTime, LocalDateTime endTime, String apiKey){
        return "json string";
    }

}
