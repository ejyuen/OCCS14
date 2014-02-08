package sdlObject;

import java.awt.Rectangle;
import java.util.ArrayList;

public class State extends SDLObject{
	
	private ArrayList<Signal> signals;
	private ArrayList<State> states;
	private static int npoints = 4; 
	
	
	public State(String name, int[] xpoints, int[] ypoints){
		super(xpoints, ypoints, name, npoints);
	}
	
	public void addSignal(Signal signal){
		signals.add(signal);
	}
	
	public void connectState(State state){
		states.add(state);
	}
	
}
