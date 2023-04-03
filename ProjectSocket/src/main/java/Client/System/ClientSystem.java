package Client.System;

import Client.Player.Player;
import Client.Player.PlayerList;

public class ClientSystem {
    private ClientSystem(){}
    private static final ClientSystem sys = new ClientSystem();
    public static ClientSystem getInstance(){
        return sys;
    }

    public String state = "join game";

    public void initPlayer(String name){
        Player you = new Player(name);
        PlayerList.getInstance().add(you);
    }

    public void sendAnswerToServer(int answer){
        //change answer to json message
        String jsonmess = "{\"event\":\"2\"}";

        SocketHandler.getInstance().sendMessage(jsonmess);
        String result = SocketHandler.getInstance().waitForServer();
        MessageHandler.handle(result);

        receiveFromServer();
    }

    public void receiveFromServer(){
        String result = SocketHandler.getInstance().waitForServer();
        MessageHandler.handle(result);
    }

    public void joinGame(String playername, String ipaddr, int port){
        //REMEMBER TO SEND THE INFOR OF PLAYER TO SERVER HERE
        this.initPlayer(playername);
        SocketHandler.getInstance().startConnection(ipaddr, port);
        SocketHandler.getInstance().sendMessage("{\"event\": \"joinRoom\", \"name\":"+ playername + "}");
        String returnmess = SocketHandler.getInstance().waitForServer();
        MessageHandler.handle(returnmess);
    }
}
