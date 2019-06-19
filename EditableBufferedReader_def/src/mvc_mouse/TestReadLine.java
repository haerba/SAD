package mvc_mouse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestReadLine {
	
	public static void main(String[] args) {
	    EditableBufferedReader in = new EditableBufferedReader(
	      new InputStreamReader(System.in));
	    String str = null;
	    try {
	      str = in.readLine();
	    } catch (Exception e) { e.printStackTrace(); }
	    System.out.println("\nline is: " + str);
	  }
}
