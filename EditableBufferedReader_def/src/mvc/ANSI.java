package mvc;

public class ANSI {
	
	final static String ESC = "\033[";
	final static String CLEAR = "\033c";
	final static String CLEAR_2 = "\033[H\033[2J";
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
	final static String RESTORE = "\033[1E";

	
}
