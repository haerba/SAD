package chat_bubbles;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

// Classe amb el main d'un client del xat. Estableix la connexió i crea una finestra, amb la classe
// de control


public class ChatClient{
    // Socket amb el servidor del xat 
    private Socket socket;

    // Gui amb la finestra del client
    private MainGUI_2 chat_gui;

    // Controlador
    private ClientControler control;
    
    // Arrenca el client d'un xat
    public static void main(String[] args){
        new ChatClient();
    }

    // Es crea la finestra, establim una connexií i isntanciem el controlador
    public ChatClient(){
        try
        {
        	
        	this.chat_gui = new MainGUI_2(this);
            
            
        } catch (Exception e)
        {
        	System.out.println("ERROR AL CHAT CLIENT");
            e.printStackTrace();
        }
    }
    
    public void initialize(String username) {
    	try {
			this.socket = new Socket("localhost", 2500);
	    	this.control = new ClientControler(username, this.socket, this.chat_gui);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    public void leave() {
    	this.control.leave();
    	
    }


}
