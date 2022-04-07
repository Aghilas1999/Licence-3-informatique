package REQUEST;

import DataHandler.JsonHandler;
import RESPONSE.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PUBLISH extends REQUEST {


    public PUBLISH(String message) {
        this.message = message;
    }
    public PUBLISH(){};

    @Override
    public String toString() {
        return "PUBLISH";
    }

    @Override
    public String handelRequest(String request, String username) {
        try {
            String message;
            username = username.toUpperCase();
            String[] Array = handel(request);
            if (Array == null) {
                return ERROR.ErrorMessage(this);
                //return;
            } else {
                message = Array[1];
                JsonHandler.pushMessage(message,JsonHandler.getIdFromUsername(username),"PUBLISH",getTagsFromMessage(message));

                if (Array[0].equals(username.toUpperCase())) {
                    return OK.OKMessage(this);
                } else if (Array[0].equals("USER")) {
                    return OK.OKMessage(this);
                } else if (Array[0].equals("")) {
                    return OK.OKMessage(this);
                }

            }
        } catch (Exception e) {
            return ERROR.ErrorMessage(this);

        }
        return "BAD REQUEST";
    }


    static String[] handel(String publication) {
        int index;
        String username = "", message = null;
        String[] array = new String[2];
        // array[0] will contain the username /// array[1] will contain the message
        if (publication.toUpperCase().contains("PUBLISH")) {
            if (publication.contains("@")) {
                // CASE 2 OR 3
                index = publication.indexOf("@");
                publication = publication.substring(index + 1);
                username = splitAfterAt(publication).toUpperCase();
                message = publication.substring(username.length() + 1);
            } else {
                message = publication.substring(7);
            }


        }
        if (message == null) return null;
        array[0] = username;
        array[1] = message;
        return array;

    }



    public static String splitAfterAt(String message) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Character c : message.toCharArray()) {
            if (c.equals(' ')) {
                return stringBuilder.toString();
            }
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    public static ArrayList<String> getTagsFromMessage(String str) {
        if (str.isEmpty()) return new ArrayList<>();
        Pattern MY_PATTERN = Pattern.compile("#(\\S+)");
        Matcher mat = MY_PATTERN.matcher(str);
        ArrayList<String> TAGS = new ArrayList<String>();
        while (mat.find()) {
            //System.out.println(mat.group(1));
            TAGS.add(mat.group(1));
        }
        return TAGS;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(handel("REPUBLISH @walid msg_id:3")));

    }




}
