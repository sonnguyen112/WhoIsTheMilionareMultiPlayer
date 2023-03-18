

import java.awt.*;

import PlayingRoom.PlayingRoomFrame;

public class Client {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
//                 new MainMenuFrame();
//                new WaitingRoomFrame();
                new PlayingRoomFrame();
               
            }
        });
      
    }
}
