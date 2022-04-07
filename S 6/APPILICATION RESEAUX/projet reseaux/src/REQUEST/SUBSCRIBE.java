package REQUEST;

import DataHandler.JsonHandler;
import RESPONSE.ERROR;
import RESPONSE.OK;

import java.util.Objects;

public class SUBSCRIBE extends REQUEST {
    @Override
    public String handelRequest(String message, String username) {
        try {
            if (parse(message) == null || identify(message) == -1 )
                return ERROR.ErrorMessage("BAD REQUEST");
           else  if(identify(message) == 0) {
                String FOLLOW = identifyAuthor(message).toUpperCase();
                int id_follower = JsonHandler.getIdFromUsername(FOLLOW);
                int id_followed = JsonHandler.getIdFromUsername(username);

                int resp = JsonHandler.pushNewSubscriber(id_follower,id_followed);

                if(resp == 1)   return OK.OKMessage(this); //OK.OKMessage("VOUS AVEZ AJOUTER " +FOLLOW +"  A VOS ABOONÉS");
                else if(resp == -2) return ERROR.ErrorMessage("VOUS FOLLOWEZ DEJA CETTE PERSONNE Ou Cette utilisateur n'existe pas ");
                else if(resp == -1) return ERROR.ErrorMessage(this);

            }
        }catch (Exception e){
            return ERROR.ErrorMessage(this);
        }

        return "BAD REQUEST";
    }
    public static int identify(String req){
        if(req.toUpperCase().contains("SUBSCRIBE")){
            if(req.toLowerCase().contains("author:@")) return 0;
            if(req.toLowerCase().contains("tag:#")) return 1;

        }
        return -1;
    }
    public static String identifyAuthor(String req) {
        return Objects.requireNonNull(parse(req)).substring(1);


    }
    public static String identifyTag(String req) {
        return Objects.requireNonNull(parse(req)).substring(1);


    }

        public static String parse(String str){

        int indexOf = str.indexOf(":");
        if(str.substring(indexOf + 1).equals(str)) return null;
        return str.substring(indexOf+1);
    }

    public static void main(String[] args) {
        String exmple = "SUBSCRIBE author:@nabil";
        String exmple1 = "SUBSCRIBE tag:#bled";
        SUBSCRIBE subscribe = new SUBSCRIBE();
        System.out.println(subscribe.handelRequest(exmple, "walid"));


    }
}
