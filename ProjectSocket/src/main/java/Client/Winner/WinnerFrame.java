package Client.Winner;

import javax.swing.*;


public class WinnerFrame extends JFrame{
    public WinnerPanel winner = new WinnerPanel();

    public WinnerFrame(){
        this.add(winner);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(false);
    }
    
    static private WinnerFrame instance = new WinnerFrame();
    public static WinnerFrame getInstance(){
        return instance;
    }
}