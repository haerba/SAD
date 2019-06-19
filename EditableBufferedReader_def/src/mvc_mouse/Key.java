package mvc_mouse;

public class Key {

	/*These are the codifications that Buffered Reader returns for the ANSI escape sequences for an iOS 
	
	*/
	
	// Return KEY:
	public final static int RETURN = 13;
	// Control + C:
	public final static int END_EXEC = 3;
	// Delete:
	public final static int DELETE = 8;
	// Delete Option 2
	public final static int DELETE_2 = 127;
	//Home is Control + A:
	public final static int HOME = 1;
	//End is Control + D:
	public final static int END = 6;
	//Delete forward (Suprimir) is Control + D or the ANSII sequence [^51 126
	public final static int SUPRIMIR = 4;
	//Insert key is Control + I:
	public final static int INSERT = 9;

	//ANSII ESCAPE SEQUENCE [^
	public final static int ESC_1 = 27;
	public final static int ESC_2 = 91;
	
	//MOUSE TRACKING KEYS
	public final static int MOUSE = 77;
	public final static int PRESS = 32;
	public final static int RELEASE = 35;

	
	// COMBINATIONS
	public final static int DRETA = 67;
	public final static int ESQUERRA = 68;
	public final static int UP = 65;
	public final static int DOWN = 66;
	public final static int SUPRIMIR_2 = 51;
	public final static int SUPRIMIR_3 = 126;

	
	// Codifications
	public final static int DELETE_KEY = 1500;
	public final static int INSERT_KEY = 1501;
	public final static int RIGHT_KEY = 1502;
	public final static int LEFT_KEY = 1503;
	public final static int HOME_KEY = 1504;
	public final static int END_KEY = 1505;
	public final static int SUPR_KEY = 1506;
	public final static int FINAL_KEY = 1507;
	public final static int UP_KEY = 1508;
	public final static int DOWN_KEY = 1509;
	public final static int MOUSE_KEY = 1510;


}
