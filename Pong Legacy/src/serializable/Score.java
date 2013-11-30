package serializable;

import java.io.Serializable;
import java.util.Arrays;

public class Score implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9117177017232278926L;
	private int[] lives;
	
	public Score(int numPlayers){
		lives = new int[numPlayers];
		Arrays.fill(lives, 50);
	}
	
	public int getNumPlayers(){
		return lives.length;
	}
	
	
	public void loseLife(int player){
		lives[player] -= 1;
	}
	
	public int getLives(int player){
		return lives[player];
	}
	
	public int[] getLives()
	{
		return lives;
	}
	public void printScore(){
		System.out.println("Scores");
		for(int i = 0; i < getNumPlayers(); i++){
			System.out.println("player " + (i +1) + " : " + lives[i]);
		}
		System.out.println();
	}
	
	public void changeScore(int player, int newScore){
		lives[player] = newScore;
	}
	
	public boolean isAlive(int player)
	{
		return (lives[player] > 0);
	}
	public String stringScore() {
		String score = "";
		for(int i = 1; i <= getNumPlayers(); i++){
			if(i < getNumPlayers()) score += "Player " + (i) + " : " + lives[i-1] + " | ";
			else score += "Player " + i + " : " + lives[i-1];
		}
		return score;
	}
}