package sdlNetwork;

import java.util.ArrayList;

import sdlNetwork.*;

public class SDLNetwork {
	private ArrayList<State> states;
	
	public SDLNetwork(){
		states = new ArrayList<State>();
	}
		
	public void addState(State s){
		states.add(s);
	}
		
	public ArrayList<State> getStates(){
		return states;
	}
}
