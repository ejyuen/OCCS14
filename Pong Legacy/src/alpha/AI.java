/*
 * AI.java
 */

package alpha;
import java.awt.geom.Line2D;

import alpha.serializable.Ball;
import alpha.serializable.Side;
/**
 * Runs the Non-human player paddle and runs as a side
 *
 * @author 2009-2010 WHS
 * <a href="http://winchester.k12.ma.us/~dpetty/apcs/">APCS</a> class
 */
public class AI extends Side {
	/**
	 * The Ball object which is used for Coordinates
	 */
	private Ball ball;
	/**
	 * The Paddle's movement variable
	 */
	private final int MOVE = 1;
	/**
	 * Constructs a new AI object
	 * @param X1 The first X coordinate for the paddle
	 * @param Y1 The first Y cooridnate for the paddle
	 * @param X2 The second X coordinate for the paddle
	 * @param Y2 The second Y cooridnate for the paddle
	 * @param numSide The side number
	 * @param x The Ball used by move()
	 */
	public AI(double X1, double Y1, double X2, double Y2, int numSide, Ball x) {
		super(X1, Y1, X2, Y2, Side.Status.OCCUPIED, numSide);
		ball = x;
	}
	
	public boolean ballInt$$() {
		return true; //STUB
	}
	
	public void move() {
		
	}
}