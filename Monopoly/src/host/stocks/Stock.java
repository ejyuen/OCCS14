package host.stocks;

import host.player.Company;
import host.player.Player;

public class Stock {
	private Player owner;
	private Company company;
	
	public Stock(Player owner, Company company){
		this.company = company;
		this.owner = owner;
	}
	
	public Company getCompany(){
		return company;
	}
	
	public Player getOwner(){
		return owner;
	}
	
	public void changeOwner(Player newOwner){
		owner = newOwner;
	}
	
}
