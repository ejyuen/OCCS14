package alpha;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	
	final String HOST; 
	final int PORT = 4444;
	Socket echoSocket = null;
	PrintWriter out = null;
	BufferedReader in = null;
	
	public Client(String ipIdentifier) {
		HOST = "192.168.0." + ipIdentifier;
		
		try {
			echoSocket = new Socket(HOST, PORT);
			out = new PrintWriter(echoSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: " + HOST);
			System.exit(0);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to: " + HOST);
			System.exit(0);
		}
	}
	
	public String getNextLine() {
		try {
			return in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "No input";
	}
	
	public void send(String s) {
		out.println(s);
	}
	
	public void close() {
		out.close();
		try {
			in.close();
			echoSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	public static void main(String[] args) throws IOException {

		Client client;
		
		if (args.length > 0) {
			client = new Client(args[0]);
		}
		else {
			client = new Client("144");
		}
		String input;

		while ((input = client.getNextLine()) != null) {
		}	
		
		System.out.println("Probably disconnected");
		
		System.exit(0);
		
	}
	*/
}
