package mvc_basic;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

// Classe amb el main d'un client del xat. Estableix la connexió i crea una finestra, amb la classe
// de control


public class ChatClient{
    // Socket amb el servidor del xat 
    private Socket socket;

    // Gui amb la finestra del client
    private ChatGUI chat_gui;

    // Arrenca el client d'un xat
    public static void main(String[] args){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    	System.out.println("What's your name?");
        String username = null;
		try {
			username = reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        new ChatClient(username);
    }

    // Es crea la finestra, establim una connexií i isntanciem el controlador
    public ChatClient(String username){
        try
        {
            this.newWindow();
            this.socket = new Socket("localhost", 2500);
            ClientControler control = new ClientControler(username, this.socket, this.chat_gui);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // Es crea la finestra, i se li afegeix dins la gui pel client, per acabar visualitzant-la
    private void newWindow(){
        JFrame v = new JFrame();
        this.chat_gui = new ChatGUI(v.getContentPane());
        v.pack();
        v.setSize(500, 300);
        v.setVisible(true);
        v.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
