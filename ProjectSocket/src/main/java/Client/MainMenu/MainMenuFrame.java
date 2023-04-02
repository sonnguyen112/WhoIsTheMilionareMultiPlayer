package Client.MainMenu;

import javax.swing.*;

import Client.Common.Common;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuFrame extends JFrame{
    public MainMenuPanel menupanel = new MainMenuPanel(); 
    private MainMenuFrame() {
        this.setTitle("Who is Milionare");
        this.add(menupanel);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(false);        
    }

    static private MainMenuFrame instance = new MainMenuFrame();
    static public MainMenuFrame getInstance(){
        return instance;
    }
}
