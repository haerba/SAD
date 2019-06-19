package mvc_basic;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//Interfícia gràfica que mostra tota la conversació i demana text a l'usuari per a ser enviat
public class ChatGUI{
    // Scroll
    private JScrollPane scroll;

    // Àrea de text on es mostrarà la conversació
    private JTextArea textArea;

    // Àrea de text on es demana text al usuari
    private JTextField textField;

    // Botó per a enviar text
    private JButton button;

    // Aqui es crea la GUI amb els seus components
    public ChatGUI(Container container){
        container.setLayout(new BorderLayout());
        this.textArea = new JTextArea();
        this.scroll = new JScrollPane(textArea);
        this.scroll.setSize(300, 200);

        JPanel panel = new JPanel(new FlowLayout());
        this.textField = new JTextField(30);
        this.button = new JButton("Enviar");
        panel.add(textField);
        panel.add(button);
        panel.setSize(350, 10);
        container.add(scroll, BorderLayout.CENTER);
        container.add(panel, BorderLayout.SOUTH);
    }
    // Afegim el listener que se li passa la accio d'apretar la tecla enter o al apretar el botó

    public void addActionListener(ActionListener action){
        this.textField.addActionListener(action);
        this.button.addActionListener(action);

    }

    // S'afegeix el text que se li passa al textArea (rebut o escrit)
    public void addText(String text){
        textArea.append(text);
    }

    // Retorna el text que hi ha en el textField i es borra per a introduir-ne de més
    public String getText()
    {
        String text = textField.getText();
        textField.setText("");
        return text;
    }
}
