package drive;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import sensors.*;
import utilities.*;

public class Driver {
	Queue<Signal> queue = null; //make sure implementation is threadsafe
	State state = null;
	ArrayList<Sensor> sensors = null;
	
	
	public Driver(){
		state = null;//TODO set default state (start state)
		queue = new ConcurrentLinkedQueue<Signal>();
		
		sensors.add(null);//TODO add all default sensors to sensors field
		
		for(Sensor s: sensors){
			new Thread(new SensorReader(queue, s)).start();
		}
		drive();
	}
	
	public void drive(){
		while(true){
			Signal s = queue.poll();
			driveAction(s);
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void driveAction(Signal s){
		if(s == null){
			return;
		}
		//TODO put case switch stuff here
	}
}
