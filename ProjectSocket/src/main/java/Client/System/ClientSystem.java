package Client.System;

import java.util.concurrent.TimeUnit;

import Client.Player.Player;
import Client.Player.PlayerList;
import Client.PlayingRoom.PlayingRoomFrame;
import Client.WaitingRoom.WaitingRoomFrame;

public class ClientSystem {
    public int gameCountDown = 3;

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

    public void countDownToGame(){
        try {
            for (int i = 0; i < this.gameCountDown; i++){
                TimeUnit.SECONDS.sleep(1);
                WaitingRoomFrame.getInstance().waitingRoom.labelOclock.setText("GAME IN " + (3-i));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void updatePlayers(){
        if (state == "playing"){
            for (int i = 0; i < 4; i++)
                PlayingRoomFrame.getInstance().playpanel.player_name[i].setText(PlayerList.getInstance().getPlayer(i).name);
        }
        else if (state == "waiting"){
            for (int i = 0; i < 4; i++)
                WaitingRoomFrame.getInstance().waitingRoom.name[i].setText(PlayerList.getInstance().getPlayer(i).name);
        }
    }

    public void sendAnswerToServer(int answer){
        // send client answer to server
        String jsonmess = "{\"event\":\"answer\",\"answer\":\"" + answer + "\"}";

        SocketHandler.getInstance().sendMessage(jsonmess);
        String result = SocketHandler.getInstance().waitForServer();
        MessageHandler.handle(result);

        // tell server that client is ready for next question
        String ready = "{\"event\":\"getQuestion\"}";
        SocketHandler.getInstance().sendMessage(ready);

        // this message is suppose to be a new question from server
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
        SocketHandler.getInstance().sendMessage("{\"event\": \"joinRoom\", \"name\":\""+ playername + "\"}");
        System.out.println("TIME>>>>>>>>>>>>>");
        String returnmess = SocketHandler.getInstance().waitForServer();
        
        System.out.println(returnmess);
        MessageHandler.handle(returnmess);
    }
}
