package server;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(6969);
        while(true) {
            Socket client = null;
            try {
                client = server.accept();

                System.out.println("Client connected");

                DataInputStream in = new DataInputStream(client.getInputStream());
                DataOutputStream out = new DataOutputStream(client.getOutputStream());

                Thread newThread = new ClientHandler(client, in, out);

                newThread.start();
            }
            catch (Exception e) {
                client.close();
                e.printStackTrace();
            };
        }
    }
}