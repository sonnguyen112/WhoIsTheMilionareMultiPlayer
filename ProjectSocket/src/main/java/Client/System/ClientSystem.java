package Client.System;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import Client.MainMenu.MainMenuFrame;
import Client.Player.PlayerList;
import Client.PlayingRoom.PlayingRoomFrame;
import Client.WaitingRoom.WaitingRoomFrame;
import Client.Winner.WinnerFrame;

public class ClientSystem {
    public int gameCountDown = 3;
    public int player_number = 2;
    private ClientSystem(){}
    private static final ClientSystem sys = new ClientSystem();
    public ArrayList<String> currentAnswerID = new ArrayList<>();
    public String QuestionID;


    public static ClientSystem getInstance(){
        return sys;
    }

    public String state = "join game";

    public void countDownToGame(){
        try {
            for (int i = 0; i < this.gameCountDown; i++){
                TimeUnit.SECONDS.sleep(1);
                WaitingRoomFrame.getInstance().waitingRoom.labelOclock.setText("GAME IN " + (2-i));
            }

            WaitingRoomFrame.getInstance().setVisible(false);
            PlayingRoomFrame.getInstance().setVisible(true);

            if (PlayerList.getInstance().playername.equals(PlayerList.getInstance().get(0).name)){
                SocketHandler.getInstance().sendMessage("{\"event\":\"getQuestion\"}");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void updatePlayers(){
        int remain = PlayerList.getInstance().size();

        if (state == "playing"){
            for (int i = 0; i < player_number; i++){
                if (i < remain)
                    PlayingRoomFrame.getInstance().playpanel.player_name[i].setText(PlayerList.getInstance().getPlayer(i).name);
                else PlayingRoomFrame.getInstance().playpanel.player_name[i].setText("ELIMINATED");
            }
            if (remain == 1 && PlayerList.getInstance().playername.equals(PlayerList.getInstance().get(0).name)){
                System.out.println("QUIT FOR WINNING");
                SocketHandler.getInstance().sendMessage("reset");
                SocketHandler.getInstance().stopConnection();
                WinnerFrame.getInstance().setVisible(true);
                WaitingRoomFrame.getInstance().setVisible(false);
                MainMenuFrame.getInstance().setVisible(false);
                PlayingRoomFrame.getInstance().setVisible(false);
                ClientSystem.getInstance().state = "join game";

                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                WinnerFrame.getInstance().setVisible(false);
                WaitingRoomFrame.getInstance().setVisible(false);
                MainMenuFrame.getInstance().setVisible(true);
                PlayingRoomFrame.getInstance().setVisible(false);
            }
        }
        else if (state == "waiting"){
            for (int i = 0; i < player_number; i++){
                if (i < remain)
                    WaitingRoomFrame.getInstance().waitingRoom.name[i].setText(PlayerList.getInstance().getPlayer(i).name);
                else WaitingRoomFrame.getInstance().waitingRoom.name[i].setText("WAITING...");
            }
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

    public void joinGame(String playername){
        //REMEMBER TO SEND THE INFOR OF PLAYER TO SERVER HERE
        SocketHandler.getInstance().startConnection();
        SocketHandler.getInstance().sendMessage("{\"event\": \"joinRoom\", \"name\":\""+ playername + "\"}");
    }

    public void QuestionTimer(){
        Thread timer = new Thread(new Runnable() {
                @Override
                public void run() {
                    int timeout = 30;
                    while (PlayerList.getInstance().answer){
                        try {
                            PlayingRoomFrame.getInstance().playpanel.clock.setText("" + timeout);
                            TimeUnit.SECONDS.sleep(1);
                            timeout--;
                            if (timeout == 0){
                                ClientSystem.getInstance().sendAnswerToServer(-2);
                                PlayerList.getInstance().answer = false;
                                break;
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        );

        timer.start();
    }
    
}
