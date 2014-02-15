package sdlObject;

import java.util.ArrayList;

public class State extends SDLObject{
	
	private ArrayList<Connection> outboundConnections;
	
	public State(String name){
		super(name);
	}
}
