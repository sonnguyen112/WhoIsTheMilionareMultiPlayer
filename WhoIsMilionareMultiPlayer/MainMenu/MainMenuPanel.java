package MainMenu;

import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends JPanel {

    Image image;
    final int WIDTH = 1100;
    final int HEIGH = 700;
    MainMenuPanel(){
        image = new ImageIcon(new ImageIcon("image/background.jpg").getImage().getScaledInstance(WIDTH, HEIGH, Image.SCALE_SMOOTH)).getImage();
        this.setPreferredSize(new Dimension(1100, 700));
    }

    public void paint(Graphics g){
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(image, 0, 0 ,null);
    }
}
