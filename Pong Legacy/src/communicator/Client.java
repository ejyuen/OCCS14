package communicator;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
			objOutput = new ObjectOutputStream(echoSocket.getOutputStream());
			objInput = new ObjectInputStream(echoSocket.getInputStream());
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
			objOutput.reset();
			objOutput.writeUnshared(o);
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
			sendObject("Reset Please");
			System.out.println("trying to reset");
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
