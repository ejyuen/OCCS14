package sdlNetwork;

import java.util.ArrayList;

public class Connection{
	private State startState;
	private Signal signal;
	private Object[] connectionActions;
	
	//Decision and endState cannot both be not equal to null
	private State endState = null;
	private Decision decision = null;
	
	public Connection(State startState, State endState, 
			Signal signal, Object[] actions){
		this.startState = startState;
		this.endState = endState;
		this.signal = signal;
		this.connectionActions = actions;
		System.out.println(connectionActions);
	}
	
	public Connection(State startState, Decision decision, 
			Signal signal, Object [] actions){
		this.startState = startState;
		this.decision = decision;
		this.signal = signal;
		this.connectionActions = actions;
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
	
	public Object[] getActions() {
		return connectionActions;
	}
	
	public String toString(){
		String res = "";
		for(int i = 0; i < connectionActions.length; i++){
			res+= connectionActions[i];
		}
		return "" + startState + " " + signal + " " + res + " " + endState; 
	}
}
