package utilities;

import java.util.ArrayList;
import java.util.Queue;

public class Timer implements Runnable {

	private static ArrayList<Long> endTimes = new ArrayList<Long>();
	private boolean endTimer = false; 
	private Queue<SignalPack> queue = null;
	
	public Timer(Queue<SignalPack> signalQueue) {
		queue = signalQueue;
		new Thread(this).start();
	}

	public static void setTimer(int milliseconds){
		endTimes.add(System.currentTimeMillis() + milliseconds);
	}
	
	public void endTimer(){
		endTimer = true;
	}
	
	public void run() {
		while(!endTimer){
			for(int i = 0; i < endTimes.size(); i++){
				if(System.currentTimeMillis() >= endTimes.get(i)){
					queue.offer(SignalPack.TIMERDONE);
					endTimes.remove(i);
					i--;
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
