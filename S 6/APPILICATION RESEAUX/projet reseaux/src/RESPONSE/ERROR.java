package RESPONSE;

import REQUEST.*;

public class ERROR implements RESPONSE {
    public ERROR(String request){

        System.out.println("ERROR");


    }
    public ERROR(){



    }

    public static String ErrorMessage(REQUEST request){
        return   " BAD REQUEST  " +
                "ERROR OF THE "+ request.toString() ;
    }
    public static String ErrorMessage(String ERROR_MESSAGE){
        return  ERROR_MESSAGE;
    }

}
