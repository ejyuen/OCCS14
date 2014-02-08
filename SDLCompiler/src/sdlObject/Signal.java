package sdlObject;

import java.awt.Polygon;
import java.util.ArrayList;

public class Signal extends SDLObject{
	
	private ArrayList<State> startStates;
	private ArrayList<State> endStates;
	private static int polygonPoints = 5;
	
	public Signal(String name, int[] xpoints, int[] ypoints){
		super(xpoints, ypoints, name, polygonPoints);
	}
	public void addStartStates(State state){
		startStates.add(state);
	}
	
	public void addEndStates(State state){
		endStates.add(state);
	}
	
}