package alpha.utilities;

import java.awt.geom.Point2D;

import alpha.Pong;
import alpha.communicator.Client;
import alpha.serializable.Ball;
import alpha.serializable.Polygon;

public class runClient implements Runnable{
	Client client = null;
	Pong pong = null;
	
	public runClient(Client client, Pong pong){
		this.client = client;
		this.pong = pong;
	}
	
	public void run() {
		Object o = null;
    	while(true){
    		//reading objects in
			o = client.getNextObject();
			if(o == null){
				continue;
			} 
			else if(o instanceof Point2D){ //Point2D always a ball location
				pong.getBall().setLocation((Point2D) o);
			} 
			else if(o instanceof double[]){ //double[] is paddle centers of the ball objects
				double[] center = (double[]) o;
				int c_side = (int)Math.round(center[0]);
				if(c_side != pong.getSide()){
					pong.getPolygon().getSide(c_side).getPaddle().setCenter(center[1]);
				}
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