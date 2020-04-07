import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler extends Thread {
    private final Socket socket;
    private final DataInputStream inputStream;
    private final DataOutputStream outputStream;

    public ClientHandler (Socket socket, DataInputStream input, DataOutputStream out) {
        this.socket = socket;
        inputStream = input;
        outputStream = out;
    }

    @Override
    public void run() {
        String inMessages;
        String outMessages;
        while (true) {
            try {
                inMessages = inputStream.readUTF();
                if (inMessages.toUpperCase().equals("EXIT")) {
                    System.out.println("Client disconnecting");
                    break;
                }
                System.out.println("Server: Am primit mesajul \"" + inMessages + "\"");
                outMessages = "Mesaj de la server pt clientul meu drag!";
                outputStream.writeUTF(outMessages);
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
