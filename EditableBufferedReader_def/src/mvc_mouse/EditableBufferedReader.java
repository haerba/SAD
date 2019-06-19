package mvc_mouse;

import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.Reader;

public class EditableBufferedReader extends BufferedReader {
	// THIS IS THE CONTROLLER OF THE MVC PATTERN

	public EditableBufferedReader(Reader in) {
		super(in);
		this.cb = 0;
		this.cx = 0;
		this.cy = 0;
	}

	// As far as how our terminal works, when we click, we send the info and then
	// with the
	// release we send it again. 33 is pos 0
	int cb;
	int cx;
	int cy;

	public void setRaw() {
		String[] command = { "/bin/sh", "-c", "stty raw </dev/tty" };
		Process pr;
		try {
			pr = Runtime.getRuntime().exec(command);

			if (pr.waitFor() == 1) {
				pr.destroy();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setMouseTracking() {
		System.out.print(ANSI.ACTIVATE_MOUSE);
		System.out.print(ANSI.ACTIVATE_MOUSE_2);

	}

	public void unsetMouseTracking() {
		System.out.print(ANSI.DEACTIVATE_MOUSE_2);

	}

	public void unsetRaw() {
		String[] command = { "/bin/sh", "-c", "stty echo cooked </dev/tty" };

		try {
			Process pr = Runtime.getRuntime().exec(command);
			if (pr.waitFor() == 1) {
				pr.destroy();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int read() {
		try {
			int valor = super.read();
			// Because we use a iOS keyboard, the Control + keys are under 13 and the ANSI
			// sequences start
			// with Key.ESC_1
			if (valor != Key.ESC_1 && valor != Key.DELETE && valor >= 13 && valor != Key.DELETE_2) {
				// It's a normal character
				return valor;
			} else if (valor != Key.ESC_1) {
				switch (valor) {
				case (Key.INSERT):
					return Key.INSERT_KEY;
				case (Key.END_EXEC):
					return Key.FINAL_KEY;
				case (Key.DELETE):
					return Key.DELETE_KEY;
				case (Key.DELETE_2):
					return Key.DELETE_KEY;
				case (Key.HOME):
					return Key.HOME_KEY;
				case (Key.END):
					return Key.END_KEY;
				case (Key.SUPRIMIR):
					return Key.SUPR_KEY;
				default:
					return -2;
				}
			} else if (valor == Key.ESC_1) {
				valor = super.read();
				if (valor == Key.ESC_2) {
					valor = super.read();
					switch (valor) {
					case (Key.DRETA):
						return Key.RIGHT_KEY;
					case (Key.ESQUERRA):
						return Key.LEFT_KEY;
					case (Key.UP):
						return Key.UP_KEY;
					case (Key.DOWN):
						return Key.DOWN_KEY;
					case (Key.MOUSE):
						// xterm sends CSI M CbCxCy
						cb = super.read();
						cx = super.read();
						cy = super.read();
						// Now we have to check if the release point of the mouse is the same as the
						// click
						int aux_1 = super.read();
						if (aux_1 == Key.ESC_1) {
							int aux_2 = super.read();
							if (aux_2 == Key.ESC_2) {
								int aux_3 = super.read();
								if (aux_3 == Key.MOUSE) {
									int cb_aux = super.read();
									int cx_aux = super.read();
									int cy_aux = super.read();
									if (cx != cx_aux || cy != cy_aux) {
										cx = cx_aux;
										cy = cy_aux;
									}
								}
							}
						}
						return Key.MOUSE_KEY;
					case (Key.SUPRIMIR_2):
						valor = super.read();
						if (valor == Key.SUPRIMIR_3) {
							return Key.SUPR_KEY;
						}
						return -2;
					default:
						return -2;
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public String readLine() {
		Line line = new Line();
		Console console = new Console();
		line.addPropertyChangeListener(console);
		line.prepare_console();
		// Caràcter llegit pel controlador
		int introd_l = 0;
		this.setMouseTracking();
		this.setRaw();
		while (introd_l != Key.RETURN && introd_l != Key.FINAL_KEY && introd_l != -2) {
			introd_l = this.read();
			switch (introd_l) {
			case (Key.LEFT_KEY):
				line.move_left();
				break;
			case (Key.RIGHT_KEY):
				line.move_right();
				break;
			case (Key.INSERT_KEY):
				line.insertMode();
				break;
			case (Key.HOME_KEY):
				line.home_key();
				break;
			case (Key.END_KEY):
				line.end_key();
				break;
			case (Key.DELETE_KEY):
				line.delete_letter();
				break;
			case (Key.SUPR_KEY):
				line.delete_supr();
				break;
			case (Key.MOUSE_KEY):
				line.mouse_click(cx - 33, cy - 33);
				break;
			default:
				line.insert_letter((char) introd_l);
				break;

			}
		}
		this.unsetMouseTracking();
		this.unsetRaw();
		return line.get_line();
	}
}
