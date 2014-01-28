package host.player;

import host.Wallet;

/**
 * Player is the superclass for both Company and Investor.
 * 
 * A company is controlled it's board of directors and CEO
 * 
 * A investor is controlled by an individual student
 * 
 * The player class contains the equals method and toString, both work by using name
 * 
 * Note that all names must be unique
 * 
 * @author Matthew Li
 *
 */
public class Player {
	private String name;
	private Wallet wallet; //holds player money
	
	public Player(String name){
		this.name = name;
	}
	
	public boolean equals(Investor investor){
		return investor.toString().equals(name);
	}
	
	/**
	 * transfer amount of this player's money to player p
	 */
	public void transfer(double amount, Player p){
		wallet.withdraw(amount);
		p.wallet.deposit(amount);
	}
	
	public String toString(){
		return name;
	}
}
