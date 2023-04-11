package Server;/*package whatever //do not write package name here */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.xdevapi.Client;

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
    private final int NUM_PLAYER = 2;
    private Selector selector = null;
    private ArrayList<SocketChannel> clients = new ArrayList<>();
    private ByteBuffer bufferRead = ByteBuffer.allocate(1024);
    private ByteBuffer bufferWrite = ByteBuffer.allocate(1024);
    private ObjectMapper objectMapper = new ObjectMapper();
    private ArrayList<String> namePlayers = new ArrayList<>();
    private ArrayList<String> questionList = new ArrayList<>();
    private ArrayList<Integer> questionListId = new ArrayList<>();
    private ArrayList<Integer> checkedQuestionListId = new ArrayList<>();
    private ArrayList<Integer> trueAnswer = new ArrayList<>();
    Statement stmt = null;
    Connection con = null;
    int curPlayerIndex = 0;

    public Server() throws ClassNotFoundException, SQLException{
        Class.forName("org.sqlite.JDBC");  
        con=DriverManager.getConnection("jdbc:sqlite:test.db");  
        //here sonoo is database name, root is username and password  
        stmt= con.createStatement();  
    }
    // Integer indexOption=0;
    // ResultSet rs=stmt.executeQuery("select * from options WHERE isTrue =" + true);  
    // while(rs.next())  
    // {
    
    //     indexOption = rs.getInt(1);
    //     trueAnswer.add(indexOption);
    // }  

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
        bufferRead = ByteBuffer.allocate(1024);
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
        Integer indexOption=0;
        ResultSet rs=stmt.executeQuery("select * from questions");  
        while(rs.next())  
        {
            question = rs.getString(2);
            questionId = rs.getInt(1); 
            indexOption = rs.getInt(3);
            trueAnswer.add(indexOption);
            questionList.add(question);
            questionListId.add(questionId);
        }  
        String result = questionList.remove(0);
        
        return result;
        
    }

    private Boolean getCheckedCorr(Object object){
        if (trueAnswer.contains(object))
        {
            return true;
        }
        return false;
    }


    private Boolean getCheckedIwin(){
        if((questionList.isEmpty()) || (namePlayers.size()==1))
        {
            return true;
        }
        return false;
    }

    private ArrayList<String> getListName(boolean corr,Object clientIndex){
        ArrayList<String> ListName = new ArrayList<>();
        for (int i = 0; i < namePlayers.size(); i++) {
            ListName.add(namePlayers.get(i));
        }
        if(!corr)
        {
            ListName.remove(clientIndex);
        }
        return ListName;
    }
    private Integer getIndexQuestion(){
        Integer CurrentIndex =  questionListId.get(0);
        return CurrentIndex;
    }
    private  HashMap<Integer,String> getListOptions() throws ClassNotFoundException, SQLException{
        HashMap<Integer,String> ListOptions = new HashMap<>();
        String option = "";
        Integer indexOption = 0;
        checkedQuestionListId.add(questionListId.get(0));
        ResultSet rs=stmt.executeQuery("select * from options WHERE QuestionID="+questionListId.remove(0));  
        while(rs.next())  
        {
            option = rs.getString(2);
            indexOption = rs.getInt(1);
            ListOptions.put(indexOption,rs.getString(2));
            System.out.println(indexOption+"  "+option+"  "+rs.getInt(3));
        }  
        return ListOptions;
    }

    private void processData(String data, SocketChannel client) throws IOException, ClassNotFoundException, SQLException {
        if (data.equalsIgnoreCase(
                "quit")) {
            // for (int i = 0; i < clients.size(); i++) {
            client.close();
            System.out.println("Connection closed...");
            return;
        }


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
            Integer questionId = getIndexQuestion();
            response.put("questionId", questionId);
            HashMap<Integer,String> ListOptions = getListOptions();
            response.put("options", ListOptions);
            response.put("name_player", namePlayers.get(curPlayerIndex ));
            curPlayerIndex = (curPlayerIndex + 1) % NUM_PLAYER;
            String jsonResponse = objectMapper.writeValueAsString(response);
            for (int i = 0; i < clients.size(); i++) sendData(jsonResponse, clients.get(i));
        }
        if (jsonData.get("event").equals("answer")){
            HashMap<String, Object> response = new HashMap<>();
            if (checkedQuestionListId.contains(jsonData.get("question"))) {
                response.put("event", "answer");
                Boolean corr = getCheckedCorr(Integer.parseInt((String) jsonData.get("answer")));
                response.put("corr", corr);
                Boolean iwin = getCheckedIwin();
                response.put("iwin", iwin);
                ArrayList<String> ListName = getListName(corr,client);
                response.put("mess", ListName);
                String jsonResponse = objectMapper.writeValueAsString(response);
                sendData(jsonResponse, client);
                System.out.println("send: " + jsonResponse);
                
                // response = new HashMap<>();
                // response.put("event", "update");
                // response.put("status", "success");
                // response.put("mess", ListName);
                // jsonResponse = "";
                // jsonResponse = objectMapper.writeValueAsString(response);
                // for (int i = 0; i < clients.size(); i++) {
                //     sendData(jsonResponse, clients.get(i));
                // }
                return;
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
}

 
