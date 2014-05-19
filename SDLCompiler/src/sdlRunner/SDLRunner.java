package sdlRunner;

import graphics.Graphics;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import sdlNetwork.Action;
import sdlNetwork.Connection;
import sdlNetwork.SDLNetwork;
import sdlNetwork.State;
import sensors.Sensor;
import utilities.Timer;
import utilities.UtilityMethods;
import utilities.SensorReader;
import utilities.SignalPack;

public class SDLRunner implements Runnable{
	private Queue<SignalPack> queue = null; //make sure implementation is threadsafe
	private State state = null;
	private ArrayList<Sensor> sensors = null;
	private boolean done = false;
	
	private SDLNetwork network = null;
	private Timer timer;
	private Graphics graphics;
	
	public SDLRunner(SDLNetwork network){
		this(network, new ArrayList<Sensor>());
	}
	
	public SDLRunner(SDLNetwork network, ArrayList<Sensor> inputSensors){
		this(network, new ArrayList<Sensor>(), new Graphics());
	}
	
	public SDLRunner(SDLNetwork network, ArrayList<Sensor> inputSensors, Graphics graphics){
		this.network = network;
		state = network.getStartState();
		this.graphics = graphics;
		graphics.setState(state);
		
		queue = new ConcurrentLinkedQueue<SignalPack>();
		sensors = inputSensors == null? new ArrayList<Sensor>(): inputSensors;

		for(Sensor s: sensors){
			new Thread(new SensorReader(queue, s)).start();
		}
		
		this.timer = new Timer(queue); //automatically starts a timer
		new Thread(this).start();
		
	}
	
	public Queue<SignalPack> getQueue(){
		return queue;
	}
	
	public ArrayList<Sensor> getSensors(){
		return sensors;
	}
	
	public void run() {
		while(!done){
			SignalPack s = queue.poll();
			
			if(s == null){
				continue;
			}
			
			//the end signal finishes the runner
			if(s.equals(SignalPack.END)){
				done = true;
				timer.endTimer();
				break;
			}
			
			for(Connection c : state.getConnections()){
				if(c.getSignal().getSignalPack().equals(s)){
					state = UtilityMethods.runConnection(c);
					graphics.setState(state);
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
