package MainMenu;

import Common.Common;

import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends JPanel {

    Image image;
    MainMenuPanel(){
        image = new ImageIcon(new ImageIcon("Image/background.jpg").getImage().getScaledInstance(Common.WIDTH, Common.HEIGHT, Image.SCALE_SMOOTH)).getImage();
        this.setPreferredSize(new Dimension(1100, 700));
    }

    public void paint(Graphics g){
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(image, 0, 0 ,null);
    }
}
