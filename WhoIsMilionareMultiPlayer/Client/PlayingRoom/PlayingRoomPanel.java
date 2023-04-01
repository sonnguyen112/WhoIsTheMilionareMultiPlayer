package Client.PlayingRoom;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import Client.Common.Common;

import java.awt.*;

public class PlayingRoomPanel extends JLabel {

    JLabel label1;
    JLabel label2;
    JLabel label3;
    JLabel label4;
    JLabel questionLabel;
    JButton option1;
    JButton option2;
    JButton option3;
    JButton option4;
    JLabel clock;
    ImageIcon image;
    JButton skipButton;
    PlayingRoomPanel(){
        image = new ImageIcon(new ImageIcon("Client/Image/background_2.jpg").getImage().getScaledInstance(Common.WIDTH, Common.HEIGHT, Image.SCALE_SMOOTH));
        ImageIcon skipButtonImg = new ImageIcon(new ImageIcon("Client/Image/skip_button.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        this.setPreferredSize(new Dimension(Common.WIDTH, Common.HEIGHT));
        this.setIcon(image);
        this.setLayout(null);

        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        questionLabel = new JLabel();
        option1 = new JButton();
        option2 = new JButton();
        option3 = new JButton();
        option4 = new JButton();
        clock = new JLabel();

        label1.setOpaque(true);
        label2.setOpaque(true);
        label3.setOpaque(true);
        label4.setOpaque(true);
        questionLabel.setOpaque(true);
        option1.setOpaque(true);
        option2.setOpaque(true);
        option3.setOpaque(true);
        option4.setOpaque(true);
        clock.setOpaque(true);

        label1.setHorizontalAlignment(JLabel.CENTER);
        label1.setVerticalAlignment(JLabel.CENTER);
        label2.setHorizontalAlignment(JLabel.CENTER);
        label2.setVerticalAlignment(JLabel.CENTER);
        label3.setHorizontalAlignment(JLabel.CENTER);
        label3.setVerticalAlignment(JLabel.CENTER);
        label4.setHorizontalAlignment(JLabel.CENTER);
        label4.setVerticalAlignment(JLabel.CENTER);
        questionLabel.setVerticalAlignment(JLabel.CENTER);
        questionLabel.setHorizontalAlignment(JLabel.CENTER);
        option1.setVerticalAlignment(JLabel.CENTER);
        option1.setHorizontalAlignment(JLabel.CENTER);
        option2.setVerticalAlignment(JLabel.CENTER);
        option2.setHorizontalAlignment(JLabel.CENTER);
        option3.setVerticalAlignment(JLabel.CENTER);
        option3.setHorizontalAlignment(JLabel.CENTER);
        option4.setVerticalAlignment(JLabel.CENTER);
        option4.setHorizontalAlignment(JLabel.CENTER);
        clock.setVerticalAlignment(JLabel.CENTER);
        clock.setHorizontalAlignment(JLabel.CENTER);

        label1.setBounds(50, 50, 200, 50);
        label2.setBounds(50, 120, 200, 50);
        label3.setBounds(50, 190, 200, 50);
        label4.setBounds(50, 260, 200, 50);
        questionLabel.setBounds(300, 50, 600, 260);
        JPanel optionPanel = new JPanel();
        optionPanel.setLayout(new GridLayout(2, 2, 20, 20));
        optionPanel.setOpaque(false);
        optionPanel.setBounds(50, 360, 850, 300);
        optionPanel.add(option1);
        optionPanel.add(option2);
        optionPanel.add(option3);
        optionPanel.add(option4);
        clock.setBounds(950, 130, 100, 100);
        clock.setFont(new Font(null, Font.BOLD, 50));
        clock.setForeground(Color.WHITE);
        clock.setOpaque(false);

        label1.setText("NickName");
        label2.setText("NickName");
        label3.setText("NickName");
        label4.setText("NickName");
        questionLabel.setText("Question");
        option1.setText("Option 1");
        option2.setText("Option 2");
        option3.setText("Option 3");
        option4.setText("Option 4");
        clock.setText("00");

        skipButton = new JButton();
        skipButton.setIcon(skipButtonImg);
        skipButton.setBounds(950, 460, 100, 100);
        skipButton.setOpaque(false);
        skipButton.setContentAreaFilled(false);
        skipButton.setBorderPainted(false);

        this.add(label1);
        this.add(label2);
        this.add(label3);
        this.add(label4);
        this.add(questionLabel);
        this.add(optionPanel);
        this.add(clock);
        this.add(skipButton);

    }

//    public void paint(Graphics g){
//        Graphics2D g2D = (Graphics2D) g;
//        g2D.drawImage(image, 0, 0 ,null);
//        label1.setText("NickName");
//        label2.setText("NickName");
//        label3.setText("NickName");
//        label4.setText("NickName");
//        questionLabel.setText("Question");
//        option1.setText("Option 1");
//        option2.setText("Option 2");
//        option3.setText("Option 3");
//        option4.setText("Option 4");
//        clock.setText("00");
//    }
}
