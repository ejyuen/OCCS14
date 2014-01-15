package drive;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import sensors.*;
import utilities.*;

public class Driver implements Runnable {
	private Queue<Signal> queue = null; //make sure implementation is threadsafe
	private State state = null;
	private ArrayList<Sensor> sensors = null;
	private boolean done = false;
	
	
	public Driver(){
		this(null);
	}
	
	public Driver(ArrayList<Sensor> inputSensors){
		state = State.STOP;
		queue = new ConcurrentLinkedQueue<Signal>();
		sensors = inputSensors == null? new ArrayList<Sensor>(): inputSensors;

		for(Sensor s: sensors){
			new Thread(new SensorReader(queue, s)).start();
		}
		new Thread(this).start();
	}
	
	public Queue<Signal> getQueue(){
		return queue;
	}
	
	public void run() {
		drive();
	}
	
	public void drive(){
		while(!done){
			Signal s = queue.poll();
			driveAction(s);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void driveAction(Signal signal){
		if(signal == null){
			return;
		}
		switch(state){
		case STOP:
			switch(signal){
			case TIMERDONE:
				System.out.println("signal: Timer Done, result: linefollowing, state = Drive");
				state = State.DRIVE;
				break;
			default: 
				break;
			}
			break;
		case DRIVE:
			switch(signal){
			case STOP:
				System.out.println("signal: Stop sign, result: stopping for the law, state = STOP");
				state = State.STOP;
				break;
			case INTERSECTION:
				System.out.println("signal: Intersection spotted, result: straightening and waiting, state = WAITSIGN");
				state = State.WAITSIGN;
				break;
			case DONEDRIVING:
				System.out.println("I'm done with this driving stuff, i will now stop, state = PARK");
				state = State.PARK;
				break;
			default: 
				break;
			}
			break;
		case WAITSIGN:
			switch(signal){
			case GO:
				System.out.println("signal: go straight,  result: going straight..., state = BLOCK");
				state = State.BLOCK;
				break;
			case LEFT:
				System.out.println("signal: go left,  result: going forward and then left..., state = BLOCK");
				state = State.BLOCK;
				break;
			case RIGHT:
				System.out.println("signal: go right,  result: going right..., state = BLOCK");
				state = State.BLOCK;
				break;
			default:
				break;
			}
			break;
		case BLOCK:
			switch(signal){
			case INTERSECTIONDONE:
				System.out.println("signal: done with intersection,  result: will now resume line following, state = DRIVE");
				state = State.DRIVE;
				break;
			default:
				break;
			}
			break;
		case PARK:
			switch(signal){
			case DONEPARKING:
				System.out.println("Now done with parking");
				done = true;
				break;
			default:
				break;
			}
			break;
		default:
			break; 	
		}
	}
}
