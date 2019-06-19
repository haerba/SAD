package chat;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ChatServidor {

    private static final int portNumber = 5000;

    private int server_port;
    private Map<String, ThreadClient> clients; // or "protected static List<ClientThread> clients;"

    public static void main(String[] args){
        ChatServidor server = new ChatServidor(portNumber);
        server.startServer();
    }

    public ChatServidor(int portNumber){
        this.server_port = portNumber;
    }

    public Map<String, ThreadClient> getMap(){
        return this.clients;
    }
    
    public List<ThreadClient> getClients(){
    	return new ArrayList<ThreadClient>(this.clients.values());
    }

    private void startServer(){
        this.clients = new HashMap<String,ThreadClient>();
        MyServerSocket myserverSocket = null;
        try {
            myserverSocket = new MyServerSocket(this.server_port);
            this.acceptClients(myserverSocket);
        } catch (IOException e){
            System.err.println("Could not listen on port: "+this.server_port);
            System.exit(1);
        }
    }

    private void acceptClients(MyServerSocket serverSocket){

        System.out.println("server starts port = " + serverSocket.getLocalSocketAddress());
        while(true){
            MySocket mysocket = serverSocket.my_accept();
			Socket socket = mysocket.getSocket();
			System.out.println("accepts : " + socket.getRemoteSocketAddress());
			ThreadClient client = new ThreadClient(this, socket);
			Thread thread = new Thread(client);
			thread.start();
			
			synchronized (this.clients){
				String username = mysocket.setUsername();
				this.clients.put(username, client);
				System.out.print("Current users: "+ this.clients.keySet()+"\n");
	        }
			
        }
    }
}