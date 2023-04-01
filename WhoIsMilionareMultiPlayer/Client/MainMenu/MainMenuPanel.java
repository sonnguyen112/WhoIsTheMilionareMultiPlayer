package Client.MainMenu;

import javax.swing.*;

import Client.Common.Common;
import Client.PlayingRoom.PlayingRoomFrame;
import Client.System.ClientSystem;
import Client.Utils.Vadiation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class MainMenuPanel extends JLabel implements ActionListener {

    ImageIcon background_main;
    JButton button;
    JTextField textField;
    MainMenuPanel(){

        background_main = new ImageIcon(new ImageIcon("Image/background.jpg").getImage().getScaledInstance(Common.WIDTH, Common.HEIGHT, Image.SCALE_SMOOTH));
        this.setPreferredSize(new Dimension(Common.WIDTH, Common.HEIGHT));
        this.setIcon(background_main);
        this.setLayout(null);

        textField = new JTextField();
        textField.setFont(new Font("Consolas",Font.PLAIN,24));
        textField.setForeground(Color.WHITE);
        textField.setBackground(Color.black);
        textField.setCaretColor(Color.white);
        textField.setText("Enter Your Name");
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setBounds(Common.WIDTH / 2 - 350/2, Common.HEIGHT/2 + 100, 350, 40);

        button = new JButton();
        ImageIcon icon = new ImageIcon(new ImageIcon("Image/play_button.jpg").getImage().getScaledInstance(213, 115, Image.SCALE_DEFAULT));
        button.setIcon(icon);
        button.setBounds(Common.WIDTH / 2 - 213/2, Common.HEIGHT/2 + 200, 213, 115);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.addActionListener(this);

        this.add(textField);
        this.add(button);

    }

    private void notification(String mess, String type){
        JOptionPane.showMessageDialog(null, mess, type, JOptionPane.WARNING_MESSAGE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button){
            String playerName = textField.getText();
            if (Vadiation.checkNickName(playerName)){
                System.out.println("OK");
                try {
                    String servermess = ClientSystem.getInstance().joinGame(playerName, "127.0.0.1", 1234);
                    servermess = ClientSystem.getInstance().handleMessage(servermess);
                    switch (servermess){
                        case "FAIL":
                            this.notification("ROOM IS FULL", "FAIL");
                            break;
                        case "SUCESS":
                            this.setVisible(false);
                            new PlayingRoomFrame();
                            break;
                    }
                } catch (ClassNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
            else {
                this.notification("The nickname is composed by the following characters ‘a’...’z’, ‘A’...’Z’, ‘0’...’9’, ‘_’ \n" +
                                "and the length is not longer than 10 characters", "Invalid Nickname");
            }
        }
    }
//    public void paint(Graphics g){
//        Graphics2D g2D = (Graphics2D) g;
//        g2D.drawImage(image, 0, 0 ,null);
//    }
}
