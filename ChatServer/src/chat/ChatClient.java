package chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

    private static final String host = "localhost";
    private static final int portNumber = 5000;

    private String user_name;
    private String server_host;
    private int serverPort;
    // private Scanner scanner;

    public static void main(String[] args){
        String readName = null;
        Scanner scan = new Scanner(System.in);
        System.out.println("Please input username:");
        while(readName == null || readName.trim().equals("")){
            // null, empty, whitespace(s) not allowed.
            readName = scan.nextLine();
            if(readName.trim().equals("")){
                System.out.println("Invalid. Please enter again:");
            }
        }

        ChatClient client = new ChatClient(readName, host, portNumber);
        client.startClient(scan);

    }

    private ChatClient(String userName, String host, int portNumber){
        this.user_name = userName;
        this.server_host = host;
        this.serverPort = portNumber;
    }

    private void startClient(Scanner scan){
        try{
            Socket socket = new Socket(this.server_host, this.serverPort);
            Thread.sleep(1000); // waiting for network communicating.
            ThreadServidor serverThread = new ThreadServidor(socket, user_name);
            Thread serverAccessThread = new Thread(serverThread);
            serverAccessThread.start();
            while(serverAccessThread.isAlive()){
                if(scan.hasNextLine()){
                    serverThread.addNextMessage(scan.nextLine());
                }

            }
        }catch(Exception ex){
            ex.printStackTrace();;
        }
    }
}
