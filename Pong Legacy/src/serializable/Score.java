package serializable;

import java.io.Serializable;
import java.util.Arrays;

public class Score implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9117177017232278926L;
	private int[] strikes;
	
	public Score(int numPlayers){
		strikes = new int[numPlayers];
		Arrays.fill(strikes, 0);
	}
	
	public int getNumPlayers(){
		return strikes.length;
	}
	
	public void addStrike(int player){
		strikes[player] += 1;
	}
	
	public void printScore(){
		System.out.println("Scores");
		for(int i = 0; i < getNumPlayers(); i++){
			System.out.println("player " + i + " : " + strikes[i]);
		}
		System.out.println();
	
	}
	
	public String stringScore() {
		String score = "";
		for(int i = 0; i < getNumPlayers(); i++){
			score += "player " + i + " : " + strikes[i] + "/n";
		}
		return score;
	}
}
