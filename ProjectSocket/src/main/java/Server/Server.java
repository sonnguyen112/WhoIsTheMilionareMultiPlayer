package Server;/*package whatever //do not write package name here */

import com.fasterxml.jackson.databind.ObjectMapper;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Server {
    private final int NUM_PLAYER = 4;
    private Selector selector = null;
    private ArrayList<SocketChannel> clients = new ArrayList<>();
    private ByteBuffer bufferRead = ByteBuffer.allocate(1024);
    private ByteBuffer bufferWrite = ByteBuffer.allocate(1024);
    private ObjectMapper objectMapper = new ObjectMapper();
    private ArrayList<String> namePlayers = new ArrayList<>();
    private ArrayList<String> questionList = new ArrayList<>();
    private ArrayList<Integer> questionListId = new ArrayList<>();
    Statement stmt = null;
    Connection con = null;

    public Server() throws ClassNotFoundException, SQLException{
        Class.forName("org.sqlite.JDBC");  
        con=DriverManager.getConnection("jdbc:sqlite:test.db");  
        //here sonoo is database name, root is username and password  
        stmt= con.createStatement();  
    }
    public void run() throws ClassNotFoundException, SQLException {
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
                    } else if (key.isReadable()) {
                        // We can run non-blocking operation
                        // READ on our client
                        handleRead(key);
                    }
                    i.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleAccept(ServerSocketChannel mySocket,
                              SelectionKey key) throws IOException {

        System.out.println("Connection Accepted..");

        // Accept the connection and set non-blocking mode
        SocketChannel client = mySocket.accept();
        client.configureBlocking(false);

        // Register that client is reading this channel
        client.register(selector, SelectionKey.OP_READ);
        clients.add(client);
        System.out.println(clients);
    }

    private void handleRead(SelectionKey key)
            throws IOException, ClassNotFoundException, SQLException {
        System.out.println("Reading client's message.");

        // create a ServerSocketChannel to read the request
        SocketChannel client = (SocketChannel) key.channel();

        client.read(bufferRead);
        bufferRead.flip();

        // Parse data from buffer to String
        String data = new String(bufferRead.array()).trim();
        if (data.length() > 0) {
            System.out.println("Received message: " + data);
            processData(data, client);
        }
        bufferWrite.clear();
        bufferRead.clear();
    }

    private void sendData(String data, SocketChannel client) throws IOException {
        bufferWrite.limit(1024);
        bufferWrite.put(data.getBytes());
        bufferWrite.flip();
        client.write(bufferWrite);
        bufferWrite.clear();
    }
    
    private  String getQuestionAction() throws ClassNotFoundException, SQLException{
        String question = ""; 
        int questionId = 0;
        ResultSet rs=stmt.executeQuery("select * from questions");  
        while(rs.next())  
        {
            question = rs.getString(2);
            questionId = rs.getInt(1); 
            System.out.println(questionId+"  "+question+"  "+rs.getInt(3));
            questionList.add(question);
            questionListId.add(questionId);
        }  
        String result = questionList.remove(0);
        
        return result;
        
    }

    private  ArrayList<String> getListOptions() throws ClassNotFoundException, SQLException{
        ArrayList<String> ListOptions = new ArrayList<>();
        String option = "";
        ResultSet rs=stmt.executeQuery("select * from options WHERE QuestionID="+questionListId.remove(0));  
        while(rs.next())  
        {
            option = rs.getString(2);
            ListOptions.add(rs.getString(2));
            System.out.println(rs.getInt(1)+"  "+option+"  "+rs.getInt(3));
        }  
        return ListOptions;
    }

    private void processData(String data, SocketChannel client) throws IOException, ClassNotFoundException, SQLException {
        HashMap<String, Object> jsonData = objectMapper.readValue(data, HashMap.class);
        if (jsonData.get("event").equals("joinRoom")) {
            HashMap<String, Object> response = new HashMap<>();
            if (namePlayers.contains((String) jsonData.get("name"))) {
                response.put("event", "joinRoom");
                response.put("status", "FAIL");
                response.put("mess", "name exist");
                String jsonResponse = objectMapper.writeValueAsString(response);
                sendData(jsonResponse, client);
                return;
            } else if (namePlayers.size() >= NUM_PLAYER) {
                response.put("event", "joinRoom");
                response.put("status", "FAIL");
                response.put("mess", "name full");
                String jsonResponse = objectMapper.writeValueAsString(response);
                sendData(jsonResponse, client);
                return;
            } else {
                namePlayers.add((String) jsonData.get("name"));
                response.put("event", "update");
                response.put("status", "success");
                response.put("mess", namePlayers);
                System.out.println("Response: " + response);
                String jsonResponse = objectMapper.writeValueAsString(response);
                for (int i = 0; i < clients.size(); i++) {
                    sendData(jsonResponse, clients.get(i));
                }
                System.out.println("List Name: " + namePlayers);
                return;
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
            sendData(jsonResponse, client);
        }
        if (data.equalsIgnoreCase(
                "quit")) {
            for (int i = 0; i < clients.size(); i++) {
                clients.get(i).close();
            }
            System.out.println("Connection closed...");
        }
    }
}
