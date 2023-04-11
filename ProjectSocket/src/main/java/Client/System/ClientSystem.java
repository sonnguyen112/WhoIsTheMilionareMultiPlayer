package Client.System;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import Client.Player.Player;
import Client.Player.PlayerList;
import Client.PlayingRoom.PlayingRoomFrame;
import Client.WaitingRoom.WaitingRoomFrame;

public class ClientSystem {
    public int gameCountDown = 3;

    private ClientSystem(){}
    private static final ClientSystem sys = new ClientSystem();
    public ArrayList<String> currentAnswerID = new ArrayList<>();
    public String QuestionID;


    public static ClientSystem getInstance(){
        return sys;
    }

    public String state = "join game";

    public void initPlayer(String name){
        // Player you = new Player(name);
        // PlayerList.getInstance().add(you);
    }

    public void countDownToGame(){
        try {
            for (int i = 0; i < this.gameCountDown; i++){
                TimeUnit.SECONDS.sleep(1);
                WaitingRoomFrame.getInstance().waitingRoom.labelOclock.setText("GAME IN " + (2-i));
            }

            WaitingRoomFrame.getInstance().setVisible(false);
            PlayingRoomFrame.getInstance().setVisible(true);

            System.out.println(PlayerList.getInstance().playername);
            System.out.println(PlayerList.getInstance().get(0).name);
            if (PlayerList.getInstance().playername.equals(PlayerList.getInstance().get(0).name)){
                SocketHandler.getInstance().sendMessage("{\"event\":\"getQuestion\"}");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void updatePlayers(){
        if (state == "playing"){
            for (int i = 0; i < PlayerList.getInstance().size(); i++)
                PlayingRoomFrame.getInstance().playpanel.player_name[i].setText(PlayerList.getInstance().getPlayer(i).name);
        }
        else if (state == "waiting"){
            for (int i = 0; i < PlayerList.getInstance().size(); i++)
                WaitingRoomFrame.getInstance().waitingRoom.name[i].setText(PlayerList.getInstance().getPlayer(i).name);
        }
    }

    public void sendAnswerToServer(int answer_){
        String answer = "-1";
        String quesid = ClientSystem.getInstance().QuestionID;
        if (answer_ != -1){
            answer = ClientSystem.getInstance().currentAnswerID.get(answer_);
        }


        // send client answer to server
        String jsonmess = "{\"event\":\"answer\",\"answer\":\"" + answer + "\", \"question\":" + quesid + "}";

        SocketHandler.getInstance().sendMessage(jsonmess);

        // tell server that client is ready for next question
        String ready = "{\"event\":\"getQuestion\"}";
        SocketHandler.getInstance().sendMessage(ready);
    }

    // public void receiveFromServer(){
    //     String result = SocketHandler.getInstance().waitForServer();
    //     MessageHandler.handle(result);
    // }

    public void joinGame(String playername){
        //REMEMBER TO SEND THE INFOR OF PLAYER TO SERVER HERE
        this.initPlayer(playername);
        SocketHandler.getInstance().startConnection();
        SocketHandler.getInstance().sendMessage("{\"event\": \"joinRoom\", \"name\":\""+ playername + "\"}");
    }
}
