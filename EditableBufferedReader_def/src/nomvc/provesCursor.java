package nomvc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class provesCursor {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader( new InputStreamReader(System.in));
		EditableBufferedReader eb = new EditableBufferedReader(new InputStreamReader(System.in));
		int valor = 0;
		
		eb.setRaw();
		System.out.println("\033c"+"\r");
		System.out.print("abcdefghijk");
		System.out.print(ANSI.LEFT+"\033[P");
		System.out.print(ANSI.LEFT);
		System.out.print(ANSI.LEFT);
		System.out.print(ANSI.LEFT);
		// ESTAS A LA H
		System.out.print("\033[P");
		System.out.print("\n");


	
		
		eb.unsetRaw();
		
		/*
		String str = eb.readLine();
		System.out.println("\n"+ str);
		*/
	}

}
