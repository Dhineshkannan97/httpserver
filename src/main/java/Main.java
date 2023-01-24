import client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.Server;
import java.io.IOException;
import java.net.ServerSocket;

public class Main {
    static Logger logger = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args) throws IOException, InterruptedException {

        ServerSocket serverSocket = new ServerSocket(8070);
        System.out.println("server start");
        logger.info("\u001B[34m" + "server started" + "\u001B[0m");
        Client client = new Client();
        client.clientConnection();
        Server server = new Server();
        server.getConnections(serverSocket);
        Thread.sleep(5000);
    }
}
