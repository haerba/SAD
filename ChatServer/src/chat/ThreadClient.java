package chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ThreadClient implements Runnable {
    private Socket socket;
    private PrintWriter output;
    private ChatServidor server;

    public ThreadClient(ChatServidor server, Socket socket){
        this.server = server;
        this.socket = socket;
    }

    private PrintWriter getWriter(){
        return output;
    }

    @Override
    public void run() {
        try{
            // setup
            this.output = new PrintWriter(socket.getOutputStream(), false);
            Scanner in = new Scanner(socket.getInputStream());
            
            // start communicating
            while(!socket.isClosed()){
                if(in.hasNextLine()){
                    String input = in.nextLine();
                    for(ThreadClient thatClient : server.getClients()){
                    	if(thatClient != this){
                    		PrintWriter thatClientOut = thatClient.getWriter();
                            if(thatClientOut != null){
                                thatClientOut.write(input + "\r\n");
                                thatClientOut.flush();
                            }
                    	}
                        
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}