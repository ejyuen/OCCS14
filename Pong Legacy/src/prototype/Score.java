/*
 * Score.java
 *
 */

package prototype;

/**
 * Keeps track of player score, which has a minimum value of MIN_SCORE.
 *
 * @author 2009-2010 WHS 
 * <a href="http://winchester.k12.ma.us/~dpetty/apcs/">APCS</a> class
 */
public class Score {
	/**
	 * Score of a player.
	 */
	private int score;

	/**
	 * Minimum score. Can be < 0.
	 */
	private static final int MIN_SCORE = -5;
	
	/**
	 * Construct score and initialize it to the maximum of score and MIN_SCORE.
	 * @param score initial value of score
	 */
	public Score(int score) {
		setScore(score);
	}

	/**
	 *  Construct score and initialize it to 0.
	 */
	public Score() {
		this(0);
	}

	/**
	 * Return current score.
	 * @return current score
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Set this score to the maximum of score and MIN_SCORE.
	 * @param score new value of score
	 */
	public void setScore(int score) {
		this.score = Math.max(score, MIN_SCORE);
	}

	/**
	 * Decrement score, as long at is is > MIN_SCORE.
	 */
	public void decreaseScore() {
		setScore(getScore() - 1);
	}

	/**
	 * Increment score.
	 */
	public void increaseScore() {
		setScore(getScore() + 1);
	}
}