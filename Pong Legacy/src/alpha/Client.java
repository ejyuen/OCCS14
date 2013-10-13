package alpha;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	
	final String HOST; 
	final int PORT = 4444;
	Socket echoSocket = null;
	PrintWriter stringOutput = null;
	BufferedReader stringInput = null;
	ObjectOutputStream objOutput = null;
	ObjectInputStream objInput = null;
	
	public Client(String ipIdentifier) {
		HOST = ipIdentifier;
		
		try {
			echoSocket = new Socket(HOST, PORT);
			OutputStream os = echoSocket.getOutputStream();
			stringOutput = new PrintWriter(os, true);
			objOutput = new ObjectOutputStream(os);
			
			InputStream is = echoSocket.getInputStream();
			stringInput = new BufferedReader(new InputStreamReader(is));
			objInput = new ObjectInputStream(is);
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: " + HOST);
			System.exit(0);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to: " + HOST);
			System.exit(0);
		}
	}
	
	public void sendString(String s) {
		stringOutput.println(s);
	}
	
	public void sendObject(Object o){
		try {
			objOutput.writeObject(o);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getNextLine() {
		try {
			return stringInput.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public Object getNextObject(){
		Object ret = null;
		try {
			ret = objInput.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return ret;
	}
	
	public void close() {
		stringOutput.close();
		try {
			stringInput.close();
			echoSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
