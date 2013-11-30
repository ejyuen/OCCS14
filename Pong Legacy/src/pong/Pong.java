package pong;

import graphics.Graphics;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
import communicator.*;
import serializable.*;
import utilities.*;

public class Pong {

	private static Ball ball; // game ball
	private static Polygon polygon; // n-sided polygon
	private Communicator comm = null;
	private Score score = null;
	private int side = -1;
	private Graphics graphics = null;
	
	private boolean ready = false; // only useful if a server
	private Timer ballAction = null;
	private int winner;
	private boolean playing;
	
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
		while (!ready) { // wait until server says ready
			try {
				TimeUnit.NANOSECONDS.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		if(comm instanceof Server){
			((Server) comm).setPong(this);
		}
		int sides = (((Server) comm).getNumClients() + 1) * 2;

		score = new Score(sides / 2); // setting score

		polygon = new Polygon(sides); // creating the polygon
		
		for (int i = 0; i < polygon.getSides().length; i += 2) {
			polygon.setPlayer(i, "PLAYER" + (i / 2 + 1));
		}

		comm.sendObject(polygon);
		comm.sendObject(score);

		side = 0;
		graphics = new Graphics(this, side);

		ball = new Ball(comm); // begin ball movement
		new Thread(new BallPause(ball, 1000)).start();

		ballAction = new Timer(36, new BallAction());
		ballAction.start();
		playing = true;

		for (int i = 0; i < sides / 2 - 1; i++) { // begin reading from players
			((Server) comm).sendObject(new Integer((i + 1) * 2), i);
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

	public static int getClosestPlayer() {
		double minDist = polygon.getSide(0).ptLineDist(ball.getLocation());
		int side = 0;

		for (int i = 0; i < polygon.getSides().length; i++) {
			if (polygon.getSide(0) instanceof Player) {
				if (polygon.getSide(i).ptLineDist(ball.getLocation()) < minDist) {
					minDist = polygon.getSide(i).ptLineDist(ball.getLocation());
					side = i;
				}
			}
		}
		return side;
	}

	public static int getClosestSide() {
		double minDist = polygon.getSide(0).ptLineDist(ball.getLocation());
		int side = 0;

		for (int i = 0; i < polygon.getSides().length; i++)
			if (polygon.getSide(i).ptLineDist(ball.getLocation()) < minDist) {
				minDist = polygon.getSide(i).ptLineDist(ball.getLocation());
				side = i;
			}

		return side;
	}
	
	public Graphics getGraphics(){
		return graphics;
	}

	public static Polygon getPolygon() {
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

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}
	
	public int getWinner(){
		return winner;
	}
	
	public boolean isPlaying(){
		return playing;
	}
	
	public void killPlayer(int playerNumber){
		if(polygon.getSide(playerNumber) instanceof Player){
			((Player) polygon.getSide(playerNumber)).killPlayer();
		}
		score.changeScore(playerNumber, 0);
	}
	
	public void endGame(){
		playing = false;
		if(ballAction != null){
			ballAction.stop();
		}
		
	}

	class BallAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {
			if (ball.getState() == true) {
				move();
			}
		}
		

		
		/**
		 * Updates ball direction and moves ball. Updates score if there is a goal.
		 *
		 */
		private void move() {
			// Update ball position.
			// RED_FLAG: this is too much Ball code in Pong

			polygon.checkCollision(ball);
			ball.move();

			// Check for scoring.
			if (!polygon.contains(ball.getLocation())) {
				ball.stop();

				score.loseLife(getClosestPlayer() / 2);
				score.printScore();
				
				if (score.getLives(getClosestPlayer() / 2) <= 0)
					killPlayer(getClosestPlayer()/2);				
				

				if (comm instanceof Server) {
					((Server) comm).reset();
					comm.sendObject(score);
					comm.sendObject(polygon);
				}
				
				int winner = 0;
				boolean isWinner = false;
				int[] lives = score.getLives();
				
				for(int i = 0; i<lives.length; i++){
					if(lives[i] != 0){
						isWinner = !isWinner;
						if(!isWinner){
							break;
						}
						winner = i;
					}
				}
				
				if(isWinner){
					System.out.println("The Winner is Player " + winner + "!!!");
					endGame();
				}

				ball = new Ball(comm);
				new Thread(new BallPause(ball, 1000)).start();
			}
		}
	}
}