package DataHandler;

import java.util.HashMap;

public class Message {
    String message ;
    int client_id ;
    int message_id ;
    String TYPE;
    public static HashMap<Integer, Message> hashMap = new HashMap<>();

    public Message(String message,int client_id,int message_id,String TYPE){
        this.client_id = client_id;
        this.message = message;
        this.message_id = message_id;
        this.TYPE = TYPE;
    }

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", client_id=" + client_id +
                ", message_id=" + message_id +
                ", TYPE='" + TYPE + '\'' +
                '}';
    }
}
