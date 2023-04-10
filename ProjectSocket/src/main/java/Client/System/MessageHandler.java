package Client.System;

import java.util.Map;

import javax.sound.midi.SysexMessage;

import com.fasterxml.jackson.databind.ObjectMapper;

import Client.MainMenu.MainMenuFrame;
import Client.Player.Player;
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
        try {
            Map<String, Object> map = jsonToMap(mess);
            switch ((String) map.get("event")){
                case JOINING_ROOM:
                    MainMenuFrame.getInstance().menupanel.notification((String) map.get("mess"), "SERVER");
                break;
                case UPDATE:
                    if (((String) map.get("status")).equals("success") && ClientSystem.getInstance().state == "join game"){
                        WinnerFrame.getInstance().setVisible(false);
                        WaitingRoomFrame.getInstance().setVisible(true);
                        MainMenuFrame.getInstance().setVisible(false);
                        PlayingRoomFrame.getInstance().setVisible(false);
                        ClientSystem.getInstance().state = "waiting";
                    }
                    
                    PlayerList.getInstance().set((Player[]) map.get("mess"));
                    if (ClientSystem.getInstance().state == "waiting" && PlayerList.getInstance().player_num == 4){
                        ClientSystem.getInstance().countDownToGame();
                    }

                    ClientSystem.getInstance().updatePlayers();
                break;
                case GET_QUESTION:
                    String currentQues = (String) map.get("ques");
                    String[] anws = (String[]) map.get("answer");

                    PlayingRoomFrame.getInstance().playpanel.questionLabel.setText(currentQues);
                    for (int i = 0; i < 4; i++)
                        PlayingRoomFrame.getInstance().playpanel.options[i].setText(anws[i]);
                    break;
                case ANSWER:
                    Boolean iwin = (Boolean) map.get("iwin");
                    Boolean corr = (Boolean) map.get("corr");

                    PlayerList.getInstance().set((Player[]) map.get("mess"));
                    ClientSystem.getInstance().updatePlayers();

                    if (iwin){
                        WinnerFrame.getInstance().setVisible(true);
                        WaitingRoomFrame.getInstance().setVisible(false);
                        MainMenuFrame.getInstance().setVisible(false);
                        PlayingRoomFrame.getInstance().setVisible(false);
                        ClientSystem.getInstance().state = "join game";
                        SocketHandler.getInstance().stopConnection();
                    }
                    else if (!corr){
                        WinnerFrame.getInstance().setVisible(false);
                        WaitingRoomFrame.getInstance().setVisible(true);
                        MainMenuFrame.getInstance().setVisible(false);
                        PlayingRoomFrame.getInstance().setVisible(false);
                        ClientSystem.getInstance().state = "join game";
                        SocketHandler.getInstance().stopConnection();
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