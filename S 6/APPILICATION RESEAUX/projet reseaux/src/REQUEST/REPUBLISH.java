package REQUEST;

import DataHandler.JsonHandler;
import RESPONSE.ERROR;
import RESPONSE.OK;

import java.util.Arrays;

public class REPUBLISH extends REQUEST {
    @Override
    public String handelRequest(String message, String username) {
        try {
            username = username.toUpperCase();
            String[] Array = handel(message);

            if (Array == null) {
                return ERROR.ErrorMessage(this);
            }
            //return;

            int id = Integer.parseInt(Array[1]);
            message = JsonHandler.getMessageFromId(id);

            if (message != null) {
                JsonHandler.pushMessage(message, JsonHandler.getIdFromUsername(username), "REPUBLISH", PUBLISH.getTagsFromMessage(message));
                if (Array[0].equals(username.toUpperCase())) {
                    return OK.OKMessage(this);
                } else if (Array[0].equals("USER")) {
                    return OK.OKMessage(this);
                } else if (Array[0].equals("")) {
                    return OK.OKMessage(this);
                }

            }
            if(message == null){
                return ERROR.ErrorMessage("le message avec cette id n'existe pas");
            }

        } catch (Exception e) {
            return ERROR.ErrorMessage(this);

        }
        return "BAD REQUEST";
    }


    public static String[] handel(String message) {
        try {
            message = message.toUpperCase();
            String[] mess = PUBLISH.handel(message);
            assert mess != null;
            String msg_id = mess[1].split("MSG_ID:")[1];
            if (Integer.parseInt(msg_id) >= 0) {
                mess[1] = msg_id;
                return mess;
            }
            return null;
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public String toString() {
        return "REPUBLISH ";
    }

    public static void main(String[] args) {
        System.out.println(REPUBLISH.handelGenRequest("REPUBLISH msg_id:322", "ds"));


    }
}
