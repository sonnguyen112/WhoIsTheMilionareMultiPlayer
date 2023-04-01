package Client.System;

import java.util.Map;

public class MessageHandler {
    public static String handle(String mess){
        try {
            Map<String, String> map = jsonToMap(mess);
            switch (map.get("event")){
                case "1":
                break;
                case "2":
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
