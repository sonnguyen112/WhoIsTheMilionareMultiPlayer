package Server;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Server server = new Server();
        server.run();
    }
}
