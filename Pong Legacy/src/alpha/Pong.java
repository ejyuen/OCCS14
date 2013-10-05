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
    	this(8);
    }
    
    public Pong(int n) {
        ball = new Ball();
        //ball.changeDirection(Math.PI * 1 / 9); // CONSISTENT DIRECTION
        polygon = new Polygon(n);
        for(int i=0; i<n; i+=2){
        	polygon.setPlayer(i, "PLAYER" + (i/2+1));
        }
//        polygon.setPlayer(0, "PLAYER1");    // RED_FLAG: test player
//        polygon.setPlayer(5, "PLAYER2");    // RED_FLAG: test player
        graphics = new Graphics(this);
        pause = new Thread(new BallPause(ball, 1000));
        pause.start();
        Timer timer = new Timer(50, new TimeAction());
        timer.start();
    }

    public Polygon getPolygon() {
        return polygon;
    }

    public Ball getBall() {
        return ball;
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
            polygon = new Polygon(8); //Testing and stuff
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

    class TimeAction extends AbstractAction {
        /**
         * Moves the ball and paddle(s) when called by the timer.
         * @param arg0 Event generated by the timer
         */
        public void actionPerformed(ActionEvent arg0) {
            if (ball.getState()==true) {
                move();
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