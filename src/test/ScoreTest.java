/*
 * ScoreTest.java
 */

package test;

import prototype.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.lang.reflect.*;

/**
 * Test prototype.Score.
 *
 * @author 2009-2010 WHS 
 * <a href="http://winchester.k12.ma.us/~dpetty/apcs/">APCS</a> class
 */

public class ScoreTest {

	private Score score;

	@Before
	public void setUp() {
		score = new Score();
	}

	@After
	public void tearDown() {
		score = null;
	}

	@Test
	public void testGetScore() {
		assertTrue(score.getScore() == 0);
	}

	@Test
	public void testIncreaseScore() {
		score.increaseScore();
		assertTrue(score.getScore() == 1);
	}

	@Test
	public void testDecreaseScore() {
		score.decreaseScore();
		assertTrue(score.getScore() == -1);
		score.decreaseScore();
		assertTrue(score.getScore() == -2);
		score.decreaseScore();
		assertTrue(score.getScore() == -3);
		score.decreaseScore();
		assertTrue(score.getScore() == -4);
		score.decreaseScore();
		assertTrue(score.getScore() == -5);
		score.decreaseScore();
		assertTrue(score.getScore() == -5);
	}

	@Test
	public void testAll() throws NoSuchMethodException, 
		IllegalAccessException, InvocationTargetException {

		final Method increase = Score.class.getMethod("increaseScore");
		final Method decrease = Score.class.getMethod("decreaseScore");

		final Method[] methods = {
			increase, increase, increase, 
			decrease, decrease, decrease, 
			decrease, decrease, decrease, decrease, decrease, decrease, decrease, 
			increase, increase, increase, increase, increase, 
		};
		final int[] values = {
			1, 2, 3, 
			2, 1, 0, 
			-1, -2, -3, -4, -5, -5, -5, 
			-4, -3, -2, -1, 0, 
		};

		for (int i = 0; i < methods.length; i++) {
			methods[i].invoke(score);
			assertTrue(score.getScore() == values[i]);
		}
	}
}