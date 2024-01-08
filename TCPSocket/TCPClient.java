import java.io.*;
import java.net.*;
import java.util.*;

class TCPClient {
    public static void main(String argv[]) throws Exception {
        String firstNumber, secondNumber;
        String result;
        Scanner inFromUser = null;
        Socket clientSocket = null;
        DataOutputStream outToServer = null;
        Scanner inFromServer = null;
        try {
            inFromUser = new Scanner(System.in);
            clientSocket = new Socket("localhost", 1667);
            outToServer = new DataOutputStream(clientSocket.getOutputStream());
            inFromServer = new Scanner(clientSocket.getInputStream());
            System.out.print(inFromServer.nextLine());
            firstNumber = inFromUser.nextLine();
            outToServer.writeBytes(firstNumber+"\n");
            System.out.print(inFromServer.nextLine());
            secondNumber = inFromUser.nextLine();
            outToServer.writeBytes(secondNumber+"\n");
            result = inFromServer.nextLine();
            System.out.println("The result is " + result);
        } catch (IOException e) {
            System.out.println("Error occurred: Closing the connection");
        } finally {
            try {
                if (inFromServer != null)
                    inFromServer.close();
                if (outToServer != null)
                    outToServer.close();
                if (clientSocket != null)
                    clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}