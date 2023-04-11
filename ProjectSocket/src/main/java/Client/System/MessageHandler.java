package Client.System;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.sound.midi.Soundbank;

import com.fasterxml.jackson.databind.ObjectMapper;

import Client.MainMenu.MainMenuFrame;
import Client.Player.PlayerList;
import Client.PlayingRoom.PlayingRoomFrame;
import Client.WaitingRoom.WaitingRoomFrame;
import Client.Winner.WinnerFrame;

public class MessageHandler {
    static final String JOINING_ROOM = "joinRoom";
    static final String UPDATE = "update";
    static final String GET_QUESTION = "getQuestion";
    static final String ANSWER = "answer";

    public static String handle(String mess){
        if (mess == "reset"){
            System.out.println("YOU LOSE");
            // SocketHandler.getInstance().sendMessage("quit");
            SocketHandler.getInstance().stopConnection();
            // PlayingRoomFrame.getInstance().playpanel.notification("Wrong answer, you are dead", "lose");
            WinnerFrame.getInstance().setVisible(false);
            WaitingRoomFrame.getInstance().setVisible(false);
            MainMenuFrame.getInstance().setVisible(true);
            PlayingRoomFrame.getInstance().setVisible(false);
            ClientSystem.getInstance().state = "join game";
            return "";
        }

        try {
            Map<String, Object> map = jsonToMap(mess);
            switch ((String) map.get("event")){
                case JOINING_ROOM:
                    MainMenuFrame.getInstance().menupanel.notification((String) map.get("mess"), "SERVER");
                break;
                case UPDATE:
                    PlayerList.getInstance().currentPlayer = (PlayerList.getInstance().currentPlayer + 1) % 4;
                    ArrayList<String> ne = (ArrayList<String>) map.get("mess");
                    PlayerList.getInstance().set(ne);

                    if (((String) map.get("status")).equals("success") && ClientSystem.getInstance().state == "join game"){
                        WinnerFrame.getInstance().setVisible(false);
                        WaitingRoomFrame.getInstance().waitingRoom.Update();
                        WaitingRoomFrame.getInstance().setVisible(true);
                        MainMenuFrame.getInstance().setVisible(false);
                        PlayingRoomFrame.getInstance().setVisible(false);
                        ClientSystem.getInstance().state = "waiting";
                    }
                    
                    if (ClientSystem.getInstance().state == "waiting"){
                        WaitingRoomFrame.getInstance().waitingRoom.Update();
                        if (PlayerList.getInstance().size() == ClientSystem.getInstance().player_number){
                            ClientSystem.getInstance().state = "playing";
                            ClientSystem.getInstance().countDownToGame();
                        }
                    }

                    ClientSystem.getInstance().updatePlayers();
                break;

                case GET_QUESTION:
                    String playerturn = (String) map.get("name_player");
                    for (int i = 0; i < ClientSystem.getInstance().player_number; i++){
                        if (playerturn.equals(PlayerList.getInstance().getPlayer(i).name)){
                            PlayingRoomFrame.getInstance().playpanel.player_name[i].setBackground(Color.CYAN);
                        }
                        else PlayingRoomFrame.getInstance().playpanel.player_name[i].setBackground(Color.WHITE);
                    }

                    if (PlayerList.getInstance().playername.equals(playerturn)){
                        String currentQues = (String) map.get("question");
                        ClientSystem.getInstance().QuestionID = String.valueOf(map.get("questionId"));

                        PlayerList.getInstance().answer = true;
                        Map<String, String> anws = (Map<String, String>) map.get("options");

                        System.out.println("-----------GET QUEST-------------");

                        PlayingRoomFrame.getInstance().playpanel.questionLabel.setText("<html><p>"+currentQues+"</p></html>");

                        int index = 0;
                        ClientSystem.getInstance().currentAnswerID.clear();
                        for (String id : anws.keySet()){
                            PlayingRoomFrame.getInstance().playpanel.options[index++].setText("<html><p>"+anws.get(id)+"</p></html>");
                            ClientSystem.getInstance().currentAnswerID.add(id);
                        }
                        
                        PlayerList.getInstance().answer = true;
                        ClientSystem.getInstance().QuestionTimer();
                    }
                    else {
                        for (int i = 0; i < 4; i++){
                            PlayingRoomFrame.getInstance().playpanel.options[i].setText("Nothing");
                        }
                        PlayingRoomFrame.getInstance().playpanel.questionLabel.setText("Wait for another player");
                    }
                    break;
                    
                case ANSWER:
                    Boolean iwin = (Boolean) map.get("iwin");
                    Boolean corr = (Boolean) map.get("corr");

                    PlayerList.getInstance().set((ArrayList<String>) map.get("mess"));
                    ClientSystem.getInstance().updatePlayers();

                    if (iwin){
                        System.out.println("QUIT FOR WINNING");
                        SocketHandler.getInstance().sendMessage("reset");
                        SocketHandler.getInstance().stopConnection();
                        WinnerFrame.getInstance().setVisible(true);
                        WaitingRoomFrame.getInstance().setVisible(false);
                        MainMenuFrame.getInstance().setVisible(false);
                        PlayingRoomFrame.getInstance().setVisible(false);
                        ClientSystem.getInstance().state = "join game";

                        TimeUnit.SECONDS.sleep(3);
                        WinnerFrame.getInstance().setVisible(false);
                        WaitingRoomFrame.getInstance().setVisible(false);
                        MainMenuFrame.getInstance().setVisible(true);
                        PlayingRoomFrame.getInstance().setVisible(false);
                    }
                    else if (!corr){
                        System.out.println("QUIT FOR WRONG ANSWER");
                        SocketHandler.getInstance().sendMessage("quit");
                        SocketHandler.getInstance().stopConnection();
                        // PlayingRoomFrame.getInstance().playpanel.notification("Wrong answer, you are dead", "lose");
                        WinnerFrame.getInstance().setVisible(false);
                        WaitingRoomFrame.getInstance().setVisible(false);
                        MainMenuFrame.getInstance().setVisible(true);
                        PlayingRoomFrame.getInstance().setVisible(false);
                        ClientSystem.getInstance().state = "join game";
                    }
            }

            return "";
        }
        catch(Exception ex){
            return "";
        }
    }
    
    public static Map<String, Object> jsonToMap(String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(json, Map.class);
        return map;
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









import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 8089));

        Scanner scanner = new Scanner(System.in);

        Thread sender = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    while (true) {
                        String message = scanner.nextLine();
                        buffer.put(message.getBytes());
                        buffer.flip();
                        socketChannel.write(buffer);
                        buffer.clear();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        );

        Thread receiver = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    while (true) {
                        socketChannel.read(buffer);
                        buffer.flip();
                        String message = new String(buffer.array()).trim();
                        System.out.println("Received message: " + message);
                        buffer.clear();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        sender.start();
        receiver.start();
    }
}
 * 
 */