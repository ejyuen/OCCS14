package sdlNetwork;

import java.util.ArrayList;

import sdlNetwork.*;

public class SDLNetwork {
	private ArrayList<State> states;
	private State startState;
	
	public SDLNetwork(State startState){
		states = new ArrayList<State>();
		states.add(startState);
		this.startState = startState;
	}
		
	public void addState(State s){
		states.add(s);
	}
		
	public ArrayList<State> getStates(){
		return states;
	}
	
	public State getStartState(){
		return startState;
	}
}
