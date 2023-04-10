package Client.WaitingRoom;
import Client.Common.Common;
import Client.Player.PlayerList;

import javax.swing.*;
import java.awt.*;


public class WaitingRoomPanel extends JLabel{
    ImageIcon image;
    public JLabel[] name = new JLabel[4];
    public JLabel labelOclock;

    public WaitingRoomPanel(){
            
            image = new ImageIcon(new ImageIcon("Image/WaitingRoomImg.jpg").getImage().getScaledInstance(Common.WIDTH, Common.HEIGHT, Image.SCALE_SMOOTH));
            this.setLayout(null);
            this.setPreferredSize(new Dimension(1100, 700));
            this.setIcon(image);
            name[0]= new JLabel();
            name[0].setVerticalAlignment(JLabel.CENTER);
            name[0].setHorizontalAlignment(JLabel.CENTER);
            name[0].setBounds(100, 50,200,73);
            name[0].setOpaque(true);
            name[1]= new JLabel();
            name[1].setVerticalAlignment(JLabel.CENTER);
            name[1].setHorizontalAlignment(JLabel.CENTER);
            name[1].setBounds(800, 50,200,73);
            name[1].setOpaque(true);
            name[2]= new JLabel();
            name[2].setVerticalAlignment(JLabel.CENTER);
            name[2].setHorizontalAlignment(JLabel.CENTER);
            name[2].setBounds(100, 577,200,73);
            name[2].setOpaque(true);
            name[3]= new JLabel();
            name[3].setVerticalAlignment(JLabel.CENTER);
            name[3].setHorizontalAlignment(JLabel.CENTER);
            name[3].setBounds(800, 577,200,73);
            name[3].setOpaque(true);
            labelOclock = new JLabel();
            labelOclock.setVerticalAlignment(JLabel.CENTER);
            labelOclock.setHorizontalAlignment(JLabel.CENTER);
            labelOclock.setBounds(500, 15, 100,100);
            labelOclock.setOpaque(false);
            labelOclock.setFont(new Font("Serif", Font.PLAIN, 40));
            labelOclock.setForeground(Color.white);
            name[0].setText("Waiting...");
            name[1].setText("Waiting...");
            name[2].setText("Waiting...");
            name[3].setText("Waiting...");
            labelOclock.setText("12:03");

            this.add(name[0]);
            this.add(name[1]);
            this.add(name[2]);
            this.add(name[3]);
            this.add(labelOclock);
    }

    public void Update(){
        for (int i = 0; i < PlayerList.getInstance().player_num; i++){
            name[i].setText(PlayerList.getInstance().get(i).name);
        }

        if (PlayerList.getInstance().player_num == 4){
            labelOclock.setText("GAME STARTS IN 5 SECONDS");
        }
    }
}   

