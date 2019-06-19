package mvc_pro;

import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.DefaultListModel;

// Servidor del chat. Accepta connexions de clients, crea un fil per a atendre'ls, i espera la
// següent conexió.

public class ChatServer{
    // Llista en la qual es guarda tota la conversació
    private DefaultListModel conversation = new DefaultListModel();

    public static void main(String[] args){
        new ChatServer();
    }

    // Crearem un bucle per a acceptar clients, on aquest llançarà un fil per a cadascun d'ells
    public ChatServer(){
        try
        {
            ServerSocket serverSocket = new ServerSocket(2500);
            while (true)
            {
                Socket client = serverSocket.accept();
                Runnable newWorker = new Worker(this.conversation, client);
                Thread thread_worker = new Thread(newWorker);
                thread_worker.start();
            }
        } catch (Exception e)
        {
	    	System.out.println("ERROR AL CHAT SERVER");
            e.printStackTrace();
        }
    }
}
