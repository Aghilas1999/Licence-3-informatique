package DataHandler;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.xml.crypto.Data;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.*;

public class JsonHandler {
    static Path fileUsers = Path.of("resources/Users.json");
    static Path fileConnectedUsers = Path.of("resources/ConnectedUser.json");
    static Path fileMessage = Path.of("resources/message.json");
    static int nbUsers, nbMessages;
    static JSONParser parser = new JSONParser();

    static {
        try {
            nbUsers = getUsersArray().size();
            nbMessages = getMessagesArray().size();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static JSONArray getUsersArray() throws IOException, org.json.simple.parser.ParseException {

        JSONParser parser = new JSONParser();
        FileReader fileReader = new FileReader(fileUsers.toFile());
        Object obj = parser.parse(fileReader);
        JSONObject object = (JSONObject) obj;
        Object jsonArray = object.get("members");
        return (JSONArray) jsonArray;
    }

    public static JSONArray getMessagesArray() throws IOException, org.json.simple.parser.ParseException {

        FileReader fileReader = new FileReader(fileMessage.toFile());
        Object obj = parser.parse(fileReader);
        JSONObject object = (JSONObject) obj;
        Object jsonArray = object.get("message");
        return (JSONArray) jsonArray;
    }

    public static String getMessageFromId(int id)  {
        try {
            for (Object o : getMessagesArray()) {
                JSONObject jsonObject = (JSONObject) o;
                if (jsonObject.get("ID_MESSAGE").equals((long) id))
                    return jsonObject.get("MESSAGE").toString();

            }
        }catch (Exception e){
            return  null;
        }

        return null;
    }
    public static String getAuthorFromMessageId(int id)  {
        try {
            String idUser;
            for (Object o : getMessagesArray()) {
                JSONObject jsonObject = (JSONObject) o;
                if (jsonObject.get("ID_MESSAGE").equals((long) id)) {
                    idUser = jsonObject.get("ID_USER").toString();
                    return  getAuthorId(Integer.parseInt(idUser));

                }

            }
        }catch (Exception e){
            return  null;
        }

        return null;
    }
    public static ArrayList<Integer> getSubscribersFromId(int id) {
        try {
            for (Object o : getUsersArray()) {
                JSONObject jsonObject = (JSONObject) o;
                if (jsonObject.get("id").equals((long) id))
                    return (ArrayList<Integer>) jsonObject.get("SUBSCRIBERS");

            }
        }catch (Exception e){
            return  null;
        }

        return null;

    }
        public static String getAuthorId(int id)  {
        try {
            for (Object o : getUsersArray()) {
                JSONObject jsonObject = (JSONObject) o;
                if (jsonObject.get("id").equals((long) id))
                    return jsonObject.get("username").toString();

            }
        }catch (Exception e){
            return  null;
        }

        return null;
    }

    public static JSONArray getConnectedUsersArray() throws IOException, ParseException, org.json.simple.parser.ParseException {

        FileReader fileReader = new FileReader(fileConnectedUsers.toFile());
        Object obj = parser.parse(fileReader);
        JSONObject object = (JSONObject) obj;
        Object jsonArray = object.get("members");
        return (JSONArray) jsonArray;
    }


    public static void printLogs() throws IOException, org.json.simple.parser.ParseException {
        for (Object o : getUsersArray()) {
            JSONObject jsonObject = (JSONObject) o;
            System.out.println("username : " + jsonObject.get("username") + " / password : " + jsonObject.get("password") + " id : " + jsonObject.get("id"));
        }
    }

    public static String[] getUserLog(String username) throws IOException, org.json.simple.parser.ParseException {
       try {

           for (Object o : getUsersArray()) {
               JSONObject jsonObject = (JSONObject) o;
               if (jsonObject.get("username").equals(username.toUpperCase())) {
                   return new String[]{jsonObject.get("password").toString(), jsonObject.get("id").toString()};
               }

           }
       }catch (Exception e ){
           return  null;
       }
        return null;
    }


    public static int getIdFromUsername(String username) {
        try {
            return Integer.parseInt(Objects.requireNonNull(getUserLog(username))[1]);
        } catch (Exception e) {
            return -1;
        }
    }


    private static Map<String, Object> formatData(String username) throws IOException, ParseException {
        Map<String, Object> data = new HashMap<>();
        data.put("username", username.toUpperCase());
        return data;
    }


    private static Map<String, Object> formatDataForUsers(String username, String password) throws IOException, ParseException {
        Map<String, Object> data = formatData(username);
        data.put("password", password);
        data.put("id", nbUsers++);
        data.put("SUBSCRIBERS", new ArrayList<>());

        return data;
    }

    private static Map<String, Object> formatDataForUsers(String username, String password,int id ,ArrayList<Integer> arrayList) throws IOException, ParseException {
        Map<String, Object> data = formatData(username);
        data.put("password", password);
        data.put("id", id);
        data.put("SUBSCRIBERS", arrayList);

        return data;
    }

    private static Map<String, Object> formatDataForConnectedUsers(String username) throws IOException, ParseException {
        Map<String, Object> data = formatData(username);
        data.put("id", getIdFromUsername(username));
        return data;

    }

    private static Map<String, Object> formatMessage(int id_user, int id_message, String message, String TYPE) {
        Map<String, Object> data = new HashMap<>();
        data.put("TYPE", TYPE);
        data.put("ID_USER", id_user);
        data.put("ID_MESSAGE", id_message);
        data.put("MESSAGE", message);
        data.put("WHEN", CurrentDate.getDATE());


        return data;

    }
    private static Map<String, Object> formatReply(int id_user, int id_message, String message, String TYPE,int id_message_reply) {
        Map<String, Object> data = new HashMap<>();
        data.put("TYPE", TYPE);
        data.put("ID_USER", id_user);
        data.put("ID_MESSAGE", id_message);
        data.put("MESSAGE", message);
        data.put("WHEN", CurrentDate.getDATE());
        data.put("ID_MESSAGE_REPLY", id_message_reply);


        return data;

    }

    private static Map<String, Object> formatMessage(int id_user, int id_message, String message, String TYPE, ArrayList<String> tags) {
        Map<String, Object> data = formatMessage(id_user, id_message, message, TYPE);
        data.put("TAGS", tags);
        return data;

    }



    private static JSONArray parser(Path path, String Obj) throws IOException, ParseException, org.json.simple.parser.ParseException {
        FileReader file = new FileReader(path.toFile());
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(file);
        JSONObject object = (JSONObject) obj;
        Object jsonArray = object.get(Obj);
        return (JSONArray) jsonArray;


    }

    public static void pushNewUser(String username, String password) throws IOException, ParseException, org.json.simple.parser.ParseException {
        JSONArray array = parser(fileUsers, "members");
        if (getIdFromUsername(username.toUpperCase()) != -1) {
            System.out.println("Username already taken ");
            return;
        }
        JSONArray jsonArray = new JSONArray();
        array.add(formatDataForUsers(username, password));
        JSONObject object = new JSONObject();
        object.put("members", array);
        BufferedWriter writerUsers = new BufferedWriter(new FileWriter(fileUsers.toFile()));
        writerUsers.write(object.toString());
        writerUsers.close();
    }


    public static void pushNewConnectedUser(String username) throws IOException, ParseException, org.json.simple.parser.ParseException {
        JSONArray array = parser(fileConnectedUsers, "members");
        array.add(formatDataForConnectedUsers(username));
        JSONObject object = new JSONObject();
        object.put("members", array);
        BufferedWriter writerUsers = new BufferedWriter(new FileWriter(fileConnectedUsers.toFile()));
        writerUsers.write(object.toString());
        writerUsers.close();
    }

    public static User getLastConnectedUser() throws IOException, ParseException, org.json.simple.parser.ParseException {
        JSONArray array = getConnectedUsersArray();
        int lastIndex = array.size();
        JSONObject object = (JSONObject) array.get(lastIndex - 1);
        Long id = (Long) object.get("id");
        String username = (String) object.get("username");
        return new User(username, id.intValue());
    }


    public static boolean login(String username, String password) {
        try {
            return Objects.equals(Objects.requireNonNull(getUserLog(username.toUpperCase()))[0], password);
        } catch (Exception e) {
            return false;
        }
    }

    public static void pushMessage(String message, String TYPE, int id_user, ArrayList<String> tags) throws IOException, ParseException, org.json.simple.parser.ParseException {
        JSONArray array = parser(fileMessage, "message");
        array.add(formatMessage(id_user, nbMessages++, message, TYPE, tags));
        JSONObject object = new JSONObject();
        object.put("message", array);
        BufferedWriter writerUsers = new BufferedWriter(new FileWriter(fileMessage.toFile()));
        writerUsers.write(object.toString());
        writerUsers.close();
    }

    public static void pushMessage(String message, String TYPE, String username, ArrayList<String> tags) throws IOException, ParseException, org.json.simple.parser.ParseException {
        int id = getIdFromUsername(username);
        pushMessage(message, TYPE, id, tags);


    }

    public static void pushMessage(String message, int id_user, String TYPE) throws IOException, ParseException, org.json.simple.parser.ParseException {
        JSONArray array = parser(fileMessage, "message");
        array.add(formatMessage(id_user, nbMessages++, message, TYPE));
        JSONObject object = new JSONObject();
        object.put("message", array);
        BufferedWriter writerUsers = new BufferedWriter(new FileWriter(fileMessage.toFile()));
        writerUsers.write(object.toString());
        writerUsers.close();
    }

    public static void pushMessage(String message, int user_id, String TYPE, ArrayList<String> TAGS) throws IOException, ParseException, org.json.simple.parser.ParseException {
        JSONArray array = parser(fileMessage, "message");
        array.add(formatMessage(user_id, nbMessages++, message, TYPE, TAGS));
        JSONObject object = new JSONObject();
        object.put("message", array);
        BufferedWriter writerUsers = new BufferedWriter(new FileWriter(fileMessage.toFile()));
        writerUsers.write(object.toString());
        writerUsers.close();
    }
    public static void pushReply(String message, int user_id, String TYPE, int id_msg_reply) throws IOException, ParseException, org.json.simple.parser.ParseException {
        JSONArray array = parser(fileMessage, "message");
        array.add(formatReply(user_id, nbMessages++, message, TYPE,id_msg_reply));
        JSONObject object = new JSONObject();
        object.put("message", array);
        BufferedWriter writerUsers = new BufferedWriter(new FileWriter(fileMessage.toFile()));
        writerUsers.write(object.toString());
        writerUsers.close();
    }





    public static HashMap<Integer, Message> getAllMessages() throws IOException, org.json.simple.parser.ParseException {
        HashMap<Integer, Message> messageHashMap = Message.hashMap;
        JSONArray array = getMessagesArray();
        String message, TYPE = null;
        int id_user, id_message;

        for (Object obj : array) {
            JSONObject jsonObject = (JSONObject) obj;
            Long temp1 = (Long) jsonObject.get("ID_USER");
            id_user = temp1.intValue();
            Long temp2 = (Long) jsonObject.get("ID_MESSAGE");
            id_message = temp2.intValue();
            message = (String) jsonObject.get("MESSAGE");
            TYPE = (String) jsonObject.get("TYPE");

            messageHashMap.put(id_user, new Message(message, id_user,id_message,TYPE));
        }
        return messageHashMap;
    }

    public static  int   pushNewSubscriber(int id_user , int id_subscriber){
        try {
            if(getSubscribersFromId(id_user) == null || getSubscribersFromId(id_user).contains(id_subscriber)) return -2;
            JSONArray array = getUsersArray();
            System.out.println(array);
            JSONObject obj;
            int index_to_remove = -1;
            String username = "", password = "";
            ArrayList<Integer> subscribers = new ArrayList<>();
            Long id_user_long = (long) id_user;
            for (int i = 0; i < array.size(); i++) {
                obj = (JSONObject) array.get(i);
                if(obj.get("id").equals(id_user_long)){
                    index_to_remove = i;
                    username = (String) obj.get("username");
                    password = (String) obj.get("password");
                    subscribers = (ArrayList<Integer>) obj.get("SUBSCRIBERS");
                }
            }
            if(index_to_remove == -1) return -1;
            JSONArray array1 = new JSONArray();
            if(subscribers.contains((long) id_subscriber)) return -2;
                subscribers.add(id_subscriber);
            for (int i = 0; i < array.size(); i++) {
                obj = (JSONObject) array.get(i);

                if(i == index_to_remove){
                    array1.add(formatDataForUsers(username, password,id_user, subscribers));
                }
                else{
                    array1.add(obj);
                }
            }
            JSONObject object = new JSONObject();
            object.put("members", array1);
            BufferedWriter writerUsers = new BufferedWriter(new FileWriter(fileUsers.toFile()));
            writerUsers.write(object.toString());
            writerUsers.close();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }




    public static void main(String[] args) throws IOException, ParseException, org.json.simple.parser.ParseException {

    }
}