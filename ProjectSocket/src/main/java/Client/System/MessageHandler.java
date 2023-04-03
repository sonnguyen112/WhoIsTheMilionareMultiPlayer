package Client.System;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MessageHandler {
    static final String JOINING_ROOM = "1";
    static final String GET_ANWSER = "2";

    public static String handle(String mess){
        try {
            Map<String, String> map = jsonToMap(mess);
            switch (map.get("event")){
                case JOINING_ROOM:
                break;
                case GET_ANWSER:
                break;
                default:
                break;
            }

            return "";
        }
        catch(Exception ex){
            return "";
        }
    }
    
    public static Map<String, String> jsonToMap(String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = mapper.readValue(json, Map.class);
        return map;
    }
}
