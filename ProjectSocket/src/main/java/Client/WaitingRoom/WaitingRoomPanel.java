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
            labelOclock.setBounds(175, 15, 700, 100);
            labelOclock.setOpaque(false);
            labelOclock.setFont(new Font("Serif", Font.BOLD, 30));
            labelOclock.setForeground(Color.black);
            name[0].setFont(new Font("Serif", Font.PLAIN, 20));
            name[1].setFont(new Font("Serif", Font.PLAIN, 20));
            name[2].setFont(new Font("Serif", Font.PLAIN, 20));
            name[3].setFont(new Font("Serif", Font.PLAIN, 20));

            name[0].setText("Waiting...");
            name[1].setText("Waiting...");
            name[2].setText("Waiting...");
            name[3].setText("Waiting...");
            for (int i = 0; i < 4; i++){
                name[i].setFont(new Font(null, Font.BOLD, 40));
            }
            labelOclock.setText("GAME IN 3");
            labelOclock.setBackground(Color.BLACK);

            this.add(name[0]);
            this.add(name[1]);
            this.add(name[2]);
            this.add(name[3]);
            this.add(labelOclock);
    }

    public void Update(){
        for (int i = 0; i < PlayerList.getInstance().size(); i++){
            System.out.print("update waiting");
            name[i].setText(PlayerList.getInstance().get(i).name);
        }

        if (PlayerList.getInstance().size() == 4){
            labelOclock.setText("GAME STARTS IN 5 SECONDS");
        }
    }
}   

