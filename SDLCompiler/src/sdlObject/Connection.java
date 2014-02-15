package sdlObject;

import java.util.ArrayList;

public class Connection{
	private State startState;
	private State endState;
	private Signal trigger;
	private Action action;
	
	public Connection(State startState, State endState){
		this.startState = startState;
		this.endState = endState;
	}
}
