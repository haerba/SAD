package mvc_pro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class MainGUI {

	// Nom que apareix a dalt de la finestra
	String windowName = "SAD CHAT";

	// Instància sobre la interfície gràfica
	MainGUI mainGUI;

	// És el contenidor que s'hi insereixen els diferents widgets un cop ja tenim
	// nom d'usuari
	JFrame mainFrame = new JFrame(this.windowName);

	// És el botó que cliquem per a enviar el text
	JButton button;

	// És l'àrea on inserim text per a enviar als diferents usuaris
	JTextField messageBox;

	// És el conjunt de textos, la conversació en general
	JTextPane chatBox;

	// El text pane si es vol afegir el text un per un, requereix d'un
	// StyledDocument
	StyledDocument document;

	// Text alineat dreta:
	SimpleAttributeSet right;

	// Text alineat esquerra:
	SimpleAttributeSet left;

	// És el area on s'insertarà el nom d'usuari
	JTextField username_field;

	// És el primer contenidor que apareix on hi inserim els widgets per a nou nom
	// d'usuari
	JFrame preFrame;

	// És el string que conté el nom d'usuari
	String username;

	// És el conjunt d'usuaris
	JTextArea usersBox;

	// És la instància necessària per a poder avisar-lo que el usuari ha sigut
	// introduït així
	// es crea el controlador del Client que necessita el nom d'usuari per a
	// funcionar
	ChatClient user;

	public MainGUI(ChatClient user) {
		this.preDisplay();
		this.user = user;
	}

	public void preDisplay() {

		// Amaguem la finestra general, es crea una instància de la nova Frame
		this.mainFrame.setVisible(false);
		this.preFrame = new JFrame(this.windowName);

		// Es crea un camp de text per a escriure el nom d'usuari
		this.username_field = new JTextField(15);

		// Es crea una Label per a demanar una instrucció
		JLabel insert_username = new JLabel("Insert a username:");

		// El botó per a iniciar sessió al servidor
		JButton enterServer = new JButton("Enter the Chat Server");

		// Afegim el Listener intern que tenim implementat i que gestionarà el event
		// d'insertar el username
		enterServer.addActionListener(new enterServerButtonListener());
		this.username_field.addActionListener(new enterServerButtonListener());

		// Es crea un panell per la preFrame on s'afegiràn les mesures de dreta i
		// esquerra
		JPanel prePanel = new JPanel(new GridBagLayout());

		// Aqui es crearàn les mesures de la dreta del panell
		GridBagConstraints preRight = new GridBagConstraints();
		preRight.insets = new Insets(0, 0, 0, 10);
		preRight.anchor = GridBagConstraints.EAST;

		// Aqui es crearàn les mesures de l'esquerra del panell
		GridBagConstraints preLeft = new GridBagConstraints();
		preLeft.anchor = GridBagConstraints.WEST;
		preLeft.insets = new Insets(0, 10, 0, 10);
		preRight.fill = GridBagConstraints.HORIZONTAL;
		preRight.gridwidth = GridBagConstraints.REMAINDER;

		// Afegim la label al panell, el costat esquerra
		prePanel.add(insert_username, preLeft);

		// Afegim el camp per insertar el username al costat dret
		prePanel.add(this.username_field, preRight);

		// Centrem el panell insertat el centre de la Frame
		this.preFrame.add(BorderLayout.CENTER, prePanel);

		// Afegim el botó per a iniciar sessió a sota
		this.preFrame.add(BorderLayout.SOUTH, enterServer);

		// Mesures de la frame en general
		this.preFrame.setSize(350, 350);
		this.preFrame.setVisible(true);

	}

	public void display() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		JPanel southPanel = new JPanel();
		southPanel.setBackground(Color.orange);
		southPanel.setLayout(new GridBagLayout());

		this.messageBox = new JTextField(30);
		this.messageBox.requestFocusInWindow();

		this.button = new JButton("Send Message");

		// Aquesta versió vol millorar l'experiencia d'usuari, així que la zona de
		// missatges és més
		// complexa, on hi afegim text a la dreta i la esquerra
		this.document = new DefaultStyledDocument();
		this.chatBox = new JTextPane(this.document);
		this.chatBox.setEditable(false);
		// this.chatBox.setContentType("text/html");
		this.chatBox.setFont(new Font("Courier New", Font.PLAIN, 15));

		this.left = new SimpleAttributeSet();
		StyleConstants.setAlignment(this.left, StyleConstants.ALIGN_LEFT);
		StyleConstants.setForeground(this.left, Color.RED);

		this.right = new SimpleAttributeSet();
		StyleConstants.setAlignment(this.right, StyleConstants.ALIGN_RIGHT);
		StyleConstants.setForeground(this.right, Color.BLUE);



		// this.chatBox.setLineWrap(true);

		this.usersBox = new JTextArea();
		this.usersBox.setEditable(false);
		this.usersBox.setFont(new Font("Courier New", Font.PLAIN, 15));
		this.usersBox.setLineWrap(true);

		mainPanel.add(new JScrollPane(this.chatBox), BorderLayout.CENTER);
		mainPanel.add(new JScrollPane(this.usersBox), BorderLayout.EAST);

		GridBagConstraints left = new GridBagConstraints();
		left.anchor = GridBagConstraints.LINE_START;
		left.fill = GridBagConstraints.HORIZONTAL;
		left.weightx = 512.0D;
		left.weighty = 1.0D;

		GridBagConstraints right = new GridBagConstraints();
		right.insets = new Insets(0, 10, 0, 0);
		right.anchor = GridBagConstraints.LINE_END;
		right.fill = GridBagConstraints.NONE;
		right.weightx = 1.0D;
		right.weighty = 1.0D;

		southPanel.add(this.messageBox, left);
		southPanel.add(this.button, right);

		mainPanel.add(BorderLayout.SOUTH, southPanel);

		this.mainFrame.add(mainPanel);
		// this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				user.leave();
				System.exit(0);
			}
		});
		this.mainFrame.setSize(470, 300);
		this.mainFrame.setVisible(true);
	}

	public void addActionListener(ActionListener action) {
		this.messageBox.addActionListener(action);
		this.button.addActionListener(action);

	}

	// S'afegeix el text que se li passa al textArea (rebut o escrit)
	public void addText(String text){
		 try {
			this.document.setParagraphAttributes(this.document.getLength(), 1, this.left, true);
			this.document.insertString(this.document.getLength(), text, this.left );
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        		
    }

	public void addMyText(String text) {
		try {
			this.document.setParagraphAttributes(this.document.getLength(), 1, this.right, true);
			this.document.insertString(this.document.getLength(), text, this.right);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Retorna el text que hi ha en el textField i es borra per a introduir-ne de
	// més
	public String getText() {
		String text = this.messageBox.getText();
		this.messageBox.setText("");
		return text;
	}

	public void addUser(String user) {
		this.usersBox.append(user);
		try {
			this.document.insertString(this.document.getLength(),
					user.substring(0, user.length() - 1) + " has joined the room.\n", left);
			this.document.setParagraphAttributes(this.document.getLength(), 1, left, false);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// PER A LA FINESTRA INICIAL PER A INICIALITZAR EL NOM D'USUARI
	class enterServerButtonListener implements ActionListener {
		// Aquest listener serà activat quan insertem text i s'apreti al botó o enter
		public void actionPerformed(ActionEvent event) {
			// AFEGIM EL NOU NOM D'USUARI
			username = username_field.getText();
			if (username.length() < 1) {
				System.out.println("No!");
			} else {
				// SI LES CONDICIONS ES DONEN, TREIEM LA FINESTRA DE DEMANDA DE NOM D'USUARI
				preFrame.setVisible(false);
				// S'ENGEGA EL MÉTODE QUE ENS MOSTRA LA FINESTRA EN GENERAL
				display();
				user.initialize(username);
			}
		}

	}

	public void removeUser(List<String> users) {
		this.usersBox.setText("");
		for (String user : users) {
			this.usersBox.append(user + "\n");
		}

	}

}
