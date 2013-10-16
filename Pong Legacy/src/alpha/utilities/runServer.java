package alpha.utilities;

import alpha.Pong;
import alpha.communicator.Server;

public class runServer implements Runnable{
	Server server = null;
	Pong pong = null;
	
	public runServer(Server server, Pong pong){
		this.server = server;
		this.pong = pong;
	}
	
	public void run() {
		Object[] objects = null;
    	while(true){
    		//input stuff
			objects = server.getNextObjects();
			for(Object o: objects){
				if(o instanceof double[]){ //paddlelocation in the format [side, location]
					double[] paddleLocation = (double[]) o;
					pong.getPolygon().getSide((int)Math.round(paddleLocation[0])).
							getPaddle().setCenter(paddleLocation[1]);
					server.sendObject(paddleLocation);
				}
			}
    	}
	}
}
