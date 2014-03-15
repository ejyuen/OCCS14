package utilities;

import java.util.Queue;

public class Timer implements Runnable {
	
	private long sleepTime;
	private Queue<SignalList> queue = null;

	public static void startTimer(Queue<SignalList> signalQueue, int miliseconds){
		new Thread(new Timer(signalQueue, miliseconds)).start();
	}

	
	public Timer(Queue<SignalList> signalQueue, long miliseconds){
		queue = signalQueue;
		sleepTime = miliseconds;
	}
	
	public void run() {
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		queue.offer(SignalList.TIMERDONE);
	}
}
