package Server.System;

import Server.Request.VirtualRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSystem{
    private ServerSystem(){}
    private static final ServerSystem sys = new ServerSystem();
    
    private ServerSocket serverSocket;
    private Socket clientSocket1, clienSocket2, clienSocket3, clienSocket4;
    private PrintWriter out;
    private BufferedReader in;

    private VirtualRequest messHandler(String mess){
        return null;
    }

    private void eventHandler(VirtualRequest request){
        //HANDLE REQUEST
    }

    private void start(int port) throws IOException{
        serverSocket = new ServerSocket(port);
        
    }

    private void stop() throws IOException{
        in.close();
        out.close();
        clientSocket1.close();
        serverSocket.close();
    }


    public static ServerSystem getInstance(){
        return sys;
    }

    private Question currentQuestion = new Question();
    private int currentPlayer = 0;

    public void GetQuestion(){
        //GET NEW QUESTION FROM DATABASE FUNCTION, UPDATE CURRENT QUESTION

    }

    public boolean CheckAnswer(int ans){
        //CHECK ANSWER OF CURRENT QUESTION
        return true;
    }

    public int UpdateInfo(boolean correctAns){
        //UPDATE INFORMATION TO 4 PLAYER: send PlayerList to 4 players

        return -1;
    }

    public void sendQuestion(){
        //SEND QUESTION TO PLAYER WITH INDEX = N

    }

    public int getAnswer(){
        //GET ANSWER FROM PLAYER: RETURN TRUE(IF ANSWER IS CORRECT) OR FALSE(IF ANSWER IS INCORRECT)
        
        //WAIT FOR PLAYER'S ANSWER
        return 0;
    }

    public void waitForPlayer(){
        //  SYSTEM WAIT FOR PLAYERS, IF GAME BEGIN, THIS FUNCTION WILL RETURN
        //  REMEMBER TO UPDATE PLAYER LIST
    }

    public void play(){
        try{
            this.start(3333);

            this.stop();
        }
        catch(IOException exc){

        }
        while(true){
            this.GetQuestion();
            this.sendQuestion();
            int answer = this.getAnswer();
            boolean correctAns = this.CheckAnswer(answer);
            int endgame = this.UpdateInfo(correctAns);

            if (endgame != -1){
                //  GAME IS OVER, SERVER GOES OFF, ALL PLAYERS AFTER UPDATE INFO WILL SHOW RESULT SCREEN
                break;
            }
        }
    }
}
