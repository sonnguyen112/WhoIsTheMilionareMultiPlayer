package MainMenu;

import Common.Common;

import javax.swing.*;
import java.awt.*;

public class MainMenuFrame extends JFrame {

    JButton button;
    JTextField textField;
    public MainMenuFrame(){

        textField = new JTextField();
        textField.setPreferredSize(new Dimension(250,40));
        textField.setFont(new Font("Consolas",Font.PLAIN,35));
        textField.setForeground(new Color(0x00FF00));
        textField.setBackground(Color.black);
        textField.setCaretColor(Color.white); //Color of "con trỏ nhấp nháy"
        textField.setText("username");
        textField.setBounds(Common.WIDTH / 2 - 213/2, Common.HEIGHT/2 + 100, 213, 115);

        button = new JButton();
        ImageIcon icon = new ImageIcon(new ImageIcon("Image/play_button.png").getImage().getScaledInstance(213, 115, Image.SCALE_DEFAULT));
        button.setIcon(icon);
        button.setBounds(Common.WIDTH / 2 - 213/2, Common.HEIGHT/2 + 200, 213, 115);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(true);

        this.add(textField);
        this.add(button);
        this.setTitle("Who is Milionare");
        this.add(new MainMenuPanel());
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
