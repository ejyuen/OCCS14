package sdlObject;

import java.util.ArrayList;

public class Connection extends SDLObject{
	private State startState;
	private State endState;
	private Signal trigger;
	private ArrayList<Action> actions;
	
	public Connection(String name){
		super(name);
	}
}
