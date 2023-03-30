package PlayingRoom;

import MainMenu.MainMenuPanel;

import javax.swing.*;

public class PlayingRoomFrame extends JFrame {
    public PlayingRoomFrame(){
        this.setTitle("Who is Milionare");
        this.add(new PlayingRoomPanel());
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
