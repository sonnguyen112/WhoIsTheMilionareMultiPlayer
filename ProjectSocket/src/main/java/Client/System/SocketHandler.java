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
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 1234));
        }
        catch (Exception ex){

        }
    }

    public void sendMessage(String msg){
        Thread sender = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    while (true) {
                        buffer.put(msg.getBytes());
                        buffer.flip();
                        socketChannel.write(buffer);
                        buffer.clear();
                    }
                } catch (Exception e) {

                }
            }
        }
        );

        sender.start();
    }

    public String waitForServer(){
        Thread receiver = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    while (true) {
                        socketChannel.read(buffer);
                        buffer.flip();
                        String message = new String(buffer.array()).trim();
                        SocketHandler.getInstance().receive_message = message;
                        buffer.clear();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        receiver.start();
        return receive_message;
    }

    public void stopConnection(){
        try {
            socketChannel.close();
        } catch (Exception e) {
            
        }
    }
}
