package alpha.communicator;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import alpha.serializable.Ball;

public class Server implements Communicator{
	final int PORT = 4444;
	ServerSocket serverSocket = null;
	ArrayList<Socket> clientSockets = null;
	ArrayList<ObjectOutputStream> objOutputs = null;
	ArrayList<ObjectInputStream> objInputs = null;
	
	public Server(){
		try {
			serverSocket = new ServerSocket(PORT);
			clientSockets = new ArrayList<Socket>();
			objOutputs = new ArrayList<ObjectOutputStream>();
			objInputs = new ArrayList<ObjectInputStream>();
			accepting();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getNumClients(){
		return clientSockets.size();
	}
	
	
	public void accepting(){
		new Thread(new Accepter()).start();
	}
	
	public void sendObject(Object o){
		for(int i = 0; i<objOutputs.size(); i++){
			sendObject(o, i);
		}
	}
	
	public void sendObject(Object o, int client){
		try {
			objOutputs.get(client).writeObject(o);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Object getNextObject(int client){
		Object ret = null;
		try {
			ret = objInputs.get(client).readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public void close() {
		try {
			for(int i = 0; i<clientSockets.size(); i++){
				objOutputs.get(i).close();
				clientSockets.get(i).close();
			}
			serverSocket.close();
		} catch (IOException e) {
			System.out.println("Sockets failed to close.");
		}
	}
	
	class Accepter implements Runnable{
		public void run() {
			int i = 0;
			while(true){
				try {
					Socket client = serverSocket.accept();
					ObjectInputStream in = new ObjectInputStream(client.getInputStream());
					ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
					clientSockets.add(client);
					objInputs.add(in);
					objOutputs.add(out);
					sendObject(new Integer((i+1)*2), i);
					i++;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
}
