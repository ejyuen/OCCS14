/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import alpha.*;
import alpha.serializable.Ball;

import java.awt.geom.Point2D;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author tim_costa
 */
public class BallTest {

    private Ball ball, ballArgs;

    @Before
    public void setUp() {
        ball = new Ball();
        ballArgs = new Ball(6, 11);
    }

    @After
    public void tearDown() {
        ball = null;
        ballArgs = null;
    }

    @Test
    public void testArgsRadius() {
        assertTrue(ballArgs.getRadius() == 11);
    }
    @Test
    public void testDefaultRadius() {
        assertTrue(ball.getRadius() == Ball.DEFAULT_RADIUS);
    }
    @Test
    public void testArgsSpeed() {
        assertTrue(ballArgs.getSpeed() == 6);
    }
    @Test
    public void testDefaultSpeed() {
        assertTrue(ball.getSpeed() ==Ball.DEFAULT_SPEED);
    }
    @Test
    public void testDirection() {
        assertTrue(ball.getDirection()<=(2 * Math.PI) 
            && ball.getDirection()>=0.00);
    }
    @Test
    public void testSetSpeed() {
        ball.changeSpeed(10);
        assertTrue(ball.getSpeed() == 10);
    }
    @Test
    public void testChangeDirection() {
        final double direction = 1.0;
        ball.changeDirection(direction);
        //using assertEquals for doubles
        assertEquals(direction, ball.getDirection(), .0001);
    }
    @Test
    public void testGetNextDirection() {
        double direction = ball.getDirection();
        double speed = ball.getSpeed();
        Point2D location = ball.getLocation();

        // RED_FLAG: this replicates the algorithm... can we test another way?
        double newX = Math.cos(direction) * speed + location.getX();
        double newY = -1.0 * Math.sin(direction) * speed + location.getY();
        Point2D nextLocation = new Point2D.Double(newX, newY);

        assertTrue(ball.getNextLocation().equals(nextLocation));
    }
    @Test
    public void testMove() {
        Point2D nextLocation = ball.getNextLocation();
        ball.move();
        assertTrue(ball.getLocation().equals(nextLocation));
    }
}
