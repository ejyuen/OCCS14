package sdlNetwork;

import java.util.ArrayList;

import sdlNetwork.*;

public class SDLNetwork {
	private ArrayList<Action> actions;
	private ArrayList<Signal> signals;
	private ArrayList<State> states;
	private ArrayList<Connection> connections;
	
	public SDLNetwork(){
		actions = new ArrayList<Action>();
		signals = new ArrayList<Signal>();
		states = new ArrayList<State>();
		connections = new ArrayList<Connection>();
	}
	
	public void addAction(Action a){
		actions.add(a);
	}
	
	public void addSignal(Signal s){
		signals.add(s);
	}
	
	public void addState(State s){
		states.add(s);
	}
	
	public void addConnection(Connection c){
		connections.add(c);
	}
	
	public ArrayList<Action> getActions(){
		return actions;
	}
	
	public ArrayList<Signal> getSignals(){
		return signals;
	}
	
	public ArrayList<State> getStates(){
		return states;
	}
	
	public ArrayList<Connection> getConnections(){
		return connections;
	}
}
