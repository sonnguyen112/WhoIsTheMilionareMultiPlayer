package MainMenu;

import Common.Common;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class MainMenuPanel extends JLabel {

    ImageIcon image;
    MainMenuPanel(){
        image = new ImageIcon(new ImageIcon("Image/background.jpg").getImage().getScaledInstance(Common.WIDTH, Common.HEIGHT, Image.SCALE_SMOOTH));
        this.setPreferredSize(new Dimension(Common.WIDTH, Common.HEIGHT));
        this.setIcon(image);
    }

//    public void paint(Graphics g){
//        Graphics2D g2D = (Graphics2D) g;
//        g2D.drawImage(image, 0, 0 ,null);
//    }
}
