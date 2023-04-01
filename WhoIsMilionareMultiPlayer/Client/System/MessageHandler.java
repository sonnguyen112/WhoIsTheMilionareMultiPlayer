package Client.System;

import java.util.Map;

public class MessageHandler {
    public static String handle(String mess){
        return "";
    }
    
    public static Map<String, String> jsonToMap(String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = mapper.readValue(json, Map.class);
        return map;
    }
}
