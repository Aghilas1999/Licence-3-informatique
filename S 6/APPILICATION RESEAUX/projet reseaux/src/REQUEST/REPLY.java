package REQUEST;

import DataHandler.JsonHandler;
import RESPONSE.ERROR;
import RESPONSE.OK;


import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class REPLY extends REQUEST {
    PUBLISH publish = new PUBLISH();

    @Override
    public String handelRequest(String request, String username) {
        try {
            String message , author ;
            int id_message;
            username = username.toUpperCase();
            String[] Array = handel(request);
            if (Array == null) {
                return ERROR.ErrorMessage(this);
                //return;
            } else if(Array != null) {
                author = Array[0];
                id_message = Integer.parseInt(Array[1]);
                message = Array[2];
                if(JsonHandler.getUserLog(author) == null)  return ERROR.ErrorMessage("AUCUN UTILISATEUR AVEC LE PSEUDO" + author );
                else if(JsonHandler.getMessageFromId(id_message) == null) return ERROR.ErrorMessage("AUCUN MESSAGE AVEC CETTE ID N'EXISTE") ;
                else if(!Objects.equals(JsonHandler.getAuthorFromMessageId(id_message), author.toUpperCase())) return ERROR.ErrorMessage(author+" n'a pas de message avec cet id ");

                JsonHandler.pushReply(message,JsonHandler.getIdFromUsername(username),"REPLY",id_message);
                return OK.OKMessage(this);


            }
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR.ErrorMessage(this);

        }
        return "BAD REQUEST";
    }

    static String[] handel(String publication) {
        int index;
        String username = "", id = null, message = "";
        String[] array = new String[3];
        // array[0] will contain the username /// array[1] will contain the message
        if (publication.toUpperCase().contains("REPLY")) {
            if (publication.contains("@")) {
                // CASE 2 OR 3
                index = publication.indexOf("@");
                publication = publication.substring(index + 1);
                username = PUBLISH.splitAfterAt(publication).toUpperCase();
                publication = publication.substring(publication.indexOf(username) + username.length() + 2);

                String[] IdMessage = parseIdMessage(publication);
                id = IdMessage[0];
                message = IdMessage[1];
            }

        }
        if (id == null || username.equals("") || Objects.equals(message, "")) return null;
        array[0] = username.toUpperCase();
        array[1] = id;
        array[2] = message;

        return array;

    }

    public static String[] parseIdMessage(String message) {
        String[] result = new String[2];
        Matcher matcher = Pattern.compile("\\d+").matcher(message);
        matcher.find();
        String id = matcher.group();
        int index = message.indexOf(id) + id.length() + 1;
        result[0] = id;
        result[1] = message.substring(index).strip();
        return result;


    }

    @Override
    public String toString() {
        return "REPLY ";
    }

    public static void main(String[] args) {
        REPLY  reply = new REPLY();
        System.out.println(reply.handelRequest("reply author:@walid reply_id:2 malkoum", "walid"));
    }



}
