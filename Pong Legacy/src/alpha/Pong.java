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
	private Thread pause;
	private Communicator comm = null;
	private int numPlayers;
	private int side = -1;
	private int[] strikes;
	private boolean ready = false;

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
		ball = new Ball(comm);
		// ball.changeDirection(Math.PI * 1 / 9); // CONSISTENT DIRECTION
		while (!ready) {
			try {
				TimeUnit.NANOSECONDS.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (comm instanceof Server) {
		}
		polygon = new Polygon(n);
		numPlayers = n / 2;
		strikes = new int[n / 2];
		new Thread(new BallPause(ball, 1000)).start();

		if (comm instanceof Server) {
			polygon = new Polygon(((Server) (comm)).getNumClients() * 2);
			for (int i = 0; i < n; i += 2) {
				polygon.setPlayer(i, "PLAYER" + (i / 2 + 1));
			}
			initServer();
		} else if (comm instanceof Client) {
			initClient();
		} else {
			System.out.println("no client or server initialized");
		}
	}

	public void nowReady() {
		ready = true;
	}

	private void initServer() {
		comm.sendObject(polygon);
		side = 0;
		graphics = new Graphics(this, side); // begin graphics
		new Timer(36, new TimeAction()).start(); // begin ball movement

		for (int i = 0; i < numPlayers - 1; i++) {
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

	/**
	 * Updates ball direction and moves ball. Updates score if there is a goal.
	 * RED_FLAG : handle ownGoal
	 */
	public void move() {
		// Update ball position.
		// RED_FLAG: this is too much Ball code in Pong
		double minDist = polygon.getSide(0).ptLineDist(ball.getLocation());
		int loseSide;
		polygon.checkCollision(ball);
		ball.move();
		// Check for scoring.

		if (!polygon.contains(ball.getLocation())) {
			ball.stop();
			loseSide = 0;
			for (int i = 0; i < numPlayers * 2; i += 2) {
				if (polygon.getSide(i).ptLineDist(ball.getLocation()) < minDist) {
					minDist = polygon.getSide(i).ptLineDist(ball.getLocation());
					loseSide = i;
				}
			}
			System.out.println("Loseside = " + loseSide);
			strikes[loseSide / 2] = strikes[loseSide / 2] + 1;

			for (int i = 0; i < strikes.length; i++)
				System.out.println("Player " + (i + 1) + " : " + strikes[i] + " strikes");

			ball = new Ball(comm);
			// polygon = new Polygon(8); //Testing and stuff
			new Thread(new BallPause(ball, 1000)).start();
			// RED_FLAG: there are too many null checks in this method
			Player lastPlayer = ball.getLastHit();
			if (lastPlayer != null) {
				Statistics lastStatistics = Statistics.getStatistics(lastPlayer.getName());
				if (lastStatistics != null) {
					lastStatistics.scores(); // does not handle own goals
				}
			}
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