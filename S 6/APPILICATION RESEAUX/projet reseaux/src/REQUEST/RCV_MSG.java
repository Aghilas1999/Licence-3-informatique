package REQUEST;

import DataHandler.JsonHandler;
import RESPONSE.*;

public class RCV_MSG extends REQUEST{
    @Override
    public String handelRequest(String message, String username) {
        try {
            String req ;
            int id = parseRequest(message);

            req = getMessageFromId(id);

            if(req == null) {
                return  ERROR.ErrorMessage("AUCUN MESSAGE AVEC CETTE ID");
            }
            else  {
                return "RCV_MSG DONE " + req;
            }
        }
        catch (Exception e){
            return ERROR.ErrorMessage(this);
        }

    }


    public static String getMessageFromId(int id){
        return  JsonHandler.getMessageFromId(id);
    }
    public static int  parseRequest(String message){
        return Integer.parseInt(message.substring(8).split(":")[1]);

    }

    @Override
    public String toString() {
        return "RCV_MSG";
    }

    public static void main(String[] args) {

    }
}
