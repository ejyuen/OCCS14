/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package prototype;

import java.util.ArrayList;

/**
 *
 * @author tim_costa
 *
 *
 * NOT SURE HOW THIS WILL BE IMPLEMENTED YET
 * just writing down ideas
 */
public class GameLog {

    private String goalRegex = "^(.+)\\s" + "scored a goal on " + "(.+)\\!";
    private String hitRegex = "^(.+)\\s" + "hit the ball!";

    private ArrayList<Player> players = new ArrayList<Player>();

    /**
     * @param playerName The name of the player whose paddle made contact with the ball.
     */
    public void ballHit(String playerName) {
        //print to a gui panel that will disply the log
        //logPanel.print(playerName + " hit the ball!");
    }

    /**
     * @param playerName The name of the player who missed the ball and therefore had a goal scored on them.
     */
    public void ballMissed(String playerName) {
        //print to a gui panel that will disply the log
        //logPanel.print(playerName + " missed the ball!");
    }
    /**
     *
     * @param name name of the player being tested
     * @return boolean true, adds player to list of players if not already present.
     */
    public boolean checkPlayers(String name) {
        for (int i = 0; i<players.size(); i++) {
            if (players.get(i).getName().equals(name)) {return true;}
            //else {players.add(new Players(name, new )); return true;}
        }
        return false;
    }

    /**
     * @param playerName The name of the player who scored the goal.
     */
    public void goalScored(String playerName) {
        //print to a gui panel that will disply the log
        //logPanel.print(playerName + " scored a goal!");
    }

}
