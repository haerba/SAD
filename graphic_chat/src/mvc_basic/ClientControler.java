package mvc_basic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

// Classe que esta atenent el socket y les peticions d'usuari 
// El que arriba pel socket és mostrat en el text area de la nostra GUIl, i el que és
// escrit per l'usuari és enviat pel socket

public class ClientControler implements ActionListener, Runnable{
	
	// El nom a mostrar en el xat del usuari
	private String username;
	
    // Per a la lectura de dades del socket 
    private DataInputStream dataInput;

    // Per a l'escriptura de dades del socket
    private DataOutputStream dataOutput;

    // GUI amb els controls de l'usuari
    private ChatGUI chat_gui;

    // Es construeix una instància de la classe, llançant un fil per a atendre al socket
    public ClientControler(String username, Socket socket, ChatGUI gui){
    	this.username = username;
        this.chat_gui = gui;
        try
        {
            this.dataInput = new DataInputStream(socket.getInputStream());
            this.dataOutput = new DataOutputStream(socket.getOutputStream());
            this.chat_gui.addActionListener(this);
            Thread thread_controler = new Thread(this);
            thread_controler.start();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // Obté el text de la gui i l'envia pel socket. La GUI cridarà aquest mètode cada vegada que 
    // s'apreti el botó enviar o es cliqui la tecla enter
    public void actionPerformed(ActionEvent event){
        try
        {
            this.dataOutput.writeUTF(this.username+": "+this.chat_gui.getText());
        } catch (Exception excepcion){
            excepcion.printStackTrace();
        }
    }

    // Métode run que és està pel socket, tot el que li arriba pel socket, és escrit a la GUI
    public void run(){
        try
        {
            while (true)
            {
                String text = dataInput.readUTF();
                this.chat_gui.addText(text);
                this.chat_gui.addText("\n");
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
