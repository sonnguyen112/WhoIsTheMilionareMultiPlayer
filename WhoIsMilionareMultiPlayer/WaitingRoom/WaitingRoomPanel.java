package WaitingRoom;
import Common.Common;

import javax.swing.*;
import java.awt.*;
import javax.swing.JPanel;

public class WaitingRoomPanel extends JPanel{
    Image image;
    JLabel label1;
    JLabel label2;
    JLabel label3;
    JLabel label4;

    public WaitingRoomPanel(){
            image = new ImageIcon(new ImageIcon("Image/WaitingRoomImg.jpg").getImage().getScaledInstance(Common.WIDTH, Common.HEIGHT, Image.SCALE_SMOOTH)).getImage();
            this.setPreferredSize(new Dimension(1100, 700));
            label1= new JLabel();
            label1.setVerticalAlignment(JLabel.CENTER);
            label1.setHorizontalAlignment(JLabel.RIGHT);
            label1.setOpaque(true);
            
            this.add(label1);
    }
    
        public void paint(Graphics g){
            Graphics2D g2D = (Graphics2D) g;
            g2D.drawImage(image, 0, 0 ,null);
            label1.setText("Waiting...");
        }
}

