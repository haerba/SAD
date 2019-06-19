package mvc;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Line {
	
	// THIS IS THE MODEL OF THE MVC PATTERN
	
	private int cursorpos;
	private boolean insert_mode;
	private List<Character> line;
	private PropertyChangeSupport support;
	
	
	public Line() {
		this.cursorpos = 0;
		this.insert_mode = false;
		this.line = new ArrayList<Character>();
		this.support = new PropertyChangeSupport(this);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
        this.support.addPropertyChangeListener(pcl);
    }
 
    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        this.support.removePropertyChangeListener(pcl);
    }
    
	public List<Character> getlist_line() {
		return this.line;
	}
	
	public void prepare_console() {
		this.support.firePropertyChange("prepare", null, null);
	}
	
	public void insertMode() {
		this.insert_mode = !this.insert_mode;
		this.support.firePropertyChange("mode", !this.insert_mode, this.insert_mode);
	}

	public void home_key() {
		int last_pos = this.cursorpos;
		this.cursorpos = 0;
		this.support.firePropertyChange("home", last_pos, this.cursorpos );
	}
	
	public void end_key()  {
		int last_pos = this.cursorpos;
		this.cursorpos = this.line.size()-1;
		this.support.firePropertyChange("end", last_pos, this.cursorpos );	
		
	}
	
	
	public void insert_letter(char c) {
		if(this.cursorpos == this.line.size()) {
			this.line.add(c);
			this.cursorpos++;
		}
		else if(this.insert_mode) {
				this.line.add(this.cursorpos, c);
				this.cursorpos++;
		}else {
			this.line.remove(this.cursorpos);
			this.line.add(this.cursorpos, c);
			this.cursorpos++;
		}
		
		this.support.firePropertyChange("insert_letter", null, c);
	}
	
	
	public void delete_letter() {
		if(this.cursorpos == 0) {
			return;
		}
		this.line.remove(this.cursorpos-1);	
		this.cursorpos--;
		this.support.firePropertyChange("delete_letter", null, null);
	}
	
		
	public void delete_supr() {
		if(this.cursorpos == this.line.size()) {
			return;
		}
		this.line.remove(this.cursorpos-1);	
		this.support.firePropertyChange("supr_letter", null, null);
	}
	
	public void move_right(){
		// changing cursor position of the input
		if(this.cursorpos < this.line.size()) {
			this.cursorpos += 1;
		}
		this.support.firePropertyChange("right", null, null);
	}
	
	public void move_left(){
		// changing cursor position of the input
		if(this.cursorpos > 0) {
			this.cursorpos -= 1;
		}
		this.support.firePropertyChange("left", null, null);
	}
	
	public void restore_cursor() {
		this.support.firePropertyChange("restore", null, null);
	}
	
	public void end_of_line() {
		int a = 0;
		while(a<this.line.size()) {
			this.support.firePropertyChange("right", null, null);
			a++;
		}

	}
	public int get_cursorpos() {
		return this.cursorpos;
	}
	
	public String count_cols() {
		String cols = null;
		String[] command = { "/bin/sh", "-c", "tput cols 2> /dev/tty" };
		try {
			ProcessBuilder pr = new ProcessBuilder(command);
			Process p = pr.start();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			cols = reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cols;
	}
	
	public String get_line() {
		System.out.println("\n"+this.line.size());
		StringBuilder builder = new StringBuilder(this.line.size());
	    for(Character ch: this.line)
	    {
	        builder.append(ch);
	    }
	    return builder.toString();
		
	}
}
