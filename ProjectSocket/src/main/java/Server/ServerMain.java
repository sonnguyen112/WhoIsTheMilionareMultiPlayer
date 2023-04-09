package Server;/*package whatever //do not write package name here */
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import netscape.javascript.JSObject;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import org.w3c.dom.events.Event;

public class ServerMain {
    private static Selector selector = null;
    private static ArrayList<SocketChannel> clients = new ArrayList<>();
    private static ByteBuffer bufferRead = ByteBuffer.allocate(1024);
    private static ByteBuffer bufferWrite = ByteBuffer.allocate(1024);
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static ArrayList<String> namePlayers = new ArrayList<>();
    private static int turn = 1;
        
    public static void main(String[] args) throws ClassNotFoundException,SQLException
    {
        Class.forName("com.mysql.jdbc.Driver");  
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/internet1db","root","Khoi2921432");  
        //here sonoo is database name, root is username and password  
        Statement stmt= con.createStatement();  
        ResultSet rs=stmt.executeQuery("select * from questions where ID ="+turn);  
        if(rs.next())  
        System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getInt(3));  
        // con.close();  
        try {
            selector = Selector.open();
            // We have to set connection host,port and
            // non-blocking mode
            ServerSocketChannel serverSocketChannel
                    = ServerSocketChannel.open();
            ServerSocket serverSocket
                    = serverSocketChannel.socket();
            serverSocket.bind(
                    new InetSocketAddress("localhost", 8089));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                selector.select();
                Set<SelectionKey> selectedKeys
                        = selector.selectedKeys();
                Iterator<SelectionKey> i
                        = selectedKeys.iterator();

                while (i.hasNext()) {
                    SelectionKey key = i.next();

                    if (key.isAcceptable()) {
                        // New client has been accepted
                        handleAccept(serverSocketChannel,
                                key);
                    }
                    else if (key.isReadable()) {
                        // We can run non-blocking operation
                        // READ on our client
                        handleRead(key);
                    }
                    i.remove();
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleAccept(ServerSocketChannel mySocket,
                                     SelectionKey key) throws IOException
    {

        System.out.println("Connection Accepted..");

        // Accept the connection and set non-blocking mode
        SocketChannel client = mySocket.accept();
        client.configureBlocking(false);

        // Register that client is reading this channel
        client.register(selector, SelectionKey.OP_READ);
        clients.add(client);
        System.out.println(clients);
    }

    private static void handleRead(SelectionKey key)
            throws IOException, ClassNotFoundException, SQLException
    {
        System.out.println("Reading client's message.");

        // create a ServerSocketChannel to read the request
        SocketChannel client = (SocketChannel)key.channel();

        client.read(bufferRead);
        bufferRead.flip();
        bufferWrite.put("KKK".getBytes());
        bufferWrite.flip();
        for (int i = 0; i < clients.size(); i++){
            System.out.println(clients.get(i));
            clients.get(i).write(bufferWrite);
            bufferWrite.flip();
        }

        // Parse data from buffer to String
        String data = new String(bufferRead.array()).trim();
        if (data.length() > 0) {
            System.out.println("Received message: " + data);
            processData(data);
        }
        bufferWrite.clear();
        bufferRead.clear();
    }

    private static String getQuestionAction() throws ClassNotFoundException, SQLException{
        String question = ""; 
        Class.forName("com.mysql.jdbc.Driver");  
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/internet1db","root","Khoi2921432");  
        //here sonoo is database name, root is username and password  
        Statement stmt= con.createStatement();  
        ResultSet rs=stmt.executeQuery("select * from questions where ID ="+turn);  
        while(rs.next())  
        {
            question = rs.getString(2);
            System.out.println(rs.getInt(1)+"  "+question+"  "+rs.getInt(3));
        }  
        
        con.close();  
        
        return question;
        
    }

    private static ArrayList<String> getListOptions() throws ClassNotFoundException, SQLException{
        ArrayList<String> ListOptions = new ArrayList<>();
        String option = "";
        Class.forName("com.mysql.jdbc.Driver");  
        System.out.println("hello Son");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/internet1db","root","Khoi2921432");  
        //here sonoo is database name, root is username and password  
        Statement stmt= con.createStatement();  
        ResultSet rs=stmt.executeQuery("select * from options where questionId="+turn);  
        while(rs.next())  
        {
            option = rs.getString(2);
            ListOptions.add(rs.getString(2));
            System.out.println(rs.getInt(1)+"  "+option+"  "+rs.getInt(3));
        }  
        
        con.close();  
        return ListOptions;
    }

    private static void sendData(String data, SocketChannel client) throws IOException {
        bufferWrite.put(data.getBytes());
        bufferWrite.flip();
        client.write(bufferWrite);
        bufferWrite.clear();
    }

    private static void processData(String data) throws IOException, ClassNotFoundException, SQLException {
        HashMap<String, Object> jsonData = objectMapper.readValue(data, HashMap.class);
        if (jsonData.get("event").equals("joinRoom")){
            HashMap<String, Object> response = new HashMap<>();
            response.put("event", "joinRoom");
            if (namePlayers.contains((String) jsonData.get("name"))){
                response.put("status", "FAIL");
            }
            else {
                namePlayers.add((String) jsonData.get("name"));
            }
            response.put("status", "OK");
            String jsonResponse = objectMapper.writeValueAsString(response);
            for (int i =0; i < clients.size(); i++){
                sendData(jsonResponse, clients.get(i));
            }
        }
        if (jsonData.get("event").equals("getQuestion")){
            HashMap<String, Object> response = new HashMap<>();
            response.put("event","getQuestion");
            String question = getQuestionAction();
            response.put("question", question);
            ArrayList<String> ListOptions = getListOptions();
            response.put("options", ListOptions);
            String jsonResponse = objectMapper.writeValueAsString(response);
            for (int i =0; i < clients.size(); i++){
                sendData(jsonResponse, clients.get(i));
            }
        }
        if (data.equalsIgnoreCase(
                "quit")) {
            for (int i = 0; i < clients.size(); i++){
                clients.get(i).close();
            }
            System.out.println("Connection closed...");
        }
    }
}
