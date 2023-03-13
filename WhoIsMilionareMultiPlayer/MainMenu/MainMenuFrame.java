package MainMenu;

import javax.swing.*;

public class MainMenuFrame extends JFrame {

    JButton button;
    public MainMenuFrame(){
        button = new JButton();
        ImageIcon icon = new ImageIcon("Image/play_button");
        button.setIcon(icon);
        button.setBounds(0, 0, 100, 100);
        this.add(button);
        this.add(new MainMenuPanel());
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
