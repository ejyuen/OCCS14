package host.stocks;

import host.player.Company;
import host.player.Player;

import java.util.ArrayList;

public class StockMarket {
	private static ArrayList<Stock> stocks; //should be a list of all stocks
	
	
	public static void addStock(Player owner, Company company){
		stocks.add(new Stock(owner, company));
	}
	
	public static void transferStock(Company c, Player oldOwner, Player newOwner){
		transferStock(c, oldOwner, newOwner, 1);
	}
	
	public synchronized static void transferStock(Company c, Player oldOwner, Player newOwner, int amountOfStocks){
		ArrayList<Stock> playerStocks = getPlayerStocksInCompany(oldOwner, c);
		if(playerStocks.size()<amountOfStocks){
			System.out.println(oldOwner + " does not own enough stocks to transfer"); //make this a message too
		} else {
			for(int i = 0; i < amountOfStocks; i++){
				playerStocks.get(i).changeOwner(newOwner);
			}
		}
	}
	
	public static ArrayList<Stock> getPlayerStocksInCompany(Player player, Company company){
		ArrayList<Stock> ret = new ArrayList<Stock>();
		for(Stock s: stocks){
			if(s.getOwner().equals(player) && s.getCompany().equals(company)){
				ret.add(s);
			}
		}
		return ret;
	}
	
	public static ArrayList<Stock> getPlayerStocks(Player player){
		ArrayList<Stock> ret = new ArrayList<Stock>();
		for(Stock s: stocks){
			if(s.getOwner().equals(player)){
				ret.add(s);
			}
		}
		return ret;
	}
	
	public static ArrayList<Stock> getCompanyStocks(Company company){
		ArrayList<Stock> ret = new ArrayList<Stock>();
		for(Stock s: stocks){
			if(s.getCompany().equals(company)){
				ret.add(s);
			}
		}
		return ret;
	}
	
	public static ArrayList<Stock> getAllStocks(){
		return stocks;
	}
}
