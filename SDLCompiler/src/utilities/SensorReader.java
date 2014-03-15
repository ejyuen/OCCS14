package utilities;

import java.util.Queue;
import sensors.Sensor;

public class SensorReader implements Runnable{
	private Queue<SignalList> queue = null;
	private Sensor sensor = null;

	public SensorReader(Queue<SignalList> signalQueue, Sensor sensor){
		queue = signalQueue;
		this.sensor = sensor;
	}
	
	public void run() {
		while(sensor != null){
			SignalList s = sensor.getSignal();
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
