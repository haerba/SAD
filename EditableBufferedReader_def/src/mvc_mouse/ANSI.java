package mvc_mouse;

public class ANSI {
	
	final static String ESC = "\033[";
	final static String CLEAR = "\033c";
    final static String HOME= "\033[%d;0f";
    final static String END = "\033[%d;%df";
	final static String RIGHT = "\033[C";
	final static String LEFT= "\033[D";
	final static String UP = "\033[2F";
	final static String DOWN = "\033[2E";
	final static String DELETE = "\033[P";
	final static String INSERT = "\033[4l";
	final static String OVERWRITE = "\033[4h";
	final static String SUPRIMIR = "\033[X";
	
	//CSI Ps ; Pu ' z
    // Enable Locator Reporting (DECELR).
	final static String ACTIVATE_MOUSE = "\033[1;'z";
	
	// CSI ? Pm h
	// DEC Private Mode Set (DECSET).
	// Ps = 1000  -> Send Mouse X & Y on button press.  
	final static String ACTIVATE_MOUSE_2 = "\033[?1000h";
	
	
	// CSI ? Pm 1
	// DEC Private Mode Set (DECSET).
	// Ps = 1000  -> Don't send Mouse X & Y on button press.  
	final static String DEACTIVATE_MOUSE_2 = "\033[?1000l";
	


	
}
