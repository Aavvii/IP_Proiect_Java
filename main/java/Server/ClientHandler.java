package Server;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;

public class ClientHandler extends Thread {
    private final Socket socket;
    private final DataInputStream inputStream;
    private final DataOutputStream outputStream;

    public ClientHandler(Socket socket, DataInputStream input, DataOutputStream out) {
        this.socket = socket;
        inputStream = input;
        outputStream = out;
    }
    public static String POSTRequest() throws IOException {
        final String POST_PARAMS = "{\n" + "\"userId\": 101,\r\n" +
                "    \"id\": 101,\r\n" +
                "    \"title\": \"Test Title\",\r\n" +
                "    \"body\": \"Test Body\"" + "\n}";

        URL obj = new URL("https://szmuschi.pythonanywhere.com/api");
        HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
        postConnection.setRequestMethod("POST");
        postConnection.setRequestProperty("content-typ", "image/jpeg");
        postConnection.setDoOutput(true);
        OutputStream os = postConnection.getOutputStream();
        os.write(POST_PARAMS.getBytes());
        os.flush();
        os.close();
        int responseCode = postConnection.getResponseCode();
        //System.out.println("POST Response Code :  " + responseCode);
       // System.out.println("POST Response Message : " + postConnection.getResponseMessage());
        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    postConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in .readLine()) != null) {
                response.append(inputLine);
            } in .close();
            return response.toString();
        } else {
            return "Jsonul nu a putut fi preluat";
        }
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
                String receivedJSON=POSTRequest();
                System.out.println(receivedJSON);
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