package serializable;
import serializable.Score;

import com.google.gdata.data.extensions.*;
import com.google.gdata.data.spreadsheet.*;
import com.google.gdata.client.spreadsheet.*;



public class Leaderboard extends Score{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7683903530289737107L;

	public static void main(String[] args) {

		SpreadsheetService leaderboard = new SpreadsheetService("Leaderboard");
		SpreadsheetQuery query = new SpreadsheetQuery(null);
//		SpreadsheetFeed feed = leaderboard.Query(query);	
	}
	
	public Leaderboard(int numPlayers) {
		super(numPlayers);
		// TODO Auto-generated constructor stub
	}
}