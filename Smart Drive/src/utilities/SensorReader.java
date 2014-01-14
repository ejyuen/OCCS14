package utilities;

import java.util.Queue;
import sensors.Sensor;

public class SensorReader implements Runnable{
	private Queue<Signal> queue = null;
	private Sensor sensor = null;
	public SensorReader(Queue<Signal> signalQueue, Sensor sensor){
		queue = signalQueue;
		this.sensor = sensor;
	}
	
	public void run() {
		while(sensor != null){
			Signal s = sensor.getSignal();
			if(s != null){
				queue.offer(s);	
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
