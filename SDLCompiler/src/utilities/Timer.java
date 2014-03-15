package utilities;

import java.util.Queue;

public class Timer implements Runnable {
	
	private long sleepTime;
	private Queue<SignalPack> queue = null;

	public static void startTimer(Queue<SignalPack> signalQueue, int miliseconds){
		new Thread(new Timer(signalQueue, miliseconds)).start();
	}

	
	public Timer(Queue<SignalPack> signalQueue, long miliseconds){
		queue = signalQueue;
		sleepTime = miliseconds;
	}
	
	public void run() {
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		queue.offer(SignalPack.TIMERDONE);
	}
}
