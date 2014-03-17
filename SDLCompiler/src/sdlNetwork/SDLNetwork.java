package sdlNetwork;

import java.util.ArrayList;

import sdlNetwork.*;

public class SDLNetwork {
	private ArrayList<State> states;
	private State startState;
	
	public SDLNetwork(){
		states = new ArrayList<State>();
	}
		
	public void addState(State s){
		if(s.getName().equals("Start")){
			setStartState(s);
		}
		states.add(s);
	}
		
	public ArrayList<State> getStates(){
		return states;
	}
	
	public State getStartState(){
		return startState;
	}
	public void setStartState(State startState){
		this.startState = startState;
	}
}
