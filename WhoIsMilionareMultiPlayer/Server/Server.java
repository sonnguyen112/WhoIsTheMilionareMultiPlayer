package Server;

import Server.System.ClientHandler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(3333);
        System.out.println("Server is running....");
        while (true){
            Socket s = ss.accept();
            Thread thread = new Thread(new ClientHandler(s));
            thread.start();
            System.out.println("ACTIVE CONNECT IS " + String.valueOf(Thread.activeCount() - 1));
        }
    }
}
