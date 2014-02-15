package sdlNetwork;

import java.util.ArrayList;

public class Connection{
	private State startState;
	private State endState;
	private Signal signal;
	private Action action;
	
	public Connection(State startState, State endState, 
			Signal signal, Action action){
		this.startState = startState;
		this.endState = endState;
		this.signal = signal;
		this.action = action;
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
	
	public Action getAction() {
		return action;
	}
}
