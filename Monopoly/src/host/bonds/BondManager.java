package host.bonds;

import host.player.Player;

import java.util.ArrayList;

public class BondManager {
	private static ArrayList<Bond> bonds; //should be a list of all bonds
	
	public static ArrayList<Bond> getLenderBonds(Player player){
		ArrayList<Bond> ret = new ArrayList<Bond>();
		for(Bond b: bonds){
			if(b.getLender().equals(player)){
				ret.add(b);
			}
		}
		return ret;
	}
}
