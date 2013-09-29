/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package prototype;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
/**
 *
 * @author kota
 */
public class Paddle {
    private Shape rectangle;
    private Dimension outerRim;
    private Pong board;
    private int xCoor, yCoor;
    /**
     * Creates a new Paddle.
     * @param width Width of paddle
     * @param height Height of paddle
     * @param xLocation X coordinate of paddle
     * @param yLocation Y coordinate of paddle
     * @param outerRim_x0 left boundary to paddle
     * @param outerRim_x1 right boundary to paddle
     * @param board board to place paddle on
     */
    public Paddle(int width, int height, int xLocation, int yLocation, int outerRim_x0, int outerRim_x1, Pong board) {
        rectangle = new Rectangle(new Point(xLocation, yLocation), new Dimension(width, height));
        outerRim = new Dimension(outerRim_x1 - outerRim_x0, yLocation + height);
        xCoor = xLocation;
        yCoor = yLocation;
        this.board = board;
    }
    
    public Paddle() {
    }

    /**
     * Moves the paddle
     * @param left true if moving left
     * @param speed speed of movement
     */

    public void move(boolean left, int speed) {
        board.setLoc(xCoor + (left? speed: -speed), yCoor);
        System.out.print("key");
    }

    /**
     * Returns the shape of the Paddle.
     * @return Shape of paddle
     */
    public Shape getShape() {
        return rectangle;
    }
}
