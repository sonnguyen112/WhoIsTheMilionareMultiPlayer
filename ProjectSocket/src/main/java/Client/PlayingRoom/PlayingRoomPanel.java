package Client.PlayingRoom;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import Client.Common.Common;
import Client.System.ClientSystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayingRoomPanel extends JLabel implements ActionListener{

    public JButton options[] = new JButton[4];
    public JLabel player_name[] = new JLabel[4];
    public JLabel questionLabel;
    JLabel clock;
    ImageIcon image;
    public JButton skipButton;
    PlayingRoomPanel(){
        image = new ImageIcon(new ImageIcon("src/main/java/Client/Image/background_2.jpg").getImage().getScaledInstance(Common.WIDTH, Common.HEIGHT, Image.SCALE_SMOOTH));
        ImageIcon skipButtonImg = new ImageIcon(new ImageIcon("src/main/java/Client/Image/skip_button.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        this.setPreferredSize(new Dimension(Common.WIDTH, Common.HEIGHT));
        this.setIcon(image);
        this.setLayout(null);

        player_name[0] = new JLabel();
        player_name[1] = new JLabel();
        player_name[2] = new JLabel();
        player_name[3] = new JLabel();
        questionLabel = new JLabel();
        options[0] = new JButton();
        options[1] = new JButton();
        options[2] = new JButton();
        options[3] = new JButton();
        clock = new JLabel();

        player_name[0].setOpaque(true);
        player_name[1].setOpaque(true);
        player_name[2].setOpaque(true);
        player_name[3].setOpaque(true);
        questionLabel.setOpaque(true);
        options[0].setOpaque(true);
        options[1].setOpaque(true);
        options[2].setOpaque(true);
        options[3].setOpaque(true);
        clock.setOpaque(true);

        player_name[0].setHorizontalAlignment(JLabel.CENTER);
        player_name[0].setVerticalAlignment(JLabel.CENTER);
        player_name[1].setHorizontalAlignment(JLabel.CENTER);
        player_name[1].setVerticalAlignment(JLabel.CENTER);
        player_name[2].setHorizontalAlignment(JLabel.CENTER);
        player_name[2].setVerticalAlignment(JLabel.CENTER);
        player_name[3].setHorizontalAlignment(JLabel.CENTER);
        player_name[3].setVerticalAlignment(JLabel.CENTER);
        questionLabel.setVerticalAlignment(JLabel.CENTER);
        questionLabel.setHorizontalAlignment(JLabel.CENTER);
        options[0].setVerticalAlignment(JLabel.CENTER);
        options[0].setHorizontalAlignment(JLabel.CENTER);
        options[1].setVerticalAlignment(JLabel.CENTER);
        options[1].setHorizontalAlignment(JLabel.CENTER);
        options[2].setVerticalAlignment(JLabel.CENTER);
        options[2].setHorizontalAlignment(JLabel.CENTER);
        options[3].setVerticalAlignment(JLabel.CENTER);
        options[3].setHorizontalAlignment(JLabel.CENTER);
        clock.setVerticalAlignment(JLabel.CENTER);
        clock.setHorizontalAlignment(JLabel.CENTER);

        player_name[0].setBounds(50, 50, 200, 50);
        player_name[1].setBounds(50, 120, 200, 50);
        player_name[2].setBounds(50, 190, 200, 50);
        player_name[3].setBounds(50, 260, 200, 50);
        questionLabel.setBounds(300, 50, 600, 260);
        JPanel optionPanel = new JPanel();
        optionPanel.setLayout(new GridLayout(2, 2, 20, 20));
        optionPanel.setOpaque(false);
        optionPanel.setBounds(50, 360, 850, 300);
        optionPanel.add(options[0]);
        optionPanel.add(options[1]);
        optionPanel.add(options[2]);
        optionPanel.add(options[3]);
        clock.setBounds(950, 130, 100, 100);
        clock.setFont(new Font(null, Font.BOLD, 50));
        clock.setForeground(Color.WHITE);
        clock.setOpaque(false);

        player_name[0].setText("NickName");
        player_name[1].setText("NickName");
        player_name[2].setText("NickName");
        player_name[3].setText("NickName");
        questionLabel.setText("Question");
        options[0].setText("Option 1");
        options[1].setText("Option 2");
        options[2].setText("Option 3");
        options[3].setText("Option 4");
        clock.setText("00");

        skipButton = new JButton();
        skipButton.setIcon(skipButtonImg);
        skipButton.setBounds(950, 460, 100, 100);
        skipButton.setOpaque(false);
        skipButton.setContentAreaFilled(false);
        skipButton.setBorderPainted(false);

        this.add(player_name[0]);
        this.add(player_name[1]);
        this.add(player_name[2]);
        this.add(player_name[3]);
        this.add(questionLabel);
        this.add(optionPanel);
        this.add(clock);
        this.add(skipButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == options[0]){
            ClientSystem.getInstance().sendAnswerToServer(0);
        }
        else if (e.getSource() == options[1]){
            ClientSystem.getInstance().sendAnswerToServer(1);
        }
        else if (e.getSource() == options[2]){
            ClientSystem.getInstance().sendAnswerToServer(2);
        }
        else if (e.getSource() == options[3]){
            ClientSystem.getInstance().sendAnswerToServer(3);
        }
        else if (e.getSource() == skipButton){
            ClientSystem.getInstance().sendAnswerToServer(-1);
        }
    }
}
