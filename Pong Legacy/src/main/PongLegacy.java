package main;
/*
 * PongLegacy.java
 *
 * NOTE TO THE AUTHOR OF THIS CLASS:
 *     When a player begins their session of pong, they should be greeted
 *     by a dialog box asking them for their name. That name should then be
 *     used to create a new Statistics object for that player via
 *     Statistics localPlayer = new Statistics(playerName);
 *     Also, not sure if this is the proper class or if ball/paddle would be
 *     better, but there needs to be a way to call the hit, miss, and goal
 *     methods of the statistics class. this would probs be server side.
 *            - Tim
 */



import java.awt.geom.*;

import javax.swing.UIManager;

import menus.MainMenu;

/**
 * <a href="http://java.sun.com/docs/books/tutorial/deployment/jar/appman.html">
 * Main-Class</a> of the <em>Pong Legacy</em> project with the <tt>public static 
 * void main(String...</tt> method as the entry point.
 * 
 * @author 2009-2010 WHS <a
 *         href="http://winchester.k12.ma.us/~dpetty/apcs/">APCS</a> class
 */
public class PongLegacy {
	/**
	 * <tt>PongLegacy</tt> main method, the <a href=
	 * "http://java.sun.com/docs/books/tutorial/deployment/jar/appman.html">
	 * Main-Class</a> entry point of the <em>Pong Legacy</em> project.
	 * 
	 * @param args
	 *            command-line arguments
	 */
	public static void main(String... args) {
		for (String string : args) {
			System.out.print(string + " ");
		}
		// RED_FLAG: test prints out vertices of all Polygons from 3 to 8
		// Polygon polygon;
		// for (int i = 3; i <= 8; i++) {
		// polygon = new Polygon(i);
		// System.out.println(polygon);
		// }
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}
		new MainMenu();
	}
}