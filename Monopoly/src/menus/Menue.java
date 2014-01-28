package menus;

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
	public Menue(){
		setTitle("Monopoly Inc. || V1.0 || Authors M.Li and A.Paine");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 500);
		setLocationRelativeTo(null);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	
	public void open(){
		setVisible(true);
	}
	
	public void hide(){
		setVisible(false); 
	}
	
	public void close(){
		setVisible(false); 
	}
}
