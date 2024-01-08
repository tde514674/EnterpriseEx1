
//EchoThread.java
import java.io.*;
import java.net.*;
import java.util.*;

public class EchoThread extends Thread {
    private Socket connectionSocket;

    public EchoThread(Socket connectionSocket) {
        this.connectionSocket = connectionSocket;
    }

    public void run() {
        Scanner inFromClient = null;
        DataOutputStream outToClient = null;
        try {
            inFromClient = new Scanner(connectionSocket.getInputStream());
            outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            outToClient.writeBytes("enter number 1 (to end just press enter): "+"\n");
            String firstNumber = inFromClient.nextLine();
            if(firstNumber.isBlank()){
                inFromClient.close();
                outToClient.close();
                connectionSocket.close();
            }
            outToClient.writeBytes("enter number 2 (to end just press enter): "+"\n");
            String secondNumber = inFromClient.nextLine();
            if(secondNumber.isBlank()){
                inFromClient.close();
                outToClient.close();
                connectionSocket.close();
            }
            String result = String.valueOf(Integer.valueOf(firstNumber)+Integer.valueOf(secondNumber));
            outToClient.writeBytes(result);

        } catch (IOException e) {
            System.err.println("Closing Socket connection");
        } finally {
            try {
                if (inFromClient != null)
                    inFromClient.close();
                if (outToClient != null)
                    outToClient.close();
                if (connectionSocket != null)
                    connectionSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
