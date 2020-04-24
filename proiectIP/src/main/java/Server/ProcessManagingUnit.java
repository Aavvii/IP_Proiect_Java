package Server;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
//aici e "client handlerul"-manageriem threadurile etc. Momentan nestiind cum facem am lasat varianta cu socketuri

public class ProcessManagingUnit extends Thread {
    private final Socket socket;
    private final DataInputStream inputStream;
    private final DataOutputStream outputStream;

    public ProcessManagingUnit(Socket socket, DataInputStream input, DataOutputStream out) {
        this.socket = socket;
        inputStream = input;
        outputStream = out;
    }


    public String receiveMessages() throws IOException {
        String inMessages;

        inMessages = inputStream.readUTF();
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
                    System.out.println(Thread.currentThread().getName() + " : " +"Client disconnecting");
                    break;
                }
                System.out.println(Thread.currentThread().getName() + " : " + "Mesajul primit este: " + received);
                sendMessages("Mesajul a fost receptionat.");
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