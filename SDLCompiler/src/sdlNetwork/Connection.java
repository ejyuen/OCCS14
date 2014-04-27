package sdlNetwork;

import java.util.ArrayList;

public class Connection{
	private State startState;
	private State endState;
	private Signal signal;
	private ArrayList<Action> actions;
	
	public Connection(State startState, State endState, 
			Signal signal, ArrayList<Action> actions){
		this.startState = startState;
		this.endState = endState;
		this.signal = signal;
		this.actions = actions;
	}
	
	public State getStartState() {
		return startState;
	}
	
	public State getEndState() {
		return endState;
	}
	
	public Signal getSignal() {
		return signal;
	}
	
	public ArrayList<Action> getActions() {
		return actions;
	}
	
	public String toString(){
		return "" + startState + " " + signal + " " + actions + " " + endState; 
	}
}
