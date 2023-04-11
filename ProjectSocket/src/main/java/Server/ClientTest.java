package Server;

/*package whatever //do not write package name here */
import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class ClientTest {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 8089));

        System.out.println(socketChannel.getLocalAddress());

        Scanner scanner = new Scanner(System.in);

        Thread sender = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    while (true) {
                        String message = scanner.nextLine();
                        String data = "";
                        if (message.equals("s")){
                            File myObj = new File("ProjectSocket/src/main/java/Server/test.json");
                            Scanner myReader = new Scanner(myObj);
                            while (myReader.hasNextLine()) {
                                data += myReader.nextLine();
                            }
                            System.out.println(data);
                            myReader.close();
                        }
                        buffer.put(data.getBytes());
                        buffer.flip();
                        socketChannel.write(buffer);
                        buffer.clear();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread receiver = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    while (true) {
                        socketChannel.read(buffer);
                        buffer.flip();
                        String message = new String(buffer.array()).trim();
                        System.out.println("Received message: " + message);
                        buffer.clear();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        sender.start();
        receiver.start();
    }
}
