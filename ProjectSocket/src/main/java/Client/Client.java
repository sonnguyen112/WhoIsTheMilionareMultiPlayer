package Client;


import java.awt.*;

import Client.MainMenu.MainMenuFrame;
import Client.PlayingRoom.PlayingRoomFrame;

public class Client {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainMenuFrame.getInstance().setVisible(true);;        
            }
        });
      
    }
}
