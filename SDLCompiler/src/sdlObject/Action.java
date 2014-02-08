package sdlObject;

import java.awt.Polygon;
import java.util.ArrayList;

public class Action extends SDLObject{
	

	private ArrayList<Signal> signals;
	private ArrayList<State> endStates;
	private static int polygonPoints = 5;
	
	public Action(String name, int[] xpoints, int[] ypoints){
		super(xpoints, ypoints, name, polygonPoints);
	}
	
	public void addSignal(Signal signal){
		signals.add(signal);
	}
	public void addEndStates(State state){
		endStates.add(state);
	}
}
