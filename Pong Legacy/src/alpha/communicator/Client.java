package alpha.communicator;

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

public class Client implements Communicator{
	
	final String HOST; 
	final int PORT = 4444;
	Socket echoSocket = null;
	ObjectOutputStream objOutput = null;
	ObjectInputStream objInput = null;
	
	public Client(String ipIdentifier) {
		HOST = ipIdentifier;
		
		try {
			echoSocket = new Socket(HOST, PORT);
			OutputStream os = echoSocket.getOutputStream();
			objOutput = new ObjectOutputStream(os);
			
			InputStream is = echoSocket.getInputStream();
			objInput = new ObjectInputStream(is);
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: " + HOST);
			System.exit(0);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to: " + HOST);
			System.exit(0);
		}
	}
	
	public void sendObject(Object o){
		try {
			objOutput.writeObject(o);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		try {
			objOutput.close();
			objInput.close();
			echoSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
