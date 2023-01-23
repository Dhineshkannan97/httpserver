package server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static Logger logger = LoggerFactory.getLogger(Server.class);

    public void getConnections(ServerSocket serverSocket) {
        try {
            Socket clientSocket = serverSocket.accept();
            Thread thread = new HttpHandler(clientSocket);
            thread.start();

        } catch (IOException e) {
            logger.warn("\u001B[31m" + e);
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8070);
        System.out.println("server start");
        logger.info("\u001B[34m" + "server started" + "\u001B[0m");
        while (true) {
            Server obj = new Server();
            obj.getConnections(serverSocket);
        }

    }
}
