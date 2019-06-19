package chat;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Scanner;

public class ThreadServidor implements Runnable {
    private String[] ANSI_USERS = new String[9];
	private Socket socket;
    private String user_name;
    private final LinkedList<String> messagesToSend;
    private boolean hasMessages = false;

    public ThreadServidor(Socket socket, String user_name){
        this.socket = socket;
        this.user_name = user_name;
        this.messagesToSend = new LinkedList<String>();
        this.charge_ansi();
    }

    public void addNextMessage(String message){
        synchronized (messagesToSend){
            this.hasMessages = true;
            this.messagesToSend.push(message);
        }
    }

    @Override
    public void run(){
        System.out.println("Benvingut : " + this.user_name);

        System.out.println("Local Port : " + socket.getLocalPort());
        System.out.println("Servidor = " + socket.getRemoteSocketAddress() + ":" + socket.getPort());

        try{
            PrintWriter serverOut = new PrintWriter(socket.getOutputStream(), false);
            InputStream serverInStream = socket.getInputStream();
            Scanner serverIn = new Scanner(serverInStream);
            
            serverOut.println(this.user_name);
            //System.out.println("Se hizo la magia");
            serverOut.flush();
            
            while(!socket.isClosed()){
                if(serverInStream.available() > 0){
                    if(serverIn.hasNextLine()){
                        System.out.println(serverIn.nextLine());
                    }
                }
                if(hasMessages){
                    String nextSend = "";
                    synchronized(messagesToSend){
                        nextSend = messagesToSend.pop();
                        hasMessages = !messagesToSend.isEmpty();
                    }
                    int i = (int) (Math.random() * 8 );
                    serverOut.print(this.ANSI_USERS[i]);
                    serverOut.println(this.user_name + " -> " + nextSend);
                    serverOut.print(this.ANSI_USERS[8]);
                    serverOut.flush();
                }
            }
        }
        catch(IOException ex){
            ex.printStackTrace();
        }

    }
    public void charge_ansi() {
    	this.ANSI_USERS[0] = "\u001B[0m";
    	this.ANSI_USERS[1] = "\u001B[30m";
    	this.ANSI_USERS[2] = "\u001B[31m";
    	this.ANSI_USERS[3] = "\u001B[32m";
    	this.ANSI_USERS[4] = "\u001B[33m";
    	this.ANSI_USERS[5] = "\u001B[34m";
    	this.ANSI_USERS[6] = "\u001B[35m";
    	this.ANSI_USERS[7] = "\u001B[36m";
    	this.ANSI_USERS[8] = "\u001B[37m";



    	
    }
}