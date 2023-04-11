package Client.System;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketHandler {
    SocketChannel socketChannel;
    Thread receiver;
    boolean working = false;

    private SocketHandler(){}
    static private SocketHandler singleton = new SocketHandler();
    static public SocketHandler getInstance(){
        return singleton;
    }

    public void startConnection(){
        try{
            socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("localhost", 8089));
        }
        catch(Exception x){

        }

        receiver = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    working = true;

                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    while (working) {
                        System.out.println("Client is ready for next income");
                        socketChannel.read(buffer);
                        buffer.flip();
                        String message = new String(buffer.array()).trim();
                        if (message.equals("")) break;
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
            working = false;
            socketChannel.close();
            System.out.println("STOP CONNECTION");
        } catch (Exception e) {
            
        }
    }
}
