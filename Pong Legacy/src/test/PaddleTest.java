package test;

import alpha.*;
import alpha.serializable.Paddle;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Test alpha.Paddle
 *
 * @author 2009-2010 WHS 
 * <a href="http://winchester.k12.ma.us/~dpetty/apcs/">APCS</a> class
 */
public class PaddleTest {
    
    private Paddle paddle;
    private Paddle side;

    @Before
    public void setUp() {
        paddle = new Paddle();
	side = new Paddle(100.0);
    }

    @After
    public void tearDown() {
        paddle = null;
	side = null;
    }

    @Test
    public void testWidth() {
        assertTrue(paddle.getWidth() == 20.0);
	assertTrue(side.getWidth() == 100.0);
    }
    
    @Test
    public void testCenter() {
        assertTrue(paddle.getCenter() == 50.0);
	assertTrue(side.getCenter() == 50.0);
    }
    
    @Test
    public void testMoveR() {
	paddle.moveRight(5);
	assertTrue(paddle.getCenter() == 55.0);
    }
    
    @Test
    public void testMoveL() {
	paddle.moveLeft(5);
	assertTrue(paddle.getCenter() == 45.0);
    }
    
    @Test
    public void testNoMove() {
	paddle.moveRight(0);
	paddle.moveLeft(0);
	assertTrue(paddle.getCenter() == 50.0);
    }
    
    @Test
    public void testMoveOverRightBoundary() {
	paddle.moveRight(100);
	assertTrue(paddle.getCenter() == 100 - (paddle.getWidth() / 2));
    }
    
    @Test
    public void testMoveOverLeftBoundary() {
	paddle.moveLeft(100);
	assertTrue(paddle.getCenter() == paddle.getWidth() / 2);
    }
    
    @Test
    public void testMoveRightONE() {
	paddle.moveRight();
	assertTrue(paddle.getCenter() == 51.0);
    }
        
    @Test
    public void testMoveRightTWO() {
    for(int i = 0; i<=99; i++)
    {
    	paddle.moveRight();
    }
    assertTrue(paddle.getCenter() == 100.0 - (paddle.getWidth() / 2));
    }
    
    
    @Test
    public void testMoveLeftONE() {
	paddle.moveLeft();
	assertTrue(paddle.getCenter() == 49.0);
    }
    
    @Test
    public void testMoveLeftTWO() {
    for(int i = 0; i<=99; i++)
    {
    	paddle.moveLeft();
    }
    assertTrue(paddle.getCenter() == paddle.getWidth() / 2);
    }
 
}
