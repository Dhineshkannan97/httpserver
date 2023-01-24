package server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HttpHandler extends Thread {
    static Logger logger = LoggerFactory.getLogger(Server.class);
    Socket clientSocket;

    public HttpHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        super.run();
        try {
            Date date = new Date();
            SimpleDateFormat DateFor = new SimpleDateFormat("MM/dd/yyyy");
            String stringDate = DateFor.format(date);
            logger.info("\u001B[33m" + "client send request");
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String incomingLineFromClient;
            while ((incomingLineFromClient = in.readLine()) != null) {
                System.out.println(incomingLineFromClient);
                if (incomingLineFromClient.equals(""))
                    break;
            }
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("punchline.html");
            InputStreamReader isReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(isReader);
            StringBuffer sb = new StringBuffer();
            String str;
            while ((str = reader.readLine()) != null) {
                sb.append(str);
            }
            System.out.println(sb);
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
            out.print("HTTP/1.1 200 OK\n");
            out.print("Content-Length: " + sb.length() + "\n");
            out.print("Content-Type: text/html; charset=utf-8\n");
            out.print("Date: " + stringDate + "\n");
            out.print("\n");
            out.print(sb);
            out.flush();
        } catch (Exception e) {
            logger.warn(String.valueOf(e));
        }
    }
}
