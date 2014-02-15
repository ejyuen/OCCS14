package sdlObject;

import java.util.ArrayList;

public class State{
	
	private ArrayList<Connection> outboundConnections;
	private String name;
	
	public State(String name){
		this.name = name;
	}
	
	public void addConnection(Connection c){
		outboundConnections.add(c);
	}
}
