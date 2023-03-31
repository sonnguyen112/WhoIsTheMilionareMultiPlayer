package Client.System;

import java.io.IOException;

import Client.Player.Player;
import Client.Player.PlayerList;
import Server.System.ServerSystem;

public class ClientSystem {
    private ClientSystem(){}
    private static final ClientSystem sys = new ClientSystem();
    public static ClientSystem getInstance(){
        return sys;
    }

    public void initPlayer(String name){
        Player you = new Player(name);
        PlayerList.getInstance().add(you);
    }

    public void sendToServer(int answer){

    }

    public void showQuestion(Question ques){
        // UPDATE QUESTION IN QUESTION INTERFACE
    }

    public int getAnswerFromPlayer(){
        // GET ANSWER FROM GAME INTERFACE
        return 0;
    }

    public Question receiveFromServer(){
        //GET CURRENT QUESTION FROM SERVER, IF QUESTION IS NULL, JUST RETURN NULL, THE UPDATE
        //FUNCTION FROM SERVER WILL UPDATE RESULT TO PLAYERS

        return null;
    }

    public boolean getResultUpdateFromServer(){
        //IF PLAYER IS WINNER, SHOW WIN INTERFACE

        //ELSE SHOW LOSE INTERFACE

        return false; //RETURN FALSE IF GAME IS NOT OVER, TRUE IF GAME IS OVER
    }

    public String joinGame(String playername, String ipaddr, int port) throws ClassNotFoundException{
        //REMEMBER TO SEND THE INFOR OF PLAYER TO SERVER HERE
        try {
            this.initPlayer(playername);
            SocketHandler.getInstance().startConnection(ipaddr, port);
            String mess = SocketHandler.getInstance().sendMessage("{\"event\": \"join_room\", \"name\":"+ playername + "}");
            return mess;
        }
        catch(IOException ex){
            return "null";
        }
    }

    public String handleMessage(String servermess) {
        return null;
    }
}
