package alpha;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class ServerUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	
	public ServerUI() {
		setTitle("Pong Legacy | Prototype v0.2.1");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 500);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblMaxPlayerCount = new JLabel("Max Player Count:");
		lblMaxPlayerCount.setBounds(97, 25, 100, 15);
		contentPane.add(lblMaxPlayerCount);

		textField = new JTextField();
		textField.setBounds(97, 50, 100, 25);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnLaunchServer = new JButton("Launch Server");
		btnLaunchServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { //re did a bit of stuff here, check it over andy
				int players = Integer.parseInt(textField.getText());
				new Pong(players*2, new Server(players-1));
				setVisible(false);
			}
		});
		btnLaunchServer.setBounds(47, 400, 200, 25);
		contentPane.add(btnLaunchServer);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnExit.setBounds(117, 436, 60, 25);
		contentPane.add(btnExit);
	}
}
