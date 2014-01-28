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
	
	public void addMenueButton(String name, final Menue menue, int xBound, int yBound, int wBound, int lBound){
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
		button.setBounds(xBound, yBound, wBound, lBound);
		contentPane.add(button);
	}
	
	public void addTextField(){ //add in the required parameters
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(121, 45, 52, 14);
		contentPane.add(lblUsername);
		JTextField nameTextField = new JTextField();
		nameTextField.setBounds(104, 67, 86, 20);
		contentPane.add(nameTextField);
		nameTextField.setColumns(10);
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
