package prototype;
import prototype.*;

/**
 * @author 2009-2010 WHS 
 * <a href="http://winchester.k12.ma.us/~dpetty/apcs/">APCS</a> class
 */
public class Player {

	/**
	 *Keeps track of the number of hits.
	 */
    private int hits;
    /**
     *Keeps track of the number of misses.
     */
    private int misses;
    /**
     *Keeps track of the number of attempts (chances) taken.
     */
    private int chances;
    /**
     *Keeps track of the number of goals.
     */
    private int goals;
    /**
     *Keeps the player's name.
     */
    private String playerName;
    /**
     *The player's paddle.
     */
    private Paddle playerPaddle;

    public Player(String name) {
         playerName = name;
    }

    public Player(String name, Paddle paddle) {
         playerName = name;
         playerPaddle = paddle;
    }
    
    /**
     * Updates the number of hits and chances for the player who hits the ball.
     */
    public void hitsBall() {
    	hits++;
    	chances++;
    }

    /**
     * Updates the number of misses and chances for the player who misses the ball.
     */
    public void missesBall() {
    	misses++;
    	chances++;
    }

    /**
     * Updates the number of goals scored when a player scores a goal.
     */
    public void scoresGoal() {
    	goals++;
    }

    /**
     * Gets the number of times a player has hit the ball.
     * @return Number of hits
     */
    public int getHits() {
    	return hits;
    }

    /**
     * Gets the number of misses for the player.
     * @return Number of misses
     */
    public int getMisses() {
    	return misses;
    }

    /**
     * Gets the number of goals a player has scored.
     * @return Number of goals
     */
    public int getGoals() {
    	return goals;
    }

    /**
     * Gets the name of the player.
     * @return Name of player
     */
    public String getName() {
    	return playerName;
    }

    /**
     * Gets the paddle the player is bound to.
     * @return Bound paddle
     */
    public Paddle getPaddle() {
    	return playerPaddle;
    }
    
    
    /**
     *Gets the chances.
     *@return The chances
     */
    public int getChances() {
    	return chances;
    }
    
    

    /**
     * Sets the bound paddle for a player.
     * @param p Paddle to be bound to the player
     */
    public void setPaddle(Paddle p) {
    	playerPaddle = p;
    }
}
