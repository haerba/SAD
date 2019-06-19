package chat_bubbles;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

// Classe que esta atenent el socket y les peticions d'usuari 
// El que arriba pel socket és mostrat en el text area de la nostra GUIl, i el que és
// escrit per l'usuari és enviat pel socket

public class ClientControler implements ActionListener, Runnable {

	// El nom a mostrar en el xat del usuari
	private String username;

	// Per a la lectura de dades del socket
	private DataInputStream dataInput;

	// Per a l'escriptura de dades del socket
	private DataOutputStream dataOutput;

	// GUI amb els controls de l'usuari
	private MainGUI_2 chat_gui;

	// Llista d'usuaris
	private List<String> users;

	// Es construeix una instància de la classe, llançant un fil per a atendre al
	// socket
	public ClientControler(String username, Socket socket, MainGUI_2 gui) {
		this.username = username;
		this.chat_gui = gui;
		this.users = new ArrayList<>();
		try {
			this.dataInput = new DataInputStream(socket.getInputStream());
			this.dataOutput = new DataOutputStream(socket.getOutputStream());
			Thread thread_controler = new Thread(this);
			this.chat_gui.addActionListener(this);
			thread_controler.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Obté el text de la gui i l'envia pel socket. La GUI cridarà aquest mètode
	// cada vegada que
	// s'apreti el botó enviar o es cliqui la tecla enter
	public void actionPerformed(ActionEvent event) {
		try {
			this.dataOutput.writeUTF(this.username + ": " + this.chat_gui.getText());
			System.out.println("Text enviat satisfactoriament per @" + this.username);

		} catch (Exception excepcion) {
	    	System.out.println("ERROR AL CONTROLER");
			excepcion.printStackTrace();
		}
	}
	
	public void leave() {
		try {
			this.dataOutput.writeUTF(".@"+this.username+" is leaving the room.");
			this.dataInput.close();
			this.dataOutput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Métode run que és està pel socket, tot el que li arriba pel socket, és escrit
	// a la GUI
	public void run() {
		try {
				// Enviem el nostre nom d'usuari a tothom
				this.dataOutput.writeUTF("@"+this.username);			
			while (true) {
				String text = dataInput.readUTF();
				if (text.startsWith("@")) {
					if (!this.users.contains(text)) {
						this.users.add(text);
						this.chat_gui.addUser(text+"\n");
						// Envio el meu username per a que ompli la llista el nou usuari
						this.dataOutput.writeUTF("@"+this.username);

					}
				} else if (text.startsWith(".@")) {
					this.chat_gui.addText(text.substring(1)+"\n");
					String[] words = text.split(" ");
					String user_leaving = words[0].substring(1);
					this.users.remove(user_leaving);
					this.chat_gui.removeUser(this.users);
				} else {
					String[] words = text.split(":");
					if(!words[0].equals(this.username)) {
						this.chat_gui.addText(text+"\n");
					}else {
						this.chat_gui.addMyText(text+"\n");
					}
				}

			}

		} catch (Exception e) {
	    	System.out.println("Tens el socket tancat, has tancat sessió.\n");
		}
	}
}
