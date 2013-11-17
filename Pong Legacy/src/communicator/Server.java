package communicator;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;



public class Server implements Communicator{
	final int PORT = 4444;
	ServerSocket serverSocket = null;
	ServerSocket broadcastSocket = null;
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
			
			broadcastSocket = new ServerSocket(PORT+1);
			new Thread(new Broadcast()).start();			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public synchronized void reset(){
		for(ObjectOutputStream out: objOutputs){
			try {
				if(out != null){
					out.reset();
				}
			} catch (IOException e) {
				System.out.println("reset did not work");
				e.printStackTrace();
			}
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
	
	public synchronized void sendObject(Object o, int client){
		if(objOutputs.get(client) != null){
			try {
				objOutputs.get(client).writeUnshared(o);
			} catch (IOException e) {
				System.out.println("connection probably lost: server");
				removeFromClients(client);
				e.printStackTrace();
			}
		}
	}
	
	public Object getNextObject(int client){
		Object ret = null;
		try {
			ret = objInputs.get(client).readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("connection probably lost: server");
			removeFromClients(client);
			e.printStackTrace();
		}
		return ret;
	}
	
	public ArrayList<ObjectInputStream> getObjInputs(){
		return objInputs;
	}
	
	private void removeFromClients(int client){
		objOutputs.set(client, null);	
		objInputs.set(client, null);		
		clientSockets.set(client, null);
	}
	
	public void close() {
		try {
			for(int i = 0; i<clientSockets.size(); i++){
				objOutputs.get(i).close();
				objInputs.get(i).close();
				clientSockets.get(i).close();
				removeFromClients(i);
			}
			serverSocket.close();
		} catch (IOException e) {
			System.out.println("Sockets failed to close.");
		}
	}
	
	class Broadcast implements Runnable{
		public void run(){
			while(true){
				try{
					broadcastSocket.accept();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	class Accepter implements Runnable{
		public void run() {
			int i = 0;
			while(true){
				try {
					System.out.println("\nwaiting for client");
					Socket client = serverSocket.accept();
					ObjectInputStream in = new ObjectInputStream(client.getInputStream());
					ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
					clientSockets.add(client);
					objInputs.add(in);
					objOutputs.add(out);
					System.out.println("client " + i + " connected");
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
