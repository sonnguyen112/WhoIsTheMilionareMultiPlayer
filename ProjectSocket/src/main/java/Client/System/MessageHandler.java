package Client.System;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import Client.MainMenu.MainMenuFrame;
import Client.PlayingRoom.PlayingRoomFrame;

public class MessageHandler {
    static final String JOINING_ROOM = "1";
    static final String GET_QUESTION = "2";
    static final String END_GAME = "3";
    static final String UPDATE_INFO = "4";

    public static String handle(String mess){
        try {
            Map<String, String> map = jsonToMap(mess);
            switch (map.get("event")){
                case JOINING_ROOM:
                //if sucess
                if (map.get("sucess") == "OK"){
                    MainMenuFrame.getInstance().setVisible(false);
                    PlayingRoomFrame.getInstance().setVisible(true);
                }
                else {
                    MainMenuFrame.getInstance().menupanel.notification("CANNOT JOIN THE GAME", "FAIL TO JOIN GAME");
                }
                break;
                case GET_QUESTION:
                    String currentQues = map.get("ques");
                    String anws0 = map.get("answer0");
                    String anws1 = map.get("answer1");
                    String anws2 = map.get("answer2");
                    String anws3 = map.get("answer3");

                    PlayingRoomFrame.getInstance().playpanel.questionLabel.setText(currentQues);
                    PlayingRoomFrame.getInstance().playpanel.options[0].setText(anws0);
                    PlayingRoomFrame.getInstance().playpanel.options[1].setText(anws1);
                    PlayingRoomFrame.getInstance().playpanel.options[2].setText(anws2);
                    PlayingRoomFrame.getInstance().playpanel.options[3].setText(anws3);
                    break;
                case END_GAME:
                    
                    break;
                case UPDATE_INFO:
                    //update player score
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
