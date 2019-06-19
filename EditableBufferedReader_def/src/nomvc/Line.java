package nomvc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Line {
	private int cursorpos;
	private boolean insert_mode;
	private String line;
	
	
	
	public Line() {
		this.cursorpos = 0;
		this.insert_mode = false;
		this.line = "";
	}
	
	public String getLine() {
		int a = this.cursorpos;
		while(a <= this.line.length()) {
			System.out.print(ANSI.RIGHT);
			a++;
		}
		return this.line;
	}
	
	public void clear(){
		System.out.print(ANSI.CLEAR);
	}
	
	
	public void insertMode() {
		this.insert_mode = !this.insert_mode;
		if(this.insert_mode){
			System.out.print(ANSI.INSERT);
		}else {
			System.out.print(ANSI.OVERWRITE);
		}
	}

	
	public void home_key()  {
		System.out.print(ANSI.HOME);
	}
	
	public void end_key()  {
		this.cursorpos = this.line.length() -1;
		System.out.print(ANSI.END);
	}
	
	public void insert_letter(char c) {
		if(this.line == "") {
			this.line += c;
			this.cursorpos++;
		}
		else if(this.insert_mode) {
			if(this.cursorpos < this.line.length()) {
				this.line = this.line.substring(0, this.cursorpos) + c + this.line.substring(this.cursorpos);
				this.cursorpos++;
				
			}else {
				this.line += c;
				this.cursorpos++;
			}
		}else {
			if(this.cursorpos < this.line.length()) {
				this.line = this.line.substring(0, this.cursorpos) + c + this.line.substring(this.cursorpos+1);
				this.cursorpos++;
			}else {
				this.line += c;
				this.cursorpos++;
			}
		}
		System.out.print(c);
	}
	
	
	public void delete_letter() throws IOException {
		if(this.cursorpos == this.line.length()) {
			this.line = this.line.substring(0, this.cursorpos-1);
			this.cursorpos -= 1;
			System.out.print(ANSI.LEFT+ANSI.DELETE);
		
		}else if(this.line.length() > 0 && this.cursorpos < this.line.length()) {
			this.line = this.line.substring(0, this.cursorpos-1) + this.line.substring(this.cursorpos);
			this.cursorpos -= 1;
			System.out.print(ANSI.LEFT+ANSI.DELETE);
		}
		
	}
	
		
	public void delete_supr() throws IOException {
		if(this.line.length() > 0) {
			this.line = this.line.substring(0, this.cursorpos+1) + this.line.substring(this.cursorpos+2, this.line.length());
			this.cursorpos -= 1;
		}
		System.out.print(ANSI.SUPRIMIR);
	}
	public void move_right() throws IOException {
		// changing cursor position of the input
		if(this.cursorpos < this.line.length()) {
			this.cursorpos += 1;
			System.out.print(ANSI.RIGHT);
		}
		
	}
	
	public void move_left() throws IOException {
		// changing cursor position of the input
		if(this.cursorpos > 0) {
			this.cursorpos -= 1;
			System.out.print(ANSI.LEFT);
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
}
