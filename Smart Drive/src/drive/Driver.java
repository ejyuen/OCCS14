package drive;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import sensors.*;
import utilities.*;

public class Driver {
	Queue<Signal> queue = null;
	State state = null;
	ArrayList<Sensor> sensors = null;
	
	
	public Driver(){
		//set default state (start state)
		queue = new LinkedList<Signal>();
		
		//add all default sensors to sensors field
		
		for(Sensor s: sensors){
			new Thread(new SensorReader(queue, s)).start();
		}
		drive();
	}
	
	public void drive(){
		while(true){
			Signal s = queue.poll();
			driveAction(s);
		}
	}
	
	private void driveAction(Signal s){
		if(s == null){
			return;
		}
		//put case switch stuff here
	}
}
