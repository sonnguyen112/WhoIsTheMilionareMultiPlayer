package MainMenu;

import javax.swing.*;

public class MainMenuFrame extends JFrame {
    public MainMenuFrame(){
        this.add(new MainMenuPanel());
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
