/*
 * Pong.java
 */

package alpha;

import java.awt.event.*;
import java.awt.geom.Point2D;

import javax.swing.*;

import alpha.communicator.Client;
import alpha.communicator.Communicator;
import alpha.communicator.Server;
import alpha.serializable.Ball;
import alpha.serializable.Player;
import alpha.serializable.Polygon;
import alpha.utilities.*;

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
    private Communicator comm = null;
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
    	this(8, null);
    }
    
    public Pong(Communicator comm){
    	this(8, comm);
    }
    
    public Pong(int n, Communicator comm) {
    	this.comm = comm;
        ball = new Ball(comm);
        //ball.changeDirection(Math.PI * 1 / 9); // CONSISTENT DIRECTION
        polygon = new Polygon(n);
        for(int i=0; i<n; i+=2){
        	polygon.setPlayer(i, "PLAYER" + (i/2+1));
        }
        //polygon.setPlayer(0, "PLAYER1");    // RED_FLAG: test player
        //polygon.setPlayer(5, "PLAYER2");    // RED_FLAG: test player
        pause = new Thread(new BallPause(ball, 1000));
        pause.start();

        if(comm instanceof Server){
        	comm.sendObject(polygon);
        	side = 0;
        	Timer timer = new Timer(36, new TimeAction());
        	graphics = new Graphics(this, side);
        	timer.start();
        	new Thread(new runServer((Server) comm, this)).start();
       
        } else if(comm instanceof Client){
        	Object o = ((Client) comm).getNextObject();
        	if(o instanceof Integer){
        		side = (Integer) o;
        	}
        	assert side != -1; //make sure a side has been set
        	graphics = new Graphics(this, side);
        	new Thread(new runClient((Client) comm, this)).start();
        
        } else {
        	System.out.println("no client or server initialized");
        }
    }

    public Polygon getPolygon() {
        return polygon;
    }
    
    public void setPolygon(Polygon p){
    	polygon = p;
    }

    public Ball getBall() {
        return ball;
    }
    
    public void setBall(Ball b){
    	ball = b;
    }
    
    public Communicator getCommunicator(){
    	return comm;
    }
    
    public int getSide(){
    	return side;
    }
    
    public void setSide(int side){
    	this.side = side;
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
            ball = new Ball(comm);
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
}