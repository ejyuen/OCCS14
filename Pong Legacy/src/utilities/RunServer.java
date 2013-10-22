package utilities;


import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

import pong.Pong;

import communicator.Server;


public class RunServer implements Runnable{
	Server server = null;
	Pong pong = null;
	int client = -1;
	
	public RunServer(Server server, int client, Pong pong){
		this.server = server;
		this.pong = pong;
		this.client = client;
	}
	
	public void run() {
    	while(server.getObjInputs().get(client)!=null){
			Object o = server.getNextObject(client);
			
			if(o instanceof double[]){ //paddlelocation in the format [side, location]
				double[] paddleLocation = (double[]) o;
				pong.getPolygon().getSide((int)Math.round(paddleLocation[0])).
						getPaddle().setCenter(paddleLocation[1]);
				server.sendObject(paddleLocation);
			}
    	}
	}
}