/*
 * Statistics.java
 */
package alpha;

import java.util.ArrayList;

/**
 * This class will effectively keep track of the statistics during a game of
 * Pong Legacy, and can provide a detailed game summary when prompted.
 *
 * @author 2009-2010 WHS
 * <a href="http://winchester.k12.ma.us/~dpetty/apcs/">APCS</a> class
 */
//V.1 by Tim Costa
public class Statistics {

    /**
     * The ArrayList that will store the Statistics for each player.
     */
    private static ArrayList<Statistics> statsArray = new ArrayList<Statistics>();
    /**
     * The number of hits for the player represented by the Statistic.
     */
    private int hits;
    /**
     * The number of misses for the player represented by the Statistic.
     */
    private int misses;
    /**
     * The number of goals the player represented by the Statistic has scored on other players.
     */
    private int goalsScored;
    /**
     * The name of the player represented by the Statistic as input upon game entry.
     */
    private String name;
    /**
     * The percent of time the player successfully hits the ball.
     */
    private int accuracy;
    private static final int MIN_SCORE = -5;
    private int ownGoals;
    private int score;

    /**
     * This is the constructor that crates a new Statistics object that can be used to track a players performance in the game.
     * @param playerInput the name the player has input to the console
     */
    public Statistics(String playerInput) {
        name = playerInput;
        hits = 0;
        misses = 0;
        goalsScored = 0;
        accuracy = 0;
        ownGoals = 0;
        score = 0;
        statsArray.add(this);
    }

    /**
     * This method will output the summary of the game to a console, first in 
     * sentence form, and then in list form. Need to add support for the "score"
     * and the number of own goals.
     */
    public void statisticsSummary() {
        for (int i = 0; i < statsArray.size(); i++) {
            System.out.println(statsArray.get(i).getName()
                    + " hit the ball " + statsArray.get(i).getHits()
                    + " times and missed it " + statsArray.get(i).getMisses()
                    + " out of " + statsArray.get(i).getChances()
                    + " chances. They scored " + statsArray.get(i).getGoals()
                    + "goals. They had an overall score of " + statsArray.get(i).getScore() + ".");
        }

        System.out.println("\n\n");
        for (int i = 0; i < statsArray.size(); i++) {
            System.out.println();
            System.out.println(statsArray.get(i).getName() + "\n\t Score: " + statsArray.get(i).getScore() + "\n\t Hits: "
                    + statsArray.get(i).getHits() + "\n\t Misses: " + statsArray.get(i).getMisses()
                    + "\n\t Chances: " + statsArray.get(i).getChances() + "\n\t Goals: "
                    + statsArray.get(i).getGoals());
        }
    }

    public static Statistics getStatistics(String name) {
        for (int i = 0; i < statsArray.size(); i++) {
            if (statsArray.get(i).getName().equalsIgnoreCase(name)) {
                return statsArray.get(i);
            }
        }
        return null;
    }

    public static ArrayList<Statistics> getStatsArray() {
        return statsArray;
    }

    private void updateAccuracy() {
        accuracy = (int) (100.00 * getHits() / getChances() + .5);
    }

    public void scores() {
        goalsScored++;
    }

    /**
     * This method will return the number of times this player has scored on another player.
     * @return number of goals
     */
    public int getGoals() {
        return goalsScored;
    }

    /**
     * This method will return the name of the player that the set of statistics corresponds to.
     * @return name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * This method will return the number of times a player has successfully hit the ball.
     * @return number of hits
     */
    public int getHits() {
        return hits;
    }

    /**
     * This method will return the number of times a player has missed the ball. It effectively tracks goals scored on this player also.
     * @return number of misses
     */
    public int getMisses() {
        return misses;
    }

    /**
     * This method will return the number of times a player had the chance to either hit or miss the ball.
     * @return number of chances
     */
    public int getChances() {
        return hits + misses;
    }

    /**
     * This method will return the accuracy of the player.
     * @return accuracy
     */
    public int getAccuracy() {
        return accuracy;
    }

    /**
     * This method is called when a player hits the ball. It updates their number of hits and chances.
     */
    public void hit() {
        hits++;
        updateAccuracy();
    }

    /**
     * This method gets the score of the associated player.
     * @return score of player
     */
    public int getScore() {
        return score;
    }

    public void ownGoal() {
        ownGoals++;
        misses++;
        updateAccuracy();
        updateScore();
    }

    public void updateScore() {
        if (goalsScored - ownGoals <= MIN_SCORE) {
            score = MIN_SCORE;
        } else {
            score = goalsScored - ownGoals;
        }
    }

    /**
     * This method is called when a player misses the ball. It updates their number of misses and chances.
     */
    public void miss() {
        misses++;
        updateAccuracy();
    }

    /**
     * Returns the name of the player associated with a set of Statistics.
     * Need to add support for the "score" and the number of own goals.
     * @return formatted string
     */
    @Override
    public String toString() {
        String fin = this.getName() + " scored " + this.getGoals()
                + " goals, hit the ball " + this.getHits() + " times, missed the ball "
                + this.getMisses() + " times, and had a hit percentage of " + this.getAccuracy() + "";
        if (statsArray.indexOf(this) == 0) {
            return fin;
        } else {
            return "\n" + fin;
        }
    }

    /**
     * used for testing methods during development.
     * @param args
     */
    public static void main(String[] args) {
        Statistics stat = new Statistics("Tim");
        Statistics stat2 = new Statistics("Bill");

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
    	System.out.println(stat.getAccuracy());
    }
}
