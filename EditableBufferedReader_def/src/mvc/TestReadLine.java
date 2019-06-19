package mvc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestReadLine {
	
	public static void main(String[] args) {
	    BufferedReader in = new EditableBufferedReader(
	      new InputStreamReader(System.in));
	    String str = null;
	    try {
	      str = in.readLine();
	    } catch (IOException e) { e.printStackTrace(); }
	    System.out.println("\nline is clearly and as you typed: \n" + str);
	    System.out.print("\r");
	    

	  }
}
