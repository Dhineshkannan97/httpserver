import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    //    static Logger logger = Logger.getLogger(Server.class.getName());
//    private static Logger logger = null;

//    static {
//        InputStream stream = Server.class.getClassLoader().
//                getResourceAsStream("logging.properties");
//        try {
//            LogManager.getLogManager().readConfiguration(stream);
//            logger= Logger.getLogger(Server.class.getName());
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
static Logger logger = LoggerFactory.getLogger(Server.class);
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(4040);
        System.out.println("server start");
        logger.info("\u001B[34m"+"server started"+"\u001B[0m");
        while (true) {
            getConnections(serverSocket);
        }

    }

    private static void getConnections(ServerSocket serverSocket) {
        try {
            Socket clientSocket = serverSocket.accept();
            logger.info("\u001B[33m"+"client send request");

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            File file = new File("C:\\Users\\Dhinesh Kannan\\IdeaProjects\\httpserver\\src\\main\\resources\\index.html");
            FileInputStream inputStream = new FileInputStream(file);
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

            out.print("HTTP/1.1 200 OK\n");
            out.print("Content-Length: " + response.length() + "\n");
            out.print("Content-Type: text/html; charset=utf-8\n");
            out.print("Date: Tue, 25 Oct 2016 08:17:59 GMT\n");
            out.print("\n");
            out.print(response);
            out.print(inputStream.readAllBytes());
            out.flush();
        } catch (IOException e) {
            logger.warn("\u001B[31m"+e);
            throw new RuntimeException(e);
        }
    }
}
