package WaitingRoom;

import javax.swing.*;


public class WaitingRoomFrame extends JFrame{
    public WaitingRoomFrame(){
        this.add(new WaitingRoomPanel());
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        

    }
    
}
