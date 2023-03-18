package MainMenu;

import Common.Common;
import Utils.Vadiation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuFrame extends JFrame implements ActionListener {

    JButton button;
    JTextField textField;
    public MainMenuFrame(){

        textField = new JTextField();
        textField.setFont(new Font("Consolas",Font.PLAIN,24));
        textField.setForeground(Color.WHITE);
        textField.setBackground(Color.black);
        textField.setCaretColor(Color.white); //Color of "con trỏ nhấp nháy"
        textField.setText("Enter Your Name");
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setBounds(Common.WIDTH / 2 - 350/2, Common.HEIGHT/2 + 100, 350, 40);

        button = new JButton();
        ImageIcon icon = new ImageIcon(new ImageIcon("Image/play_button.png").getImage().getScaledInstance(213, 115, Image.SCALE_DEFAULT));
        button.setIcon(icon);
        button.setBounds(Common.WIDTH / 2 - 213/2, Common.HEIGHT/2 + 200, 213, 115);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.addActionListener(this);

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button){
            if (Vadiation.checkNickName(textField.getText())){
                System.out.println("OK");
            }
            else {
                JOptionPane.showMessageDialog(null, "The " +
                                "nickname is composed by the following characters ‘a’...’z’, ‘A’...’Z’, ‘0’...’9’, ‘_’ \n" +
                                "and the length is not longer than 10 characters", "Invalid Nickname",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
