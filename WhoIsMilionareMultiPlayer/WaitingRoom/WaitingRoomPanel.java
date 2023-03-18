package WaitingRoom;
import Common.Common;

import javax.swing.*;
import java.awt.*;


public class WaitingRoomPanel extends JLabel{
    ImageIcon image;
    JLabel label1;
    JLabel label2;
    JLabel label3;
    JLabel label4;
    JLabel labelOclock;

    public WaitingRoomPanel(){
            
            image = new ImageIcon(new ImageIcon("Image/WaitingRoomImg.jpg").getImage().getScaledInstance(Common.WIDTH, Common.HEIGHT, Image.SCALE_SMOOTH));
            this.setLayout(null);
            this.setPreferredSize(new Dimension(1100, 700));
            this.setIcon(image);
            label1= new JLabel();
            label1.setVerticalAlignment(JLabel.CENTER);
            label1.setHorizontalAlignment(JLabel.CENTER);
            label1.setBounds(100, 50,200,73);
            label1.setOpaque(true);
            label2= new JLabel();
            label2.setVerticalAlignment(JLabel.CENTER);
            label2.setHorizontalAlignment(JLabel.CENTER);
            label2.setBounds(800, 50,200,73);
            label2.setOpaque(true);
            label3= new JLabel();
            label3.setVerticalAlignment(JLabel.CENTER);
            label3.setHorizontalAlignment(JLabel.CENTER);
            label3.setBounds(100, 577,200,73);
            label3.setOpaque(true);
            label4= new JLabel();
            label4.setVerticalAlignment(JLabel.CENTER);
            label4.setHorizontalAlignment(JLabel.CENTER);
            label4.setBounds(800, 577,200,73);
            label4.setOpaque(true);
            labelOclock = new JLabel();
            labelOclock.setVerticalAlignment(JLabel.CENTER);
            labelOclock.setHorizontalAlignment(JLabel.CENTER);
            labelOclock.setBounds(500, 15, 100,100);
            labelOclock.setOpaque(false);
            labelOclock.setFont(new Font("Serif", Font.PLAIN, 40));
            labelOclock.setForeground(Color.white);
            label1.setText("Waiting...");
            label2.setText("Waiting...");
            label3.setText("Waiting...");
            label4.setText("Waiting...");
            labelOclock.setText("12:03");

            this.add(label1);
            this.add(label2);
            this.add(label3);
            this.add(label4);
            this.add(labelOclock);
    }
    
        // public void paint(Graphics g){
        //     Graphics2D g2D = (Graphics2D) g;
        //     g2D.drawImage(image, 0, 0 ,null);
        //     label1.setText("Waiting...");
        //     label2.setText("Waiting...");
        //     label3.setText("Waiting...");
        //     label4.setText("Waiting...");
        //     labelOclock.setText("12:03");
        // }
}   

