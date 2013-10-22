/*
 * Pong.java
 */

package alpha;

import java.awt.event.*;
import java.awt.geom.Point2D;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

import alpha.communicator.Client;
import alpha.communicator.Communicator;
import alpha.communicator.Server;
import alpha.serializable.Ball;
import alpha.serializable.Player;
import alpha.serializable.Polygon;
import alpha.serializable.Score;
import alpha.utilities.*;

/**
 * DESCRIPTION
 * 
 * @author 2009-2010 WHS <a
 *         href="http://winchester.k12.ma.us/~dpetty/apcs/">APCS</a> class
 */
public class Pong {

	private Ball ball; // game ball
	private Polygon polygon; // n-sided polygon
	private Graphics graphics; // awt graphics
	private Communicator comm = null;
	private Score score = null;
	private int side = -1;
	private boolean ready = false; //only useful if a server

	/**
	 * Defult pong game square window size.
	 */
	public static final int DEFAULT_SIZE = 1000;

	/**
	 * Create an n-sided Pong game.
	 * 
	 * @param n
	 *            number of polygon sides
	 */
	public Pong() {
		this(8, null);
	}

	public Pong(Communicator comm) {
		this(8, comm);
	}

	public Pong(int n, Communicator comm) {
		this.comm = comm;
		// ball.changeDirection(Math.PI * 1 / 9); // CONSISTENT DIRECTION
		polygon = new Polygon(n);
		ball = new Ball(comm);
		score = new Score(n);

		if (comm instanceof Client) {
			initClient();
		} else {
			System.out.println("Probably a server");
		}
	}

	public void nowReady() {
		ready = true;
	}

	public void initServer() {
		while (!ready) { //wait until server says ready
			try {
				TimeUnit.NANOSECONDS.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		int sides = (((Server)comm).getNumClients() + 1) * 2;
		
		score = new Score(sides/2); //setting score
		
		polygon = new Polygon(sides); //creating the polygon
		for (int i = 0; i < polygon.getSides().length; i += 2) {
			polygon.setPlayer(i, "PLAYER" + (i / 2 + 1));
		}
		comm.sendObject(polygon);

		new Thread(new BallPause(ball, 1000)).start();
		side = 0;
		graphics = new Graphics(this, side); // begin graphics
		
		ball = new Ball(comm); // begin ball movement
		new Thread(new BallPause(ball, 1000)).start();
		
		new Timer(36, new TimeAction()).start();
		
		for (int i = 0; i < sides/2-1; i++) { //begin reading from players
			new Thread(new RunServer((Server) comm, i, this)).start();
		}
	}

	private void initClient() {
		Object o = ((Client) comm).getNextObject();
		if (o instanceof Integer) {
			side = (Integer) o;
		}
		assert side != -1; // make sure a side has been set

		graphics = new Graphics(this, side);
		new Thread(new RunClient((Client) comm, this)).start();
	}

	public Polygon getPolygon() {
		return polygon;
	}

	public void setPolygon(Polygon p) {
		polygon = p;
	}

	public Ball getBall() {
		return ball;
	}

	public void setBall(Ball b) {
		ball = b;
	}

	public Communicator getCommunicator() {
		return comm;
	}

	public int getSide() {
		return side;
	}

	public void setSide(int side) {
		this.side = side;
	}
	
	public Score getScore(){
		return score;
	}
	
	public void setScore(Score score){
		this.score = score; 
	}

	/**
	 * Updates ball direction and moves ball. Updates score if there is a goal.
	 * RED_FLAG : handle ownGoal
	 */
	public void move() {
		// Update ball position.
		// RED_FLAG: this is too much Ball code in Pong
		double minDist = polygon.getSide(0).ptLineDist(ball.getLocation());
		
		polygon.checkCollision(ball);
		ball.move();
		// Check for scoring.
		if (!polygon.contains(ball.getLocation())) {
			ball.stop();
			int loseSide = 0;
			for (int i = 0; i < score.getNumPlayers() * 2; i += 2) {
				if (polygon.getSide(i).ptLineDist(ball.getLocation()) < minDist) {
					minDist = polygon.getSide(i).ptLineDist(ball.getLocation());
					loseSide = i;
				}
			}
			
			score.addStrike(loseSide/2);
			score.printScore();
			
			if(comm instanceof Server){
				comm.sendObject(score);
			}

			ball = new Ball(comm);
			new Thread(new BallPause(ball, 1000)).start();
		}
	}

	class TimeAction extends AbstractAction {
		public void actionPerformed(ActionEvent e) {
			if (ball.getState() == true) {
				move();
			}
		}
	}
}