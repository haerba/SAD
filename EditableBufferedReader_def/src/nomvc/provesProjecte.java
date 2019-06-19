package nomvc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class provesProjecte {

	public static void main(String[] args) throws IOException {
		EditableBufferedReader eb = new EditableBufferedReader(new InputStreamReader(System.in));
		String string_consulta = "QUÈ VOLS PROVAR?\n 1 - COMPTADOR COLUMNES\n 2 - PROVES CURSOR I INSERCIÓ DE LÍNIA\n "
				+ "3 - LECTURES DEL BUFFERED READER I ELS VALORS DE LES ANSI\n" + " 4 - PROVES RAW\n"
				+ " 5 - TEST READLINE\n" + " ? - EXIT\n";
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int a = 53;
		do{
			System.out.println(string_consulta);
			a = in.read();
			if(a == 10) {
				a = in.read();
			}
			System.out.println(a);
			if (a == 49) {
				System.out.println("\n\nEstas provant el COMPTADOR DE COLUMNES");
				Line line = new Line();
				String valor = line.count_cols();
				System.out.println("El teu terminal té " + valor + " columnes.\n");

			} else if (a == 50) {
				System.out.println("\n\nEstas provant PROVES AMB EL CURSOR I INSERCIÓ DE LÍNIA");
				eb.setRaw();
				Line line = new Line();
				line.clear();
				line.insert_letter('a');
				line.insert_letter('b');
				line.insert_letter('c');
				line.insert_letter('d');
				System.out.println(line.get_cursorpos());
				line.move_left();
				System.out.println(line.get_cursorpos());
				int a_2 = eb.read();
				System.out.print(a_2);
				line.insert_letter('z');
				line.insert_letter('y');
				line.move_right();
				line.insert_letter('a');
				eb.unsetRaw();

			} else if (a == 51) {
				System.out.println("\n\nEstas provant LECTURES DEL BUFFERED READER I ELS VALORS DE LES ANSI");
				eb.setRaw();
				int valor = 0;
				while (valor != 13) {
					valor = in.read();
					System.out.println(valor);
				}
				eb.unsetRaw();

			} else if (a == 52) {
				System.out.println("\n\nEstas provant SETRAW - Entraràs en mode RAW, per sortir-ne, tecleja < stty sane >");
				eb.setRaw();
			} else if (a == 53) {
				String str = eb.readLine();
				System.out.println("\nLine is: \n" + str);
			}
		}while(a < 54);
		System.out.print(ANSI.CLEAR);
	}

}
