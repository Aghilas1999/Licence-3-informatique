package REQUEST;

import RESPONSE.ERROR;

public abstract class REQUEST {

    public String message;
    public static String handelGenRequest(String message, String username) {

        if (BeginWith(message,"REPUBLISH")) {
            REPUBLISH RePublish = new REPUBLISH();
            return  RePublish.handelRequest(message, username);
        }

        else if (BeginWith(message,"PUBLISH")) {
            PUBLISH publish = new PUBLISH();
            return  publish.handelRequest(message, username);
        }
        else if (BeginWith(message,"RCV_IDS")) {
            RCV_IDS rcv_ids = new RCV_IDS();
            return rcv_ids.handelRequest(message, username);

        }
        else if(BeginWith(message,"RCV_MSG")){
            RCV_MSG rcv_ids = new RCV_MSG();
             return  rcv_ids.handelRequest(message, username);

        }

        else if(BeginWith(message,"SUBSCRIBE")) {
            SUBSCRIBE subscribe = new SUBSCRIBE();
            return subscribe.handelRequest(message, username);

        }
        else if(BeginWith(message,"UNSUBSCRIBE")) {
            UNSUBSCRIBE unsubscribe = new UNSUBSCRIBE();
            return unsubscribe.handelRequest(message, username);
        }


        else if(BeginWith(message,"REPLY")) {
            REPLY reply = new REPLY();
            return reply.handelRequest(message, username);
        }


        else {
            return  "BAD REQUEST";
        }
    }
    public abstract  String  handelRequest(String message,String username);


    public  static  boolean BeginWith(String message , String begin){
        message = message.toUpperCase();
        begin  = begin.toUpperCase();
        for (int i = 0; i < begin.length(); i++) {
            if(message.charAt(i) != begin.charAt(i)) return  false;
        }
        return  true;
    }

    public static void main(String[] args) {

    }



}
