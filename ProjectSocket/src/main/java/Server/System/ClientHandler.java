package Server.System;

import Server.Server;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private DataOutputStream dout;
    private DataInputStream din;
    private Socket socket;

    ObjectMapper objectMapper = new ObjectMapper();
    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        din = new DataInputStream(this.socket.getInputStream());
        dout = new DataOutputStream(this.socket.getOutputStream());
    }

    private void eventHandler(VirtualRequest request){
        //HANDLE REQUEST
        if (request.getEvent().equals("join_room")){
            JoinRoomRequest joinRoomRequest = (JoinRoomRequest)request;
            String playerName = joinRoomRequest.getName();
            if (Server.playerNames.contains(playerName)){
                
            }
            Server.playerNames.add(joinRoomRequest.getName());
        }
    }

    @Override
    public void run() {
        try {
            // Displaying the thread that is running
            System.out.println("New Connected " + socket.getLocalSocketAddress());
            boolean connected = true;
            while (connected){
                String msg = din.readUTF();
                VirtualRequest request = objectMapper.readValue(msg, VirtualRequest.class);
                eventHandler(request);
                if (msg.equals("stop")){
                    connected = false;
                }
                System.out.println(msg);
            }
            din.close();
            dout.close();
            socket.close();
        }
        catch (Exception e) {
            // Throwing an exception
            System.out.println("Exception is caught");
        }
    }
}
