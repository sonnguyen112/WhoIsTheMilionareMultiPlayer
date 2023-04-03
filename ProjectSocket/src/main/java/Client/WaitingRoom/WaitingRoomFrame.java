package Client.WaitingRoom;

import javax.swing.*;


public class WaitingRoomFrame extends JFrame{
    public WaitingRoomPanel waitingRoom = new WaitingRoomPanel();

    private WaitingRoomFrame(){
        this.add(waitingRoom);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(false);
    }
    
    static private WaitingRoomFrame instance = new WaitingRoomFrame();
    public static WaitingRoomFrame getInstance(){
        return instance;
    }
}
