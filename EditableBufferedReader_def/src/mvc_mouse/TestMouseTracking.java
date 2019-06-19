package mvc_mouse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestMouseTracking {

	public static void main(String[] args) {
		EditableBufferedReader eb = new EditableBufferedReader(new InputStreamReader(System.in));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("\n\nEstas provant LECTURES DEL BUFFERED READER I ELS VALORS DE LES ANSI");
		eb.setMouseTracking();
		eb.setRaw();
		int valor = 0;
		while (valor != 13) {
			try {
				valor = in.read();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(valor);
		}
		eb.unsetMouseTracking();
		eb.unsetRaw();

	}

}
