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
	Point2D location = null;
	
	public RunClient(Client client, Pong pong){
		this.client = client;
		this.pong = pong;
		new Thread(new ProcessBall());
	}
	
	public void run() {
		Object o = null;
    	while(true){
			o = client.getNextObject();
			if(o == null){
				continue;
			} 
			else if(o instanceof Point2D){ //Point2D always a ball location
				location = (Point2D) o;
			} else {
				new Thread(new ProcessObject(o)).start();
			}
    	}
	}
	
	class ProcessBall implements Runnable{
		public void run() {
			Point2D prevLocation = null;
			while(true){
				if(!prevLocation.equals(location) && location != null){
					pong.getBall().setLocation((Point2D) location);
					prevLocation = location;
				}
			}
		}
	}
	
	class ProcessObject implements Runnable{
		Object o;
		public ProcessObject(Object o){
			this.o = o;
		}
		public void run() {
			if(o instanceof double[]){ //double[] is paddle centers of the ball objects
				double[] center = (double[]) o;
				int c_side = (int)Math.round(center[0]);
				if(c_side != pong.getSide()){
					pong.getPolygon().getSide(c_side).getPaddle().setCenter(center[1]);
				}
			}
			else if(o instanceof Score){
				pong.setScore((Score)o);
				pong.getScore().printScore();
			}
			else if(o instanceof Polygon){
				pong.setPolygon((Polygon) o); //this will only work once, afterwards reset required
			} 
			else if(o instanceof Ball){
				System.out.println("Ball");
				pong.setBall((Ball) o); //this will only work once, afterwards reset required
			}
			else if(o instanceof Integer){ //Integers are sides
				pong.setSide((Integer) o);
			} else {
				System.out.println("not understood object type");
				System.out.println(o);
			}
		}
	}
}