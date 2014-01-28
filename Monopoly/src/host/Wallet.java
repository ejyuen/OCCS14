package host;

import java.text.DecimalFormat;

/**
 * Wallet manages money for all players
 * Money is a double
 * 
 * @author Matthew Li
 *
 */
public class Wallet {
	private double money;
	private static DecimalFormat d = new DecimalFormat("'$'0.00");
	
	public Wallet(double initialDeposit){
		money = initialDeposit;
	}
	
	public void deposit(double amount){
		money += amount;
	}
	
	public void withdraw(double amount){
		money-=amount;
		if(money < 0){
			System.out.println("you are in bankrupcy, you have one turn to deal with bankrupcy until you forfit"); //bankrupcy
		}
	}
	
	public double amountOfMoney(){
		return money;
	}
	
	public String toString(){
		return d.format(money);
	}
}
