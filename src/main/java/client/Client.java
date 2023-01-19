import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;

public class Client {
    public static void main(String[] args) throws IOException {

        File file = new File("C:\\Users\\Dhinesh Kannan\\IdeaProjects\\httpserver\\src\\main\\resources\\index.html");

        try {
            Socket s = new Socket("localhost", 8080);
            System.out.println("[CONNECTED]");

            DataInputStream in = new DataInputStream(s.getInputStream());
            BufferedReader br = new BufferedReader(new FileReader(file));
            String header = "GET / HTTP/1.0\r\n" + file
                    + "Host:localhost\r\n\r\n";
            byte[] byteHeader = header.getBytes();

            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            dos.write(byteHeader, 0, header.length());
            dos.writeUTF(header);
            String res = "";

            byte[] buf = new byte[in.available()];
            in.readFully(buf);
            System.out.println("\t[READ PROCESS]");
            System.out.println("\t\tbuff length->" + buf.length);
            for (byte b : buf) {
                res += (char) b;
            }
            System.out.println("\t[/READ PROCESS]");


            System.out.println("[RES]");
            System.out.println(res);
            System.out.println("[CONN CLOSE]");

            in.close();
            dos.close();
            s.close();
        }
        catch (Exception e){
            System.err.println(e);
        }
    }
}


