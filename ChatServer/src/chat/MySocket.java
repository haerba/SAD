package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MySocket {

	
	Socket mysocket;
	String username;
	BufferedReader input;
	PrintWriter output;
	
	
	public MySocket(String host, int port) {
		try {
	           this.mysocket = new Socket(host, port);
	           this.input = new BufferedReader(new InputStreamReader(this.mysocket.getInputStream()));
	           this.output = new PrintWriter(this.mysocket.getOutputStream());
	    }
	    catch (IOException e) {
	        System.out.println(e);
	    }
		
	}
	
	public MySocket(String username, String host, int port) {
		try {
			   this.username = username;
	           this.mysocket = new Socket(host, port);
	           this.input = new BufferedReader(new InputStreamReader(this.mysocket.getInputStream()));
	           this.output = new PrintWriter(this.mysocket.getOutputStream());
	    }
	    catch (IOException e) {
	        System.out.println(e);
	    }
		
	}
	
	public MySocket(Socket s) {
		try {
	           this.mysocket = s;
	           this.input = new BufferedReader(new InputStreamReader(this.mysocket.getInputStream()));
	           this.output = new PrintWriter(this.mysocket.getOutputStream());
	    }
	    catch (IOException e) {
	        System.out.println(e);
	    }
		
	}
	
	public void close() {
		 try {
	           this.output.close();
	           this.input.close();
	           this.mysocket.close();
	    } 
	    catch (IOException e) {
	       System.out.println(e);
	    }

	}
	
	public String readLine() {
		String s = null;
		try {
			s = this.input.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
	
	
	public void writeLine(String s) {
		this.output.write(s);
	}
	
	public Socket getSocket() {
		return this.mysocket;
	}
	
	public void sendUsername() {
		//this.writeLine(this.username);
		this.output.println(this.username);
		System.out.println("USERNAME SENT: "+this.username);
	}
	
	public String receiveUsername() {
		return this.readLine();
	}
	public String setUsername() {
		this.username = this.readLine();
		return this.username;
	}
}

