package Client.System;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketHandler {
    private Socket clientSocket;
    ObjectOutputStream out = null;
    ObjectInputStream in = null;
    
    private SocketHandler(){}
    static private SocketHandler singleton = new SocketHandler();
    static public SocketHandler getInstance(){
        return singleton;
    }
    
    public void startConnection(String ip, int port) throws UnknownHostException, IOException {
        clientSocket = new Socket(ip, port);
        out = new ObjectOutputStream(clientSocket.getOutputStream());
        in = new ObjectInputStream(clientSocket.getInputStream());
    }

    public String sendMessage(String msg) throws IOException, ClassNotFoundException {
        if (msg != "") out.writeObject(msg);
        String listen = (String) in.readObject();
        return listen;
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
}
