package client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Client {
    static Logger logger = LoggerFactory.getLogger(Client.class);

    public static void main(String[] args) {

        try {
            String si = "Hlo From Client";
            Socket s = new Socket("localhost", 8070);
            logger.info("[CONNECTED]");
            DataInputStream in = new DataInputStream(s.getInputStream());
            String header = "GET / HTTP/1.0\r\n" + si +
                    "Host:localhost\r\n\r\n";
            byte[] byteHeader = header.getBytes();
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            dos.write(byteHeader, 0, header.length());
            dos.writeUTF(header);
            String res = "";
            logger.info("\t[READ PROCESS]");
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


