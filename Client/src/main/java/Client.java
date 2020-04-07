import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("localhost", 4999);

        PrintWriter text = new PrintWriter(client.getOutputStream());
        text.println("Hello");
        text.flush();

        InputStreamReader in = new InputStreamReader(client.getInputStream());
        BufferedReader bf = new BufferedReader(in);

        String str = bf.readLine();
        System.out.println("Client: " + str);
    }
}
