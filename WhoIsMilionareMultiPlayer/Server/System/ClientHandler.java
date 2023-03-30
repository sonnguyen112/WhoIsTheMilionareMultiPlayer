package Server.System;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private DataOutputStream dout;
    private DataInputStream din;
    private Socket socket;
    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        din = new DataInputStream(this.socket.getInputStream());
        dout = new DataOutputStream(this.socket.getOutputStream());
    }
    @Override
    public void run() {
        try {
            // Displaying the thread that is running
            System.out.println("New Connected " + socket.getLocalSocketAddress());
            boolean connected = true;
            while (connected){
                String msg = din.readUTF();
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
