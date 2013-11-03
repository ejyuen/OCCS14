package pong;
/*
 * Pong.java
 */




import graphics.Graphics;

import java.awt.event.*;
import java.awt.geom.Point2D;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

import communicator.Client;
import communicator.Communicator;
import communicator.Server;

import serializable.Ball;
import serializable.Player;
import serializable.Polygon;
import serializable.Score;
import utilities.BallPause;
import utilities.RunClient;
import utilities.RunServer;

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
			((Server)comm).sendObject(new Integer((i+1)*2), i);
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
			/*Ignore this!!! -Neil
			 * 
			 * D.Double[-54.0935293600956, -458.8222859467143]
					Point2D.Double[-58.30860956997318, -494.5746718646401]
					Point2D.Double[-62.64077534123625, -531.3201796136194]
					Point2D.Double[-67.09002667388481, -569.0588091936522]
					Point2D.Double[-71.77344912930434, -608.783682435792]
					Point2D.Double[-76.57395714610936, -649.5016775089853]
					Point2D.Double[-81.49155072429987, -691.212794413232]
					Point2D.Double[-88.3874857249761, -664.2957612646999]
					Point2D.Double[-93.53925042593758, -620.5984006983462]
					Point2D.Double[-98.92518624967005, -574.9147964698855]
					Point2D.Double[-104.4282076347
				*/
			score.addStrike(loseSide/2);
			score.printScore();
			
			if(comm instanceof Server){
				((Server) comm).reset();
				comm.sendObject(score);
				comm.sendObject(polygon);
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