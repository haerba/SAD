package mvc_pro;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.DefaultListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

public class Worker implements Runnable, ListDataListener {
	// Llista en la qual es guarda tota la conversació
	private DefaultListModel conversation;

	// Socket al qual està conectat el client
	private Socket socket;

	// Per a la lectura de dades en el socket
	private DataInputStream dataInput;

	// Per a l'escriptura de dades en el socket
	private DataOutputStream dataOutput;


	// Es crea una instància d'aquesta conversació i un listener pels canvis
	// d'aquesta.
	public Worker(DefaultListModel conversation, Socket socket) {
		this.conversation = conversation;
		this.socket = socket;
		try {
			dataInput = new DataInputStream(socket.getInputStream());
			dataOutput = new DataOutputStream(socket.getOutputStream());
			this.conversation.addListDataListener(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Fil encarregat d'atendre el socket. Tot el que li arribi, serà afegit a la
	// conversació
	public void run() {
		try {
			while (true) {
				String text = dataInput.readUTF();
				synchronized (this.conversation) {
					this.conversation.addElement(text);
				}

			}
		} catch (Exception e) {
	    	System.out.println("Connection closed");
	    	try {
	    		this.conversation.removeListDataListener(this);
	    		this.socket.close();
				this.dataInput.close();
				this.dataOutput.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	    	

		}
	}

	// Envia el ultim text de la conversació pel socket, aquest métode és avisat
	// cada vegada que
	// s'afegeix alguna cosa a la conversació, d'aquesta manera el que escrigui un
	// client, serà reenviat
	// per a mostrar-ho en el textArea
	public void intervalAdded(ListDataEvent e) {
		// Cap usuari ha sigut afegit, algú ha enviat un missatge
		String text = (String) this.conversation.getElementAt(e.getIndex0());
		try {
			dataOutput.writeUTF(text);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		}
	}

	public void intervalRemoved(ListDataEvent e) {
	}

	public void contentsChanged(ListDataEvent e) {
	}
}
