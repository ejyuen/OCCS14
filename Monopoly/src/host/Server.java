package host;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import utilities.Constants;

public class Server {
	ServerSocket broadcastSocket = null;

	ServerSocket serverSocket = null;
	ArrayList<Socket> clientSockets = null;
	ArrayList<ObjectOutputStream> objOutputs = null;
	ArrayList<ObjectInputStream> objInputs = null;

	int writeCount = 0;

	public Server() {
		try {
			serverSocket = new ServerSocket(Constants.PORT);
			
			clientSockets = new ArrayList<Socket>();
			objOutputs = new ArrayList<ObjectOutputStream>();
			objInputs = new ArrayList<ObjectInputStream>();

			new Thread(new Accepter()).start();

			broadcastSocket = new ServerSocket(Constants.BROADCAST_PORT);
			new Thread(new Broadcast()).start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized void reset() {
		for (int i = 0; i < objOutputs.size(); i++) {
			try {
				if (objOutputs.get(i) != null) {
					objOutputs.get(i).reset();
				}
			} catch (IOException e) {
				System.out.println("reset did not work");
				e.printStackTrace();
			}
		}
	}

	public int getNumClients() {
		return clientSockets.size();
	}
	
	public synchronized void sendObject(Object o) {
		for (int i = 0; i < objOutputs.size(); i++) {
			sendObject(o, i);
		}
		if (writeCount == 1000) {
			reset();
			writeCount = 0;
		}
		writeCount++;
	}

	public synchronized void sendObject(Object o, int client) {
		if (objOutputs.get(client) != null) {
			try {
				objOutputs.get(client).writeUnshared(o);
			} catch (IOException e) {
				System.out.println("connection probably lost: server");
				removeFromClients(client);
				e.printStackTrace();
			}
		}
	}

	public Object getNextObject(int client) {
		Object ret = null;
		try {
			ret = objInputs.get(client).readUnshared();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("connection probably lost: server");
			removeFromClients(client);
			e.printStackTrace();
		}
		return ret;
	}

	public ArrayList<ObjectInputStream> getObjInputs() {
		return objInputs;
	}

	private void removeFromClients(int client) {
		objOutputs.set(client, null);
		objInputs.set(client, null);
		clientSockets.set(client, null);
	}
	
	/**
	 * rewrite this cleanup
	 */
	public void cleanUp(){
		int size = clientSockets.size();
		for(int i = 0; i < size; i++) {
			if(clientSockets.get(i) == null){
				objOutputs.remove(i);
				objInputs.remove(i);
				clientSockets.remove(i);
				size--;
				i--;
			} else {
				sendObject(new Integer((i + 1) * 2), i);
			}
		}
	}

	public void close() {
		try {
			for (int i = 0; i < clientSockets.size(); i++) {
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

	class Broadcast implements Runnable {
		public void run() {
			while (true) {
				try {
					broadcastSocket.accept();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	
	class Accepter implements Runnable {
		public void run() {
			int i = 0;
			while (true) {
				try {
					System.out.println("\nwaiting for client");
					Socket client = serverSocket.accept();
					ObjectInputStream in = new ObjectInputStream(client.getInputStream());
					ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
					clientSockets.add(client);
					objInputs.add(in);
					objOutputs.add(out);
					i = clientSockets.size()-1;
					
					System.out.println("client " + i + " connected");
					Object name = getNextObject(i); //note that we read for name first
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
