package main;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import sdlNetwork.SDLNetwork;
import sdlNetwork.State;
import sensors.Sensor;
import utilities.SensorReader;
import utilities.SignalList;

public class SDLRunner implements Runnable{
	private Queue<SignalList> queue = null; //make sure implementation is threadsafe
	private State state = null;
	private ArrayList<Sensor> sensors = null;
	private boolean done = false;
	private SDLNetwork network = null;
	
	public SDLRunner(ArrayList<Sensor> inputSensors, SDLNetwork network){
		this.network = network;
		queue = new ConcurrentLinkedQueue<SignalList>();
		sensors = inputSensors == null? new ArrayList<Sensor>(): inputSensors;

		for(Sensor s: sensors){
			new Thread(new SensorReader(queue, s)).start();
		}
		new Thread(this).start();
	}
	
	public Queue<SignalList> getQueue(){
		return queue;
	}
	
	public void run() {
		
	}
	
	
	public static void main(String[] args) {
		
	}
}
