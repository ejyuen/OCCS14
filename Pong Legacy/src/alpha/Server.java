package alpha;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	final int PORT = 4444;
	ServerSocket serverSocket = null;
	Socket[] clientSockets = null;
	PrintWriter[] stringOutputs = null;
	BufferedReader[] stringInputs = null;
	ObjectOutputStream[] objOutputs = null;
	ObjectInputStream[] objInputs = null;
	
	public Server(){
		this(0); //defaults to no client
	}
	
	public Server(int numberOfClients) {
		clientSockets = new Socket[numberOfClients];
		stringOutputs = new PrintWriter[numberOfClients];
		stringInputs = new BufferedReader[numberOfClients];
		objOutputs = new ObjectOutputStream[numberOfClients];
		objInputs = new ObjectInputStream[numberOfClients];
		
		try {
			serverSocket = new ServerSocket(PORT);
		} catch (IOException e) {
			System.out.println("Could not listen on port: " + PORT);
		}
		
		for(int i = 0; i<numberOfClients; i++){
			Socket clientSocket = null;
			try {
				System.out.println("Waiting for socket server");
				clientSocket = serverSocket.accept();
				clientSockets[i] = clientSocket;
			} catch (IOException e) {
				System.out.println("Client accepting failed.");
			}
		
			try {
				//outputs
				OutputStream os = clientSocket.getOutputStream();
				stringOutputs[i] = new PrintWriter(os, true);
				objOutputs[i] = new ObjectOutputStream(os);
				
				//inputs
				InputStream is = clientSocket.getInputStream();
				stringInputs[i] = new BufferedReader(new InputStreamReader(is));
				objInputs[i] = new ObjectInputStream(is);
			} catch (IOException e) {
				System.out.println("Streams failed to instantiate.");
			}
			//sendString("Client number is "+i, i);
			System.out.println("Client " + i + " has been instantiated");
		}
		System.out.println("Server loaded");		
	}
	
	public void sendObject(Object o){
		for(int i = 0; i<objOutputs.length; i++){
			sendObject(o, i);
		}
	}
	
	public void sendObject(Object o, int client){
		try {
			objOutputs[client].writeObject(o);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendString(String s) {
		for(int i = 0; i<stringOutputs.length; i++){
			sendString(s, i);
		}
	}
	
	public void sendString(String s, int client){
		stringOutputs[client].println(s);
	}
	
	public String[] getNextLines() {
		String[] strings = new String[clientSockets.length];
		for(int i = 0; i<clientSockets.length; i++){
			try {
				String in = stringInputs[i].readLine();
				if(in == null){
					in = "";
				}
				strings[i] = in;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return strings;
	}
	
	public Object[] getNextObjects(){
		Object[] objects = new Object[clientSockets.length];
		for(int i = 0; i<clientSockets.length; i++){
			try {
				objects[i] = objInputs[i].readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return objects;
	}
	
	public void close() {
		try {
			for(int i = 0; i<clientSockets.length; i++){
				stringOutputs[i].close();
				stringInputs[i].close();
				clientSockets[i].close();
			}
			serverSocket.close();
		} catch (IOException e) {
			System.out.println("Sockets failed to close.");
		}
	}
}
