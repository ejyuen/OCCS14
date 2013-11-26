package graphics;
/*
 * Graphics.java
 */



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import javax.swing.*;

import pong.Pong;
import serializable.Ball;
import serializable.Polygon;
import serializable.Side;
import communicator.Communicator;


/**
 * Graphics is in charge of all the game window drawing, as well as relative positioning.
 *
 * @author 2009-2010 WHS
 * <a href="http://winchester.k12.ma.us/~dpetty/apcs/">APCS</a> class
 */
public class Graphics extends JPanel implements KeyListener, ActionListener {
	private static final long serialVersionUID = 1L;
	private Pong pong;
    private double degrees;
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private int side = -1;
    private Communicator comm = null;

    /**
     * Constructs a new Graphics with the Pong that creates it.
     * @param pong number of sides in Polygon
     */
    public Graphics(Pong pong) {
        this(pong, 0);
    }
    
    public Graphics(Pong pong, int side) {
        super();
        this.pong = pong;
        this.side = side;
        comm = pong.getCommunicator();
        // RED_FlAG: constructor needs title / player name and player number?
        init("PongLegacy");
    }

    /**
     * Initiates a Graphics object and sets up a JFrame as a container.
     * @param title window title
     */
    private void init(String title) {
        JFrame frame = new JFrame(title);
        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(480,480));
        frame.setSize(new Dimension(640, 640));
        frame.setVisible(true);
        frame.setFocusable(true);
        this.setFocusable(true);
        this.setBackground(Color.white);
        Timer timer = new Timer(36, new TimeAction());
        timer.start();
        frame.addKeyListener(this);
    }

    /**
     * Transforms and draws a Polygon object to the screen.
     * @param g the Graphics context in which to paint
     */
    private void paintPolygon(java.awt.Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Polygon poly = pong.getPolygon();
        //java.awt.Polygon test = pong.getPolygon().container; // To test the container. It looks right.
        g2.transform(transformPolygon(poly));
        AffineTransform old = g2.getTransform();
        g2.rotate(rotatePolygon(poly, pong.getPolygon().getSides().length));
        
        g2.drawPolygon(poly);
//        g2.setTransform(old);
        //g2.drawPolygon(test);
        
    }
    
    private void paintScoreField(java.awt.Graphics g) {
    	Graphics2D g2 = (Graphics2D) g;
    	g2.setFont(new Font("Serif", Font.BOLD, 36));
    	FontMetrics fm = g2.getFontMetrics();
    	Rectangle2D r = fm.getStringBounds(pong.getScore().stringScore(), g2);
    	int scoreRectX = (int) (-.5*r.getWidth()) - 40;
    	int scoreRectY = (int) (-.5*r.getHeight()) - 50;
    	g2.drawRect(scoreRectX, scoreRectY+200, -2*scoreRectX, -2*scoreRectY);
    	int x = 0 - (int) (r.getWidth()/2);
    	int y = 200 - (int) (r.getHeight()/2) + (int)fm.getAscent();
    	g2.drawString(pong.getScore().stringScore(), x, y);
    	g2.setColor(Color.BLACK);
    }
    
    /**
     * Paints a transformed Ball object to the screen.
     * @param g the Graphics context in which to paint
     */
    private void paintBall(java.awt.Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Ball ball = pong.getBall();
        g2.setColor(Color.BLACK);
        g2.fillOval((int) ((ball.getLocation().getX() - ball.getRadius())),
                    (int) ((ball.getLocation().getY() - ball.getRadius())),
                    (int) (ball.getRadius() * 2),
                    (int) (ball.getRadius() * 2));
    }

    /**
     * Paints a transformed Side object to the screen.
     * @param side the Side object to paint
     * @param g the Graphics context in which to paint
     */
    private void paintSide(Side side, java.awt.Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(10));
        g2.draw(side.paddleLocation());

        g2.setFont(new Font("Arial", Font.PLAIN, 50));

        g2.drawString(side.getName(), (int) (side.getX1() + side.getX2()) / 2, (int) (side.getY1() + side.getY2()) / 2);
    }

    /**
     * Creates an AffineTransform that allows Polygon to match the window size.
     * @param poly the Polygon object to retrive statistics from
     * @return the AffineTransform to use with a Graphics2D object
     */
    private AffineTransform transformPolygon(Polygon poly) {
        AffineTransform transform = new AffineTransform();
        double scaleFactor = Math.min(super.getWidth(),super.getHeight()) / poly.getBounds().getWidth() - 0.02;
        double translationX = super.getWidth()/2;
        double translationY = super.getHeight()/2;
        
        transform.setTransform(scaleFactor, 0, 0, scaleFactor,translationX, translationY);
        transform.rotate(Math.toRadians(degrees));
        return transform;
    }
    
    /**
     * 
     * @param poly
     * @param sideNum
     * @param totalSides
     * @return angle of rotation
     */
    private double rotatePolygon(Polygon poly, int numSides) {
    	double rotationFactor = -2 * Math.PI / numSides * side;
    	return rotationFactor;
    }

    /**
     * Paints Graphics object to screen
     * @param g the Graphics context in which to paint
     */
    @Override
    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        Image bufferImage = createImage(this.getSize().width, this.getSize().height);
        Graphics2D bufferGraphics = (Graphics2D) bufferImage.getGraphics();
        bufferGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        paintPolygon(bufferGraphics);        
        paintBall(bufferGraphics); 
        paintScoreField(bufferGraphics);

        for(int i = 0; i < pong.getPolygon().npoints; i++)
            paintSide(pong.getPolygon().getSide(i), bufferGraphics);
        g.drawImage(bufferImage, 0, 0, this);
    }

    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void keyTyped(KeyEvent ke) {
        //System.out.println(ke + "\nKeyTyped");
    }

    public void keyPressed(KeyEvent ke) {
        if(ke.getKeyCode() == 39) {leftPressed = true; rightPressed = false;}
        if(ke.getKeyCode() == 37) {rightPressed = true; leftPressed = false;}
    }

    public void keyReleased(KeyEvent ke) {
        if(ke.getKeyCode() == 39) leftPressed = false;
        if(ke.getKeyCode() == 37) rightPressed = false;
    }
    
    public void sendPaddle() {
    	double[] paddleLocation = {side, pong.getPolygon().getSide(side).getPaddle().getCenter()};
    	if(comm != null){
    		comm.sendObject(paddleLocation);
    	} else {
    		System.out.println("no server or client");
    	}
    }

    class TimeAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		/**
    	 * Moves the ball and paddle(s) when called by the timer.
    	 * @param arg0 Event generated by the timer
    	 */
        public void actionPerformed(ActionEvent arg0) {
            Graphics.this.repaint();

            if(leftPressed == true){
            	pong.getPolygon().getSide(side).getPaddle().moveLeft();
            } else if(rightPressed == true){
            	pong.getPolygon().getSide(side).getPaddle().moveRight();
            } else {
            	return;
            }
        	sendPaddle();
        }
    }
}
