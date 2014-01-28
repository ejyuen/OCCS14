package menus;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class MainMenue extends Menue{
	public MainMenue(){
		super();
		
		//Host button
		addMenueButton("Host", new HostMenue(this), 100, 100, 100, 25);
		
		//Company button
		addMenueButton("Company", new CompanyMenue(this), 100, 150, 100, 25);
		
		//Investor button
		addMenueButton("Investor", new InvestorMenue(this), 100, 200, 100, 25);
	}
	
	public void addMenueButton(String name, final Menue menue, int xBound, int yBound, int wBound, int lBound){
		JButton button = new JButton(name);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Menue.open(menue);
							Menue.hide(MainMenue.this);
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
}
