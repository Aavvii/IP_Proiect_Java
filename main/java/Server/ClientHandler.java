package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler extends Thread {
    private final Socket socket;
    private final DataInputStream inputStream;
    private final DataOutputStream outputStream;

    public ClientHandler(Socket socket, DataInputStream input, DataOutputStream out) {
        this.socket = socket;
        inputStream = input;
        outputStream = out;
    }

    public String receiveMessages() throws IOException {
        String inMessages;

        inMessages = inputStream.readUTF();
        System.out.println("Am citit " + inMessages);
        return inMessages;
    }

    public void sendMessages(String message) throws IOException {
        String outMessages;
        outMessages = message;
        outputStream.writeUTF(outMessages);
    }

    @Override
    public void run() {
        while (true) {
            try
            {
                String received = receiveMessages();
                if (received.toUpperCase().equals("EXIT")) {
                    System.out.println("Client disconnecting");
                    break;
                }
                System.out.println("mesajul primit a fost: " + received);
                sendMessages("am citit mesajul tau");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            this.inputStream.close();
            this.outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}