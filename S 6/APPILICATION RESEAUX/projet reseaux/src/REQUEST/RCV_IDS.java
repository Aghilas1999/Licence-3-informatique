package REQUEST;

import RESPONSE.*;

import java.util.Arrays;

public class RCV_IDS extends REQUEST {

    public RCV_IDS(String message) {
        this.message = message;
    }
    public RCV_IDS( ) {
    }



    @Override
    public String handelRequest(String message, String username) {
        if (message.toUpperCase().contains("RCV_IDS")) {
            System.out.println("RCV_IDS DONE ");
            new OK(new RCV_IDS(message));
        }
        new ERROR("ed");
        return  null;
    }


    @Override
    public String toString() {
        return "RCV_IDS ";
    }

    public static void main(String[] args) {
        String req = "RCV_IDS @walid ";
        String user = "", tag = "", since_id = "", limit = "";
        req = "RCV_IDS [author:@user] [tag:#tag] [since_id:id] [limit:n]";
        String[] params = req.split(" ");
        for (int i = 0; i < params.length; i++) {
            params[i] = params[i].trim();
        }
        for (String param : params) {
            if (param.contains("author")) user = param;
            if (param.contains("tag")) tag = param;
            if (param.contains("since_id")) since_id = param;
            if (param.contains("limit")) limit = param;

        }
        System.out.println(user);
        System.out.println(tag);
        System.out.println(since_id);
        System.out.println(limit);
    }

}
