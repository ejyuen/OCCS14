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
	Point2D location = new Point2D.Double(0, 0);;
	
	public RunClient(Client client, Pong pong){
		this.client = client;
		this.pong = pong;
		new Thread(new ProcessBall()).start();
	}
	
	public synchronized void setLocation(Point2D p){
		location = p;
	}
	
	public void run() {
		Object o = null;
    	while(true){
			o = client.getNextObject();
			if(o != null){
				new Thread(new ProcessObject(o)).start();
			} else if(o instanceof Point2D){ //Point2D always a ball location
				setLocation((Point2D) o);
			} else if(o instanceof double[]){ //double[] is paddle centers of the ball objects
				double[] center = (double[]) o;
				int c_side = (int)Math.round(center[0]);
				if(c_side != pong.getSide()){
					pong.getPolygon().getSide(c_side).getPaddle().setCenter(center[1]);
				}
			} else if(o instanceof Score){
				pong.setScore((Score)o);
				pong.getScore().printScore();
			} else if(o instanceof Polygon){
				pong.setPolygon((Polygon) o); //this will only work once, afterwards reset required
			} else if(o instanceof Ball){
				System.out.println("Ball");
				pong.setBall((Ball) o); //this will only work once, afterwards reset required
			} else if(o instanceof Integer){ //Integers are sides
				pong.setSide((Integer) o);
			} else {
				System.out.println("not understood object type");
				System.out.println(o);
			}
    	}
	}
	
	class ProcessBall implements Runnable{
		public void run() {
			Point2D prevLocation = new Point2D.Double(0, 0);
			while(true){
				if(!location.equals(prevLocation)){
					pong.getBall().setLocation((Point2D) location);
					prevLocation = location;
				}
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
			if(o instanceof Point2D){ //Point2D always a ball location
				setLocation((Point2D) o);
			}
			else if(o instanceof double[]){ //double[] is paddle centers of the ball objects
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