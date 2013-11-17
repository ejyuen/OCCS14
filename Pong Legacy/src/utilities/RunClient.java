package utilities;


import java.awt.geom.Point2D;

import pong.Pong;

import serializable.Ball;
import serializable.Polygon;
import serializable.Score;

import communicator.Client;


public class RunClient implements Runnable{
	Client client = null;
	Pong pong = null;
	
	public RunClient(Client client, Pong pong){
		this.client = client;
		this.pong = pong;
		new Thread(new ProcessBall()).start();
	}
	
	public void run() {
		Object o = null;
    	while(true){
			o = client.getNextObject();
			if(o == null){
				continue;
			} else if(o instanceof double[]){ //double[] is paddles in the form [side, location]
				double[] center = (double[]) o;
				int c_side = (int) (center[0] + .5);
				if(c_side != pong.getSide()){
					pong.getPolygon().getSide(c_side).getPaddle().setCenter(center[1]);
				}
			} else if(o instanceof Score){
				pong.setScore((Score)o);
				pong.getScore().printScore();
			} else if(o instanceof Polygon){
				pong.setPolygon((Polygon) o);
			} else if(o instanceof Integer){ //Integers are sides
				pong.setSide((Integer) o);
			} else {
				System.out.println("not understood object type");
				System.out.println(o);
			}
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
	}
	
	class ProcessBall implements Runnable{
		public void run() {
			Object o = null;
			while(true){
				o = client.getNextBallLocation();
				if(o!=null){
					pong.getBall().setLocation((Point2D) o);
				}
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}