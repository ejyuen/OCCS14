package sdlNetwork;

import java.util.ArrayList;

public class Connection{
	private State startState;
	private Signal signal;
	private ArrayList<Action> actions;
	
	//Decision and endState cannot both be not equal to null
	private State endState = null;
	private Decision decision = null;
	
	public Connection(State startState, State endState, 
			Signal signal, ArrayList<Action> actions){
		this.startState = startState;
		this.endState = endState;
		this.signal = signal;
		this.actions = actions;
	}
	
	public Connection(State startState, Decision decision, 
			Signal signal, ArrayList<Action> actions){
		this.startState = startState;
		this.decision = decision;
		this.signal = signal;
		this.actions = actions;
	}
	
	public State getStartState() {
		return startState;
	}
	
	public State getEndState() {
		if(endState == null && decision != null){
			return decision.makeDecision();
		} else {
			return endState;
		}
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
