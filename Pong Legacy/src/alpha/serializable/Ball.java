/*
 * Ball.java
 *
 * This is clearly not quite so wrong.
 *
 */

package alpha.serializable;

import java.awt.geom.Point2D;
import java.io.Serializable;

import alpha.communicator.Communicator;
import alpha.communicator.Server;

/**
 * DESCRIPTION
 * 
 * @author 2009-2010 WHS <a
 *         href="http://winchester.k12.ma.us/~dpetty/apcs/">APCS</a> class
 */
public class Ball implements Serializable{

    /*
     * The location of the ball.
     */
    private Point2D location;

    /**
     * The radius of the ball.
     */
    private int radius;
    
    /**
     *The side the ball last hit
     */
    private Side side;

    /**
     * Direction of the Ball in radians with 0 being to the east.
     */
    private double direction;

    /**
     * Speed of the Ball in absolute units / time tick.
     */
    private int speed;
    
    /**
     * Server for the ball to send info across
     */
    
    private Server server = null;
    
    /**
     * Default ball radius.
     */
    public static final int DEFAULT_RADIUS = 20;

    
    /*
     * Default ball location.
     */
    public static final Point2D DEFAULT_LOCATION = new Point2D.Double(0, 0);

    /**
     * Default ball speed.
     */
    public static final int DEFAULT_SPEED = 80;

    private boolean moving = false;
    
    private Side lastHit;

    /**
     * Creates a Ball with default location, radius, and speed, but with
     *.randomly determined direction.
     */
    public Ball() {
        this(DEFAULT_LOCATION, DEFAULT_RADIUS, Math.random() * (2 * Math.PI), DEFAULT_SPEED, null);
    }
    
    public Ball(Communicator comm){
    	this(DEFAULT_LOCATION, DEFAULT_RADIUS, Math.random() * (2 * Math.PI), DEFAULT_SPEED, comm);
    }

    /**
     * Creates a ball with the specified speed and radius, but with
     * default location and randomly determined direction.
     * 
     * @param speed speed of the ball
     * @param radius radius of the ball
     */
    public Ball(int speed, int radius) {
        this(DEFAULT_LOCATION, radius, Math.random() * (2 * Math.PI), speed, null);
    }
    
    public Ball(Ball b){
    	this(b.location, b.radius, b.direction, b.speed, null);
    }

    /**
     * Creates a ball with the specified speed and radius, but with
     * default location and randomly determined direction.
     * 
     * @param location ball location
     * @param radius ball radius
     * @param direction ball direction
     * @param speed ball speed
     * @param occupied side last hit
     */
    public Ball(Point2D location, int radius, double direction, int speed, Communicator comm) {
        if(comm instanceof Server){
        	server = (Server) comm;
        }
    	setLocation(location);
        setRadius(radius);
        changeDirection(direction);
        changeSpeed(speed);
        lastHit = null;
    }

    public void stop() {
        moving = false;
    }

    public void start() {
        moving = true;
    }

    public boolean getState() {
        return moving;
    }

    /**
     * Returns ball location.
     * 
     * @return ball location
     */
    public Point2D getLocation() {
        return location;
    }

    /**
     * Sets ball location.
     * 
     * @param location new ball location
     */
    public void setLocation(Point2D location) {
        this.location = location;
        if(server != null){
        	server.sendObject(location);
        }
    }
    /**
     * Returns location that the Ball will move to next.
     * 
     * @return next location
     */
    public Point2D getNextLocation() {
        double newX = Math.cos(direction) * speed + location.getX();
        double newY = -1.0 * Math.sin(direction) * speed + location.getY();
        return new Point2D.Double(newX, newY);
    }

    /*
     * Moves the ball.
     */
    public void move() {
        setLocation(getNextLocation());
    }

    /**
     * Returns ball radius.
     * 
     * @return ball radius
     */
    public int getRadius() {
        return radius;
    }

    /**
     * Sets ball radius.
     * 
     * @param radius new ball radius
     */
    public void setRadius(int radius) {
        this.radius = radius;
    }

    /**
     * Returns ball speed.
     * 
     * @return ball speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Changes ball speed.
     * RED_FLAG: should be named setSpeed
     * 
     * @param speed new ball speed
     */
    public void changeSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Returns ball direction.
     * 
     * @return ball direction
     */
    public double getDirection() {
        return direction;
    }

    /**
     * Changes ball direction.
     * RED_FLAG: should be named setDirection
     * 
     * @param direction new ball direction in radians
     */
    public void changeDirection(double direction) {
        this.direction = direction;
    }

    /**
     * Changes side last hit to wall, if wall is-a Player.
     *
     * @param side side last hit
     */
    public void setLastHit(Side side) {
        if(side instanceof Player) {
           lastHit = side;
        }
    }

    /**
     * Gets player who last hit ball, or null if no player has yet hit ball.
     *
     * @returns player who last hit ball
     */
    public Player getLastHit() {
        return (Player)lastHit;
    }
    
    public String toString(){
    	return getLocation().toString() + " " + getRadius() + " " + getDirection();
    }
}