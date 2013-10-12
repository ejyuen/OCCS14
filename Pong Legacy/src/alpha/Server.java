package alpha;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	final int PORT = 4444;
	ServerSocket serverSocket = null;
	Socket[] clientSockets = null;
	PrintWriter[] outputs = null;
	BufferedReader[] inputs = null;
	
	public Server(int numberOfClients) {
		clientSockets = new Socket[numberOfClients];
		outputs = new PrintWriter[numberOfClients];
		inputs = new BufferedReader[numberOfClients];
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
			} catch (IOException e) {
				System.out.println("Client accepting failed.");
			}
		
			try {
				outputs[i] = new PrintWriter(clientSocket.getOutputStream(), true);
				inputs[i] = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			} catch (IOException e) {
				System.out.println("Streams failed to instantiate.");
			}
		}
		System.out.println("Server loaded");		
	}
	
	public void send(String s) {
		for(PrintWriter out: outputs){
			out.println(s);
		}
	}
	
	public String[] getNextLines() {
		String[] ret = new String[clientSockets.length];
		for(int i = 0; i<clientSockets.length; i++){
			try {
				ret[i] = inputs[i].readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}
	
	public void close() {
		try {
			for(int i = 0; i<clientSockets.length; i++){
				outputs[i].close();
				inputs[i].close();
				clientSockets[i].close();
			}
			serverSocket.close();
		} catch (IOException e) {
			System.out.println("Sockets failed to close.");
		}
	}
}
