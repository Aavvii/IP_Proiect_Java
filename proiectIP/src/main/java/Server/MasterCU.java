package Server;
import org.json.JSONObject;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
//asta e partea princiala care comunica cu toate celelalte componente
//am lasat din nu partea cu socket, aici trebuie apelate functiile din celelalte clase, facute verificarile etc
//concurenta cu processManagingUnit
public class MasterCU {

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket server = new ServerSocket(6969);
        DatabaseCU databaseCU=new DatabaseCU();
        JSONObject obj = new JSONObject();
        obj.put("name", "foo");
        obj.put("num", new Integer(100));
        obj.put("balance", new Double(1000.21));
        obj.put("is_vip", new Boolean(true));
        System.out.println(databaseCU.databaseCommunication(obj).toString());
        while(true) {
            Socket client = null;
            try {
                client = server.accept();

                System.out.println("Client connected");

                DataInputStream in = new DataInputStream(client.getInputStream());
                DataOutputStream out = new DataOutputStream(client.getOutputStream());

                Thread newThread = new ProcessManagingUnit(client, in, out);

                newThread.start();
            }
            catch (Exception e) {
                client.close();
                e.printStackTrace();
            };
        }
    }
}