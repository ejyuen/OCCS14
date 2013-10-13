/*
 * Pong.java
 */

package alpha;

import java.awt.event.*;
import java.awt.geom.Point2D;

import javax.swing.*;

import alpha.serializable.Ball;
import alpha.serializable.Player;
import alpha.serializable.Polygon;
import alpha.serializable.Side;

/**
 * DESCRIPTION
 *
 * @author 2009-2010 WHS
 * <a href="http://winchester.k12.ma.us/~dpetty/apcs/">APCS</a> class
 */
public class Pong{

    private Ball ball;          // game ball
    private Polygon polygon;    // n-sided polygon
    private Graphics graphics;  // awt graphics
    private Thread pause;
    private Server server = null;
    private Client client = null;
    private int side = -1;
    
    /**
     * Defult pong game square window size.
     */
    public static final int DEFAULT_SIZE = 1000;

    /**
     * Create an n-sided Pong game.
     *
     * @param n number of polygon sides
     */
    public Pong(){
    	this(8, null, null);
    }
    
    public Pong(Client client){
    	this(8, client);
    }
    
    public Pong(int n, Client client){
    	this(n, null, client);
    }
    
    public Pong(Server server){
    	this(8, server);
    }
    
    public Pong(int n, Server server){
    	this(n, server, null);
    }
    
    public Pong(int n, Server server, Client client) {
    	this.client = client;
    	this.server = server;
        ball = new Ball(server);
        //ball.changeDirection(Math.PI * 1 / 9); // CONSISTENT DIRECTION
        polygon = new Polygon(n);
        for(int i=0; i<n; i+=2){
        	polygon.setPlayer(i, "PLAYER" + (i/2+1));
        }
        //polygon.setPlayer(0, "PLAYER1");    // RED_FLAG: test player
        //polygon.setPlayer(5, "PLAYER2");    // RED_FLAG: test player
        pause = new Thread(new BallPause(ball, 1000));
        pause.start();

        if(server != null){
        	server.sendObject(polygon);
        	side = 0;
        	Timer timer = new Timer(40, new TimeAction());
        	graphics = new Graphics(this, side);
        	timer.start();
        	
        	new Thread(new runServer()).start();
        } else if(client != null){
        	Object o = client.getNextObject();
        	setSideNumber(o); //need to set side for graphics to control correct paddle
        	graphics = new Graphics(this, side);
        	
        	new Thread(new runClient()).start();
        } else {
        	System.out.println("no client or server initialized");
        }
    }

    public Polygon getPolygon() {
        return polygon;
    }

    public Ball getBall() {
        return ball;
    }
    
    public Server getServer(){
    	return server;
    }
    
    public Client getClient(){
    	return client;
    }
    
    public void setSideNumber(Object o){
    	if(o instanceof Integer){
    		System.out.println("setting side");
    		side = (Integer)o;
    	} else {
    		System.out.println("o was not an integer, side not set");
    	}
    }

    /**
     * Updates ball direction and moves ball. Updates score if there is a goal.
     * RED_FLAG : handle ownGoal
     */
    public void move() {
        // Update ball position.
        // RED_FLAG: this is too much Ball code in Pong
    	polygon.checkCollision(ball);
        ball.move();
        // Check for scoring.
        if (!polygon.contains(ball.getLocation())) {
            ball.stop();
            ball = new Ball(server);
            //polygon = new Polygon(8); //Testing and stuff
            pause = new Thread(new BallPause(ball, 1000));
            pause.start();
            // RED_FLAG: there are too many null checks in this method
            Player lastPlayer = ball.getLastHit();
            if (lastPlayer != null) {
                Statistics lastStatistics = 
                    Statistics.getStatistics(lastPlayer.getName());
                if (lastStatistics != null) {
                    lastStatistics.scores();    // does not handle own goals
                }
            }
        }
    }
    
    class TimeAction extends AbstractAction{
		public void actionPerformed(ActionEvent e) {
			if (ball.getState()==true) {
                move();
            }
		}
    }
    
    class runServer implements Runnable{
		public void run() {
			Object[] objects = null;
	    	while(true){
	    		//input stuff
				objects = server.getNextObjects();
				for(Object o: objects){
					if(o instanceof double[]){ //paddlelocation in the format [side, location]
						double[] paddleLocation = (double[]) o;
						polygon.getSide((int)Math.round(paddleLocation[0])).
								getPaddle().setCenter(paddleLocation[1]);
						server.sendObject(paddleLocation);
					}
				}
	    	}
		}
    }
    
    class runClient implements Runnable{
		public void run() {
			Object o = null;
	    	while(true){
	    		//reading objects in
				o = client.getNextObject();
				if(o == null){
					System.out.println("no object");
				} 
				else if(o instanceof Polygon){
					System.out.println("polygon");
					polygon = (Polygon) o; //this will only work once, afterwards reset required
				} 
				else if(o instanceof Ball){
					System.out.println("Ball");
					ball = (Ball) o; //this will only work once, afterwards reset required
				}
				else if(o instanceof Integer){ //Integers are 
					setSideNumber(o);
				} 
				else if(o instanceof Point2D){ //Point2D always a ball location
					System.out.println("ball location");
					ball.setLocation((Point2D) o);
				} 
				else if(o instanceof double[]){ //double[] is paddle centers of the ball objects
					double[] center = (double[]) o;
					int c_side = (int)Math.round(center[0]);
					if(c_side != side){
						polygon.getSide(c_side).getPaddle().setCenter(center[1]);
					}
				}
				System.out.println("read objects");
	    	}
		}
    }
}

class BallPause implements Runnable {
	Ball ball;
	int sleep;
	
	public BallPause(Ball ball, int sleep){
		this.sleep = sleep;
		this.ball = ball;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ball.changeSpeed(10);
		ball.start();
		for (int i = 1; i < ball.DEFAULT_SPEED - 10; i++) {
			ball.changeSpeed(10 + i);
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ball.changeSpeed(ball.DEFAULT_SPEED);
		return;
	}	
}