package client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

public class Client {
    static Logger logger = LoggerFactory.getLogger(Client.class);

    public static void main(String[] args) {

        File file = new File( Client.class.getClassLoader().getResourceAsStream("index.html").toString());

        try {
            Socket s = new Socket("localhost", 8070);
            logger.info("[CONNECTED]");

            DataInputStream in = new DataInputStream(s.getInputStream());
//            BufferedReader br = new BufferedReader(new FileReader(file));
            String header = "GET / HTTP/1.0\r\n" + file+" hlo all"
                    + "Host:localhost\r\n\r\n";
            byte[] byteHeader = header.getBytes();
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            dos.write(byteHeader, 0, header.length());
            dos.writeUTF(header);
            String res = "";

            byte[] buf = new byte[in.available()];
            in.readFully(buf);
            logger.info("\t[READ PROCESS]");
            logger.info("\t\tbuff length->" + buf.length);
            for (byte b : buf) {
                res += (char) b;
            }
            logger.info("\t[/READ PROCESS]");


            logger.info("[RES]");
            logger.info(res);
            logger.info("[CONN CLOSE]");

            in.close();
            dos.close();
            s.close();
        } catch (Exception e) {
            logger.warn(String.valueOf(e));
        }
    }
}


