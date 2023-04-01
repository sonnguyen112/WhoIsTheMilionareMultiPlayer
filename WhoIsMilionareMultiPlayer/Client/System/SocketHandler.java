package Client.System;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketHandler {
    private Socket clientSocket;
    ObjectOutputStream out = null;
    ObjectInputStream in = null;
    
    private SocketHandler(){}
    static private SocketHandler singleton = new SocketHandler();
    static public SocketHandler getInstance(){
        return singleton;
    }
    
    public void startConnection(String ip, int port){
        try {
            clientSocket = new Socket(ip, port);
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());
        }
        catch (Exception ex){

        }
    }

    public String sendMessage(String msg){
        try {
        if (msg != "") out.writeObject(msg);
        String listen = (String) in.readObject();
        return listen;
        }
        catch (Exception ex){
            return "";
        }
    }

    public void stopConnection(){
        try {
            in.close();
            out.close();
            clientSocket.close();
        }
        catch (Exception ex){

        }
    }
}
