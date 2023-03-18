package Winner;

import javax.swing.*;


public class WinnerFrame extends JFrame{
    public WinnerFrame(){
        this.add(new WinnerPanel());
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        

    }
    
}