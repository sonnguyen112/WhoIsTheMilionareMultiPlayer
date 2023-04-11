package Client.Winner;
import Client.Common.Common;
import Client.MainMenu.MainMenuFrame;
import Client.PlayingRoom.PlayingRoomFrame;
import Client.WaitingRoom.WaitingRoomFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

public class WinnerPanel extends JLabel implements ActionListener{
    ImageIcon image;
    ImageIcon ImageIcon;
    JButton ReplayButton;
    WinnerPanel(){
        image = new ImageIcon(new ImageIcon("src/main/java/Client/Image/WinnerImg.png").getImage().getScaledInstance(Common.WIDTH, Common.HEIGHT, Image.SCALE_SMOOTH));
        this.setPreferredSize(new Dimension(1100, 700));
        ReplayButton = new JButton();
        ImageIcon =new ImageIcon(new ImageIcon("src/main/java/Client/Image/replayButton.png").getImage().getScaledInstance(200, 100, Image.SCALE_SMOOTH));
        ReplayButton.setIcon(ImageIcon);
        this.setIcon(image);
        ReplayButton.setBounds(900,600,200,100);
        ReplayButton.setOpaque(false);
        ReplayButton.setContentAreaFilled(false);
        ReplayButton.setBorderPainted(true);
        this.add(ReplayButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ReplayButton){
            WinnerFrame.getInstance().setVisible(false);
            MainMenuFrame.getInstance().setVisible(true);
        }
    }


}