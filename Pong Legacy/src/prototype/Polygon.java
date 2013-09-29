
package prototype;

import java.awt.geom.Line2D;


/**
 * Creates and maintains the polygon that is the board.
 * @author 2009-2010 WHS
 * <a href="http://winchester.k12.ma.us/~dpetty/apcs/">APCS</a> class
 */
public class Polygon {

    private int numSides;
    private int xCent, yCent, radius;
    private int ballRadius;
    private int[] xArray, yArray;
    private Line2D.Double[] polyLines;
    private java.awt.Polygon polygon, container;

    /**
     * Constructs a regular polygon with the given radius, center, and number of sides to display on the screen.
     * Also constructs a hidden, smaller polygon used for collision detection.
     * @param numSides Number of sides in the polygon
     * @param xCent X coordinate for center point
     * @param yCent Y coordinate for center point
     * @param radius Radius of circle surrounding polygon
     */
    public Polygon(int numSides, int xCent, int yCent, int radius, int ballRadius) {
        this.numSides = numSides;
        this.xCent = xCent;
        this.yCent = yCent;
        this.radius = radius;
        this.ballRadius = ballRadius;

        xArray = calculateX(radius);
        yArray = calculateY(radius);
        polyLines = getLines();
        polygon = new java.awt.Polygon(xArray, yArray, numSides);
        container = getContainer();
    }

    /**
     * Creates the sides of the polygon.
     * @return polyLines An array of the polygon's sides
     */
    private Line2D.Double[] getLines() {
        polyLines = new Line2D.Double[numSides];
        for (int i = 0; i < polyLines.length - 1; i++) {
            polyLines[i] = new Line2D.Double(xArray[i], yArray[i], xArray[i + 1], yArray[i + 1]);
        }
        polyLines[numSides - 1] = new Line2D.Double(xArray[numSides - 1], yArray[numSides - 1], xArray[0], yArray[0]);
        return polyLines;
    }

    /**
     * Returns the container polygon.
     * @return the container polygon
     */
    private java.awt.Polygon getContainer() {
        return new java.awt.Polygon(calculateX(radius - ballRadius), calculateY(radius - ballRadius), numSides);
    }

    /**
     * Calculates the x coordinates of each point.
     * @return xPoints An array of the x coordinates of the points of the polygon
     */
    private int[] calculateX(int radius) {
        int[] xPoints = new int[numSides];
        double theta = (2 * Math.PI) / numSides;
        double angle = theta / 2;
        for (int i = 0; i < numSides; i++) {
            xPoints[i] = (int) ((Math.cos(angle + (i * theta)) * radius) + xCent);
        }
        return xPoints;
    }

    /**
     * Calculates the y coordinates of each point.
     * @return yPoints An array of the y coordinates of the points of the polygon
     */
    private int[] calculateY(int radius) {
        int[] yPoints = new int[numSides];
        double theta = (2 * Math.PI) / numSides;
        double angle = theta / 2;
        for (int i = 0; i < numSides; i++) {
            yPoints[i] = (int) (yCent - (Math.sin(angle + (i * theta)) * radius));
        }
        return yPoints;
    }

    /**
     * Returns the side the ball is colliding with or null if there is no collision.
     * @param xBall The x coordinate of the ball
     * @param yBall The y coordinate of the ball
     * @param ballRadius The radius of the ball
     * @return line The side the ball is colliding with
     */
    public Line2D.Double checkCollision(double xBall, double yBall, int ballRadius) {
        if (container.contains(xBall, yBall)) return null;

        // This can probably be optimized so it doesn't test every wall.
        Line2D.Double line = null;
        for (int i = 0; i < polyLines.length; i++) {
            Line2D.Double wall = polyLines[i];
            double dist = wall.ptSegDist(xBall, yBall);
            if (dist <= ballRadius) {
                line = polyLines[i];
                break;
            }
        }
        return line;
    }

    /**
     * Returns the polygon to be printed.
     * @return The polygon
     */
    public java.awt.Polygon getPolygon() {
        return polygon;
    }
}
