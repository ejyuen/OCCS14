/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import alpha.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Test alpha.Statistics
 *
 * @author tim_costa
 */
public class StatisticsTest {
    
    private Statistics stat;

    @Before
    public void setUp() {
        stat = new Statistics("Tim");
    }

    @After
    public void tearDown() {
        stat = null;
    }

    @Test
    public void testHitCount() {
        assertTrue(stat.getHits() == 0);
    }
    @Test
    public void testMissCount() {
        assertTrue(stat.getMisses() == 0);
    }
    @Test
    public void testChanceCount() {
        assertTrue(stat.getChances() == 0);
    }
    @Test
    public void testHit() {
        stat.hit();
        assertTrue(stat.getChances() == 1 && stat.getHits() == 1);
    }
    @Test
    public void testMiss() {
        stat.miss();
        assertTrue(stat.getChances() == 1 && stat.getMisses() == 1);
    }
    @Test
    public void testGoalScored() {
        stat.scores();
        assertTrue(stat.getGoals() == 1);
    }
    @Test
    public void testName() {
        assertTrue(stat.getName().equals("Tim"));
    }
    @Test
    public void testAccuracy() {
        stat.hit();
        stat.hit();
        stat.hit();
        stat.miss();
        assertTrue(stat.getAccuracy() == 75);
    }
    @Test
    public void testOwnGoal()
    {
    	stat.ownGoal();
    	stat.ownGoal();
    	stat.hit();
    	stat.ownGoal();
    	stat.ownGoal();
    	stat.ownGoal();
    	stat.ownGoal();
    	stat.ownGoal();
    	stat.ownGoal();
    	stat.ownGoal();
    	assertTrue(stat.getAccuracy() == 10 && stat.getScore() == -5);
    }
    @Test
    public void testUpdateScore()
    {
    	stat.ownGoal();
    	stat.ownGoal();
    	stat.scores();
      	stat.scores();
      	stat.scores();
      	stat.scores();
      	stat.scores();
    	stat.ownGoal();
    	stat.ownGoal();
    	assertTrue(stat.getScore() == 1);
    }
    
}
