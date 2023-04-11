package Client.PlayingRoom;

import javax.swing.*;

public class PlayingRoomFrame extends JFrame {
    public PlayingRoomPanel playpanel = new PlayingRoomPanel();

    private PlayingRoomFrame(){
        this.setTitle("Who is Milionare");
        this.add(playpanel);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(false);
    }

    static private PlayingRoomFrame instance = new PlayingRoomFrame();
    static public PlayingRoomFrame getInstance(){
        return instance;
    }
}
