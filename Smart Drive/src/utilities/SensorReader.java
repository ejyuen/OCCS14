package utilities;

import java.util.Queue;
import sensors.Sensor;

public class SensorReader implements Runnable{
	Queue<Signal> queue = null;
	Sensor sensor = null;
	public SensorReader(Queue<Signal> signalQueue, Sensor sensor){
		queue = signalQueue;
		this.sensor = sensor;
	}
	
	public void run() {
		while(sensor != null){
			queue.offer(sensor.getSignal());
		}
	}
}
