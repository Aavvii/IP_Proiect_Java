import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client
{
    public static void main(String[] args) throws IOException
    {
        try
        {
            Scanner scn = new Scanner(System.in);
            Socket s = new Socket("localhost", 6969);

            DataInputStream input = new DataInputStream(s.getInputStream());
            DataOutputStream output = new DataOutputStream(s.getOutputStream());

            while (true)
            {
                String toSend = scn.nextLine();
                output.writeUTF(toSend);

                // If client sends exit,close this connection  
                // and then break from the while loop 
                if(toSend.toUpperCase().equals("EXIT"))
                {
                    System.out.println("Closing this connection : " + s);
                    s.close();
                    System.out.println("Connection closed");
                    break;
                }

                // printing date or time as requested by client 
                String received = input.readUTF();
                System.out.println(received);
            }

            // closing resources 
            scn.close();
            input.close();
            output.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
} 