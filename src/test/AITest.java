package test;

import prototype.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Michael Croteau
 */
public class AITest {

	private AI ai;

	@Before
	public void setUp() {
	//	ai = new AI();
	}

	@After
	public void tearDown() {
		ai = null;
	}

	@Test
	public void testAll() {
		assertTrue(true);		// RED_FLAG: this test is vapid
	}

/*
    public static void main(String args[]) {
        Pong board = new Pong();
        Paddle paddle = new Paddle(5, 10, 25, 0, 0, 50, board);
        AI ai = new AI(paddle);
        ai.hitsBall();
        ai.hitsBall();
        ai.missesBall();
        System.out.println("Hits: " + ai.getHits() + " Expected: 2");
        System.out.println("Misses: " + ai.getMisses() + " Expected: 1");
        System.out.println("Name: " + ai.getName() + " Expected: AI");
        
    }
*/
}
