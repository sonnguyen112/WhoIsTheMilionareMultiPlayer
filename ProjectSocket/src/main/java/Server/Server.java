package Server;

import Server.System.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
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
