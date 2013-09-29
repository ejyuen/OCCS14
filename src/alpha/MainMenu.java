/*
 * MainMenu.java
 */

package alpha;

/**
 * PongLegacy main menu from which all other objects are launched.
 *
 * @author 2009-2010 WHS
 * <a href="http://winchester.k12.ma.us/~dpetty/apcs/">APCS</a> class
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

import alpha.Graphics;

/**
 * Main menu from which all other objects are launched. Three buttons enable players to chose
 * start, options, or exit.
 */
public class MainMenu extends JFrame {
    /**
     * Create a new main menu from which all other objects are launched.
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
        logo.setSize(100, 50);
        JLabel info = new JLabel("prototype v3.47");
        //.....Add other things here

        //These next lines deal with the center panel
        JPanel buttons = new JPanel();
        JButton start = new JButton("START");
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Player Name: (NOT USED)",null);
                
                new Statistics(name);
                // RED_FLAG: uses default 8-sided pong
                new Pong(8);
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