package Winner;
import Common.Common;
import javax.swing.*;
import java.awt.*;
import javax.swing.JLabel;

public class WinnerPanel extends JLabel {
    ImageIcon image;
    ImageIcon ImageIcon;
    JButton ReplayButton;
    WinnerPanel(){
        image = new ImageIcon(new ImageIcon("Image/WinnerImg.png").getImage().getScaledInstance(Common.WIDTH, Common.HEIGHT, Image.SCALE_SMOOTH));
        this.setPreferredSize(new Dimension(1100, 700));
        ReplayButton = new JButton();
        ImageIcon =new ImageIcon(new ImageIcon("Image/replayButton.png").getImage().getScaledInstance(200, 100, Image.SCALE_SMOOTH));
        ReplayButton.setIcon(ImageIcon);
        this.setIcon(image);
        ReplayButton.setBounds(900,600,200,100);
        ReplayButton.setOpaque(false);
        ReplayButton.setContentAreaFilled(false);
        ReplayButton.setBorderPainted(true);
        this.add(ReplayButton);
    }
}