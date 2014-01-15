package main;

import java.util.Queue;

import drive.Driver;

/**
 * Main method for the smartdrive project
 * 
 * @author Sayak Chatterjee
 *
 */
public enum State{
	STOP, DRIVE, WAITSIGN, BLOCK, PARK
}

public enum Signal{
	STOP, TIMERDONE, INTERSECTION, GO, LEFT, RIGHT, INTERSECTIONDONE, DONEDRIVING, DONEPARKING, NULL
}
public class SmartDrive() {
	/*
	 * The way we do this since the program starts in Stop, either we need another signal for drive, or just generate a
	 * TIMERONE signal at the start
	 * 
	 * 
	 * Also I realize that PARK is not a very good state, i just wanted a way to stop the program, so we can discuss that
	 * at the meeting
	 * 
	 * The NULL signal will be used with the real thing, but in this predetermined queue format its not useful
	 */
	
	public void Drive(Queue<Signal> queue){
	Queue<Signal> signals = queue;
	State state = State.STOP;
	Signal signal = Signal.STOP;
	boolean doneVariable = false;
		while(!doneVariable){
			signal = signals.poll();
			switch(state){
			case STOP:
				switch(signal){
				case TIMERDONE:
					System.out.println("signal: Timer Done, result: linefollowing, state = Drive");
					state = DRIVE;
					break;
				default: 
					break;
				}
				break;
			case DRIVE:
				switch(signal){
				case STOP:
					System.out.println("signal: Stop sign, result: stopping for the law, state = STOP");
					state = STOP;
					break;
				case INTERSECTION:
					System.out.println("signal: Intersection spotted, result: straightening and waiting, state = WAITSIGN");
					state = WAITSIGN;
					break;
				case DONEDRIVING:
					System.out.println("I'm done with this driving stuff, i will now stop, state = PARK");
					state = PARK;
					break;
				default: 
					break;
				}
				break;
			case WAITSIGN:
				switch(signal){
				case GO:
					System.out.println("signal: go straight,  result: going straight..., state = BLOCK");
					state = BLOCK;
					break;
				case LEFT:
					System.out.println("signal: go left,  result: going forward and then left..., state = BLOCK");
					state = BLOCK;
					break;
				case RIGHT:
					System.out.println("signal: go right,  result: going right..., state = BLOCK");
					state = BLOCK;
					break;
				default:
					break;
				}
				break;
			case BLOCK:
				switch(signal){
				case INTERSECTIONDONE:
					System.out.println("signal: done with intersection,  result: will now resume line following, state = DRIVE");
					state = DRIVE;
					break;
				default:
					break;
				}
				break;
			case PARK:
				switch(signal){
				case DONEPARKING:
					System.out.println("Now done with parking");
					doneVariable = true;
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
	
	/*
	 * I'm not sure if there was a better way to add stuff to the queue
	 * 
	 */
	public static void main(String[] args) {
		//new Driver(); not needed for this prototype
		Queue<Signal> queue = new Queue<Signal>();
		queue.add(TIMERDONE);
		queue.add(INTERSECTION);
		queue.add(GO);
		queue.add(INTERSECTIONDONE);
		queue.add(STOP);
		queue.add(TIMERDONE);
		queue.add(INTERSECTION);
		queue.add(LEFT);
		queue.add(INTERSECTIONDONE);
		queue.add(INTERSECTION);
		queue.add(RIGHT);
		queue.add(INTERSECTIONDONE);
		queue.add(DONEDRIVING);
		queue.add(DONEPARKING);
		SmartDrive(queue);
	}
}
