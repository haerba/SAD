package mvc_mouse;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class Console  implements PropertyChangeListener{
	// THIS IS THE VIEW OF THE MVC PATTERN

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		switch (evt.getPropertyName()) {
		case ("mode"):
			if((boolean) evt.getNewValue()) {
				System.out.print(ANSI.INSERT);
			}
			System.out.print(ANSI.OVERWRITE);
			break;
		case ("home"):
			System.out.print(ANSI.HOME);
			break;
		case ("end"):
			System.out.print(ANSI.END);
			break;
		case ("insert_letter"):
			System.out.print((char)evt.getNewValue());
			break;
		case ("delete_letter"):
			System.out.print(ANSI.LEFT+ANSI.DELETE);
			break;
		case ("supr_letter"):
			System.out.print(ANSI.DELETE);
			break;
		case ("right"):
			System.out.print(ANSI.RIGHT);
			break;
		case ("left"):
			System.out.print(ANSI.LEFT);
			break;
		case ("prepare"):
			System.out.print(ANSI.CLEAR);
			break;
		default:
			System.out.print(ANSI.CLEAR);
			System.out.print("You are having an error my friend.");
		}
	
	}

	
	
}
