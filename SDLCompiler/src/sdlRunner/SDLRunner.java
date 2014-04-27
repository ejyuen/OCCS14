package sdlRunner;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import sdlNetwork.Action;
import sdlNetwork.Connection;
import sdlNetwork.SDLNetwork;
import sdlNetwork.State;
import sensors.Sensor;
import utilities.SensorReader;
import utilities.SignalPack;

public class SDLRunner implements Runnable{
	private Queue<SignalPack> queue = null; //make sure implementation is threadsafe
	private State state = null;
	private ArrayList<Sensor> sensors = null;
	private boolean done = false;
	private SDLNetwork network = null;
	
	public SDLRunner(SDLNetwork network){
		this(null, network);
	}
	
	public SDLRunner(ArrayList<Sensor> inputSensors, SDLNetwork network){
		this.network = network;
		state = network.getStartState();
		
		queue = new ConcurrentLinkedQueue<SignalPack>();
		sensors = inputSensors == null? new ArrayList<Sensor>(): inputSensors;

		for(Sensor s: sensors){
			new Thread(new SensorReader(queue, s)).start();
		}
		new Thread(this).start();
	}
	
	public Queue<SignalPack> getQueue(){
		return queue;
	}
	
	public void run() {
		while(!done){
			SignalPack s = queue.poll();
			
			//the end signal finishes the runner
			if(s.equals(SignalPack.END)){
				done = true;
				break;
			}
			
			for(Connection c : state.getConnections()){
				if(c.getSignal().getSignalPack().equals(s)){
					for(Action a: c.getActions()){
						new Thread(a).start();
					}
					state = c.getEndState();
					break;
				}
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
