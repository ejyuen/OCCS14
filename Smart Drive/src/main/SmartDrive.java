package main;

import java.util.LinkedList;
import java.util.Queue;

import drive.Driver;
import utilities.*;

/**
 * Main method for the smartdrive project
 * 
 * @author Sayak Chatterjee
 *
 */

public class SmartDrive {

	
	public static void main(String[] args) {
		Driver d = new Driver();
		
		//for testing purposes
		Queue<Signal> queue = d.getQueue();
		queue.add(Signal.TIMERDONE);
		
		queue.add(Signal.INTERSECTION);
		queue.add(Signal.STRAIGHTEN_DONE);
		queue.add(Signal.GO);
		queue.add(Signal.INTERSECTION_DONE);
		
		queue.add(Signal.STOP);
		queue.add(Signal.TIMERDONE);
		
		queue.add(Signal.INTERSECTION);
		queue.add(Signal.STRAIGHTEN_DONE);
		queue.add(Signal.LEFT);
		queue.add(Signal.INTERSECTION_DONE);
		
		queue.add(Signal.INTERSECTION);
		queue.add(Signal.STRAIGHTEN_DONE);
		queue.add(Signal.RIGHT);
		queue.add(Signal.INTERSECTION_DONE);
		
		queue.add(Signal.PARK);
	}
}
