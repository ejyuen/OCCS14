package main;

import menus.MainMenue;
import menus.Menue;

/**
 * The game works just like monopoly, except players buy and trade stocks for ownership over the pieces
 * 
 * @authors Matthew Li, Amelia Paine, Erica Yuen
 *
 */
public class MonopolyInc {
	public static void main(String[] args){
		Menue.open(new MainMenue());
	}
}
