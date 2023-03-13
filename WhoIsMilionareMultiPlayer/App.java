

import java.awt.*;

import MainMenu.MainMenuFrame;
import WaitingRoom.WaitingRoomFrame;

public class App {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() { 
                // new MainMenuFrame();
                new WaitingRoomFrame(); 
               
            }
        });
      
    }
}
