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


/*
 * join room:

{
"event":"joinRoom",
"name":"$playername"
}

{
"event":"joinRoom",
"status":"fail",
"mess":"name exist/full"
}

{
"event":"update",
"status":"sucess",
"mess":[name1, name2, name3, name4]
}

client
{
"event":"getQuestion"
}

server
{
"event":"getQuestion",
"question":"$question",
"options":[$op1, $op2, $op3, $op4]
}

client
{
"event":"answer",
"answer":"$index"
}

server
{
"event":"answer",
"corr":"$bool",
"iwin":"$bool",
"mess":[name1, name2, name3, name4]
}
 * 
 */