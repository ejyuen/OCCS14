package menus;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * the default class for everything
 * 
 * @author Matthew Li
 *
 */
public class Menue extends JFrame{
	protected JPanel contentPane;
	private Menue lastMenue;
	
	public Menue(){
		this(null);
	}
	
	public Menue(Menue previousMenue){
		lastMenue = previousMenue;
		setTitle("Monopoly Inc. || V1.0");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 500);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBack = new JButton(lastMenue != null? "Back":"Exit");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menue.hide(Menue.this);
				if(lastMenue != null){
					Menue.open(lastMenue);
				} else {
					System.exit(0);
				}
			}
		});
		btnBack.setBounds(100, 436, 100, 25);
		contentPane.add(btnBack);
	}
	
	/**
	 * 
	 * @param name
	 * @param menue
	 * @param xBound
	 * @param yBound
	 * @param wBound
	 * @param hBound
	 * @return
	 */
	public JButton addMenueButton(String name, final Menue menue, int xBound, int yBound, int wBound, int hBound){
		JButton button = new JButton(name);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Menue.open(menue);
							Menue.hide(Menue.this);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		button.setBounds(xBound, yBound, wBound, hBound);
		contentPane.add(button);
		
		return button;
	}
	
	/**
	 * 
	 * @param word the word printed above the 
	 * @param xBound the x-coord of the upper right hand corner of the textField
	 * @param yBound the y-coord of the upper right hand corner of the textField
	 * @param wBound the width (horzontal) of the textField
	 * @param hBound the height (vertical) of the textField
	 * 
	 * @return returns the textField
	 */
	public JTextField addTextField(String word, int xBound, int yBound, int wBound, int hBound){ //add in the required parameters
		//TODO figure out a better way to setBound for the word, maybe based on word length
		
		//add the label
		JLabel label = new JLabel(word);
		label.setBounds(xBound, yBound - hBound, wBound, hBound);
		contentPane.add(label);
		
		//add the textfield
		JTextField textField = new JTextField();
		textField.setBounds(xBound, yBound, wBound, hBound);
		contentPane.add(textField);
		textField.setColumns(10);
		
		return textField;
	}
	
	public static void open(Menue menue){
		menue.setVisible(true);
	}
	
	public static void hide(Menue menue){
		menue.setVisible(false); 
	}
	
	public static void close(Menue menue){
		menue.dispose();
	}
}
