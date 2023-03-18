package MainMenu;

import Common.Common;
import Utils.Vadiation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuFrame extends JFrame{
    public MainMenuFrame() {
        this.setTitle("Who is Milionare");
        this.add(new MainMenuPanel());
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
