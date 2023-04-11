package Client.System;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketHandler {
    SocketChannel socketChannel;
    String receive_message;
    Thread receiver;

    private SocketHandler(){
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("localhost", 8089));
        } catch (Exception e) {
        }
        receiver = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    while (true) {
                        socketChannel.read(buffer);
                        buffer.flip();
                        String message = new String(buffer.array()).trim();
                        System.out.println("Received message: " + message);

                        if (message != null) MessageHandler.handle(message);
                        buffer = ByteBuffer.allocate(1024);
                    }
                } catch (Exception e) {
                }
            }
        });

        receiver.start();
    }
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
            System.out.println("client sends: " + new String(buffer.array()).trim());
            socketChannel.write(buffer);
            buffer.clear();
        } catch (Exception e) {

        }
    }

    public void stopConnection(){
        try {
            socketChannel.close();
        } catch (Exception e) {
            
        }
    }
}
