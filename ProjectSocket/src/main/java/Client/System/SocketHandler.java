package Client.System;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketHandler {
    SocketChannel socketChannel;
    String receive_message;

    private SocketHandler(){}
    static private SocketHandler singleton = new SocketHandler();
    static public SocketHandler getInstance(){
        return singleton;
    }

    public void startConnection(String ip, int port){
        try {
            socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("localhost", 8089));
        }
        catch (Exception ex){
            
        }
    }

    public void sendMessage(String msg){
        try {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put(msg.getBytes());
            buffer.flip();
            socketChannel.write(buffer);
            buffer.clear();
        } catch (Exception e) {

        }
    }

    public String waitForServer(){
        try {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            socketChannel.read(buffer);
            buffer.flip();
            String message = new String(buffer.array()).trim();
            // MessageHandler.handle(message);
            receive_message = message;
            // SocketHandler.getInstance().receive_message = message;
            buffer.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return receive_message;
    }

    public void stopConnection(){
        try {
            socketChannel.close();
        } catch (Exception e) {
            
        }
    }
}
