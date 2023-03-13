package WaitingRoom;
import Common.Common;

import javax.swing.*;
import java.awt.*;
import javax.swing.JPanel;

public class WaitingRoomPanel extends JPanel{
    Image image;
    public WaitingRoomPanel(){
            image = new ImageIcon(new ImageIcon("Image/WaitingRoomImg.jpg").getImage().getScaledInstance(Common.WIDTH, Common.HEIGHT, Image.SCALE_SMOOTH)).getImage();
            this.setPreferredSize(new Dimension(1100, 700));
            JLabel label= new JLabel();
            label.setVerticalAlignment(JLabel.CENTER);
            label.setHorizontalAlignment(JLabel.RIGHT);
            label.setOpaque(true);
            label.setText("Waiting...");
            this.add(label);
    }
    
        public void paint(Graphics g){
            super.paint(g);
            Graphics2D g2D = (Graphics2D) g;
            g2D.drawImage(image, 0, 0 ,null);
        }
}

