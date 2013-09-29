package prototype;

/**
 * @(#)MainMenu.java
 *
 *This is Main Menu class and the main class for the game
 * @author Michael Calnan
 * @version 6.23 2010/5/18
 */
import javax.swing.BoxLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class MainMenu extends JFrame {
	/**
	 * Creates a new Main Menu class (the frame is created within the constructor)
	 */
    public MainMenu() {
		// Assumes ClassLoader for this class loads it from the .JAR file.
    	ImageIcon icon = new ImageIcon(this.getClass().getResource("/images/logo.jpg"));
    	//This is the basics for the main window
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    	setTitle("Pong Legacy");
    	setLayout(new BorderLayout());
    	setSize(600, 600);
    	setResizable(false);
    	//Now the logo, statusbar, and other things we want to add in
    	JLabel logo = new JLabel(icon);
    	JLabel info = new JLabel("prototype v6.14");
    	//.....Add other things here

    	//These next lines deal with the center panel
    	JPanel buttons = new JPanel();
    	JButton start = new JButton("START");
    	start.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			Pong.main(new String[0]);
    			setVisible(false);
    		}
    	});
    	JButton option = new JButton("OPTIONS");
    	option.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			//sub = new OptionWindow();
    			//sub.start;
    		}
    	});
    	JButton close = new JButton("EXIT");
    	close.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			System.exit(0);
    		}
    	});
    	buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
    	buttons.setBorder(new EmptyBorder(new Insets(20, 150, 20, 20)));
    	buttons.add(start);
    	buttons.add(Box.createRigidArea(new Dimension(0, 5)));
    	buttons.add(option);
    	buttons.add(Box.createRigidArea(new Dimension(0, 5)));
    	buttons.add(close);
    	buttons.setBackground(Color.black);
    	//buttons.pack();
    	//buttons.setVisible(true);
    	//Now we are going to put everything inside the main panel
    	add(logo, BorderLayout.NORTH);
    	add(buttons, BorderLayout.CENTER);
    	add(info, BorderLayout.SOUTH);
    	setVisible(true);
    	pack();
    }
    public static void main(String...args) {
    	new MainMenu();
    }


}