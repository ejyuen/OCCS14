package alpha.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

import alpha.Pong;
import alpha.communicator.Server;

public class RunServer implements Runnable{
	Server server = null;
	Pong pong = null;
	Socket socket = null;
	ObjectInputStream objInput = null;
	
	public RunServer(Server server, Socket socket, Pong pong){
		this.server = server;
		this.socket = socket;
		this.pong = pong;
		
		try {
			objInput = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
    	while(true){
			Object o = getNextObject();
			
			if(o instanceof double[]){ //paddlelocation in the format [side, location]
				double[] paddleLocation = (double[]) o;
				pong.getPolygon().getSide((int)Math.round(paddleLocation[0])).
						getPaddle().setCenter(paddleLocation[1]);
				server.sendObject(paddleLocation);
			}
    	}
	}
	
	public Object getNextObject(){
		Object ret = null;
		try {
			ret = objInput.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}
}
