package Client.System;

import Client.Player.Player;
import Client.Player.PlayerList;

public class ClientSystem {
    private ClientSystem(){}
    private static final ClientSystem sys = new ClientSystem();
    public static ClientSystem getInstance(){
        return sys;
    }

    public final int CONNECT_TO_SERVER = 0;
    public final int SEND_ANWSER = 1;

    public void initPlayer(String name){
        Player you = new Player(name);
        PlayerList.getInstance().add(you);
    }

    private String renderMess(int type, int aws){
        switch (type){
            case CONNECT_TO_SERVER:
            return "";
            case SEND_ANWSER:
            return "";
        }
        return "";
    }

    public void sendToServer(int answer){
        SocketHandler.getInstance().sendMessage(renderMess(SEND_ANWSER, answer));
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

    public String joinGame(String playername, String ipaddr, int port){
        //REMEMBER TO SEND THE INFOR OF PLAYER TO SERVER HERE
        this.initPlayer(playername);
        SocketHandler.getInstance().startConnection(ipaddr, port);
        SocketHandler.getInstance().sendMessage("{\"event\": \"join_room\", \"name\":"+ playername + "}");
        String returnmess = SocketHandler.getInstance().waitForServer();
        return returnmess;
    }

    public String handleMessage(String servermess) {
        return null;
    }
}
