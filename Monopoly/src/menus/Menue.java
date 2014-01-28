package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
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
