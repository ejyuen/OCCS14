package menus;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

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
}
