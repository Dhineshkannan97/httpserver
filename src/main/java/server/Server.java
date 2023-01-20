package server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
public class Server {
    static Logger logger = LoggerFactory.getLogger(Server.class);

    public void getConnections(ServerSocket serverSocket) {
        try {
            Socket clientSocket = serverSocket.accept();
            logger.info("\u001B[33m" + "client send request");

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String requestedResource = "";
            String incomingLineFromClient;
            while ((incomingLineFromClient = in.readLine()) != null) {
                System.out.println(incomingLineFromClient);

                if (incomingLineFromClient.contains("HTTP/1.1")) {
                    requestedResource = incomingLineFromClient;
                }

                if (incomingLineFromClient.equals(""))
                    break;
            }
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("punchline.html");
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
            BufferedInputStream bi = new BufferedInputStream(inputStream);
            System.out.println(bi.readAllBytes());
            String response = String.valueOf(bi.readAllBytes());// + requestedResource;
            out.print("HTTP/1.1 200 OK\n");
            out.print("Content-Length: " + response.length() + "\n");
            out.print("Content-Type: text/html; charset=utf-8\n");
            out.print("Date: \n");
            out.print("\n");
            out.print(response);
            out.flush();
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
