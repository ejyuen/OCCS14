package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import pong.Pong;
import utilities.Constants;

import communicator.Client;


public class OptionsUI extends JFrame {
	private static final long serialVersionUID = 1019782401351695809L;
	private JPanel contentPane;
	private JRadioButton rdbtnRetro;
	
	public OptionsUI() {
		setTitle("Pong Legacy");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 500);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		rdbtnRetro = new JRadioButton("Retro Graphics?");
		rdbtnRetro.setBounds(66, 25, 161, 25);
		contentPane.add(rdbtnRetro);
		
		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (rdbtnRetro.isSelected()){
					Constants.retro = true;
				} else {
					Constants.retro = false;
				}
				setVisible(false);
			}
		});
		btnDone.setBounds(47, 400, 200, 25);
		contentPane.add(btnDone);
	}
}
