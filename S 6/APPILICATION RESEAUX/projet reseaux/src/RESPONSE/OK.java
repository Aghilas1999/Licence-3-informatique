package RESPONSE;

import REQUEST.REQUEST;

public class OK implements RESPONSE {


    public OK(REQUEST req) {
        System.out.println( req.toString() + " DONE !! ");
    }
    public static String OKMessage(REQUEST request){
        return   request.toString() + " DONE ";
    }
    public static String OKMessage(String request){
        return   request  ;
    }


}
