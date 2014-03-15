package sdlNetwork;

import java.util.ArrayList;

public class State{
	
	private ArrayList<Connection> outboundConnections = new ArrayList<Connection>();
	private String name;
	
	public State(String name){
		this.name = name;
	}
	
	public void addConnection(Connection c){
		outboundConnections.add(c);
	}
	
	public String getName() {
		return name;
	}
	
	public boolean equals(State s){
		return name.equals(s.name);
	}
	
	public ArrayList<Connection> getConnections() {
		return outboundConnections;
	}
	
	public String toString(){
		return name;
	}
}
