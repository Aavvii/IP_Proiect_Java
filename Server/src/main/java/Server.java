import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(4999);
        while(true) {
            Socket client = server.accept();

            System.out.println("Client connected");

            InputStreamReader in = new InputStreamReader(client.getInputStream());
            BufferedReader bf = new BufferedReader(in);

            String str = bf.readLine();
            System.out.println("Ce am primit de la client: " + str);

            PrintWriter pr = new PrintWriter(client.getOutputStream());
            pr.println("Hello ");
            pr.flush();
        }
    }
}
