package host.bonds;

import host.player.Player;

public class Bond {
	private Player lender;
	private Player borrower;
	private double amount;
	private double interestRate;
	private int totalTurns; //The number of turns you have to pay off the bond
	private int turns; //The number of turns you have payed off the bond
	
	public Bond(Player lender, Player borrower, double amount, double interestRate, int matureTurn){
		lender.transfer(amount, borrower);
		this.lender = lender;
		this.borrower = borrower;
		this.amount = amount;
		this.interestRate = interestRate;
		totalTurns = matureTurn;
	}
	
	/**
	 * 
	 * @return False if bond still has not been paid, true if bond has been paid
	 */
	public boolean check(){
		if(turns == totalTurns){
			double payment = amount * interestRate;
			borrower.transfer(payment, lender);
			return true;
		}
		turns++;
		return false;
	}
}
