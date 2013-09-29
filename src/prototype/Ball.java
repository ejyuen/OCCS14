package prototype;

import java.awt.geom.Line2D;

/**
 * The ball.
 * @author jburka
 */
public class Ball {

    private double direction;
    private double xCoord, yCoord;
    private int speed;
    private double xVect, yVect;
    private Pong board;
    private int radius;

    /**
     *
     * @param direction The direction the ball is travelling in radians
     * @param xCoord The X-Coordinate of the current location
     * @param yCoord The Y-Coordinate of the current location
     * @param speed The speed of the ball
     * @param board The name of the Pong board the ball will be loacted in
     * @param radius The radius of the ball
     */
    public Ball(double direction, double xCoord, double yCoord, int speed, Pong board, int radius) {
        this.direction = direction;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.speed = speed;
        xVect = Math.cos(direction) * speed;
        yVect = -1.0 * Math.sin(direction) * speed;
        this.board = board;
        this.radius = radius;
    }
    /**
     * The move method for the ball.
     */
    public void move() {
        double nextX = xCoord + xVect;
        double nextY = yCoord + yVect;
        Line2D.Double wall = board.getPoly().checkCollision(nextX, nextY, radius);
        if (wall == null) {
            xCoord = nextX;
            yCoord = nextY;
            board.setLoc((int) xCoord, (int) yCoord);
        }
        else bounce(nextX, nextY, wall);
    }

    /**
     * Changes the direction of the ball.
     * @param direction The new direction in radians
     */
    public void changeDirection(double direction) {
        this.direction = direction;
    }
    
    /**
     * Changes the speed of the ball.
     * @param speed The speed of the ball
     */
    public void changeSpeed(int speed) {
    	this.speed = speed;
    }

    /**
     * "Bounces" the ball off of the wall it hits.
     * @param nextX Not used currently
     * @param nextY Not used currently
     * @param wall The wall the ball is going to "Bounce" off of
     */
    private void bounce(double nextX, double nextY, Line2D.Double wall) { // This method is not quite done. At high speeds it should still have the ball hit the wall before it bounces, which does not happen right now.
        double wallSlope = (wall.y2 - wall.y1) / (wall.x2 - wall.x1);
        double wallAngle = Math.atan(wallSlope);
        direction = 2 * wallAngle - direction; /* This is because direction should equal wallAngle - (180 - WallAngle - (180 - direction))
                                                  due to trig which simplifies to 2 * wallAngle - direction */
        xVect = Math.cos(direction) * speed;
        yVect = Math.sin(direction) * speed;
        move();
    }
    
     /**
     * Returns the radius of the ball.
     * @return radius of ball
     */
    public int getRadius() {
        return radius;
    }
    
     /**
     * Returns the speed of the ball.
     * @return speed of ball
     */
     
    public int getSpeed() {
        return speed;
    }
    
     /**
     * Returns the direction of the ball.
     * @return direction of ball
     */
     
    public double getDirection() {
        return direction;
    }
    
    /**
     * Returns the x-coordinate of the ball.
     * @return x-coordinate of ball
     */
     
    public double getXCoordinate() {
        return xCoord;
    }
    
    /**
     * Returns the y-coordinate of the ball.
     * @return y-coordinate of ball
     */
    
    public double getYCoordinate() {
        return yCoord;
    }
}
