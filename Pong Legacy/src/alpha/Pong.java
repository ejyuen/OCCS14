/*
 * Pong.java
 */

package alpha;

import java.awt.event.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

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
        ball = new Ball();
        //ball.changeDirection(Math.PI * 1 / 9); // CONSISTENT DIRECTION
        polygon = new Polygon(n);
        for(int i=0; i<n; i+=2){
        	polygon.setPlayer(i, "PLAYER" + (i/2+1));
        }
        //polygon.setPlayer(0, "PLAYER1");    // RED_FLAG: test player
        //polygon.setPlayer(5, "PLAYER2");    // RED_FLAG: test player
        pause = new Thread(new BallPause(ball, 1000));
        pause.start();
        
        Timer timer = null;
        int delay = 50;
        if(server != null){
        	timer = new Timer(delay, new ServerAction());
        	side = 0;
        } else if(client != null){
        	timer = new Timer(delay, new ClientAction());
        	Object o = client.getNextObject();
        	setSideNumber(o);
        } else {
        	System.out.println("no client or server initialized");
        }
        graphics = new Graphics(this, side);
        timer.start();
    }

    public Polygon getPolygon() {
        return polygon;
    }

    public Ball getBall() {
        return ball;
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
            ball = new Ball();
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
    
    class ServerAction extends AbstractAction{
		public void actionPerformed(ActionEvent arg0) {
			if (ball.getState()==true) {
                move();
            }
			
			//implement rest of server stuff below here
			server.sendObject(polygon);
			server.sendObject(ball);
		}
    }
    
    class ClientAction extends AbstractAction{
		public void actionPerformed(ActionEvent arg0) {
			Object o = client.getNextObject();
			if(o == null){
				System.out.println("no object");
			} else if(o instanceof Polygon){
				System.out.println("polygon");
				polygon = (Polygon)o;
			} else if(o instanceof Ball){
				System.out.println("ball");
				ball = (Ball)o;
			} else if(o instanceof Integer){
				setSideNumber(o);
			}
		}
    }
    
    /**
     * This method will move the paddle in the direction based upon the numeric
     * value of the key pressed. 37=Left 39=Right according to the adobe documentation
     * <a href="http://www.adobe.com/livedocs/flash/9.0/main/wwhelp/wwhimpl/common/html/wwhelp.htm?context=LiveDocs_Parts&file=00001136.html">
     * here</a>.
     *
     * NOTE: this method is probably incorrect and likely to be changed and/or 
     *       modified. It was added simply to document the numerical key values 
     *       for left and right.
     *
     * @param keyValue numeric value of the key pressed
     */
    /*public static void keyPressed(int keyValue) {
        Paddle paddle = new Paddle();
        if (keyValue == 37) paddle.moveLeft();
        if (keyValue == 39) paddle.moveRight();
    }*/
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