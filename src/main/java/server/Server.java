package server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static Logger logger = LoggerFactory.getLogger(Server.class);

    private static void getConnections(ServerSocket serverSocket) {
        try {
            Socket clientSocket = serverSocket.accept();
            logger.info("\u001B[33m" + "client send request");

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//            Server.class.getClassLoader().getResourceAsStream("index.html").toString()

//            File file = new File("C:\\Users\\Dhinesh Kannan\\Documents\\Streams\\httpserver\\src\\main\\resources\\index.html");
//            FileInputStream inputStream = new FileInputStream(file);
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

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream());

            String response = "You have requested this resource: " + requestedResource;
            logger.info(String.valueOf(response.length()));
            out.print("HTTP/1.1 200 OK\n");
            out.print("Content-Length: " + response.length() + "\n");
            out.print("Content-Type: text/html; charset=utf-8\n");
            out.print("Date: Tue, 25 Oct 2016 08:17:59 GMT\n");
            out.print("\n");
            out.print(response);
//            out.print(inputStream.readAllBytes());
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
            getConnections(serverSocket);
        }

    }
}
