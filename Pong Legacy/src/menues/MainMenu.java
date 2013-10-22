package menues;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.border.*;

public class MainMenu extends JFrame {
	private JTextField textField;

	public MainMenu() {
		setTitle("Pong Legacy");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 500);
		setLocationRelativeTo(null);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(121, 45, 52, 14);
		contentPane.add(lblUsername);
		textField = new JTextField();
		textField.setBounds(104, 67, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnStartServer = new JButton("Start Server");
		btnStartServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// new Statistics(textField.getText()); Move this elsewhere.
				// Work on it when implementing chat client.
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ServerUI frame = new ServerUI();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				setVisible(false);
			}
		});
		btnStartServer.setBounds(97, 364, 100, 25);
		contentPane.add(btnStartServer);

		JButton btnStartClient = new JButton("Start Client");
		btnStartClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ClientUI frame = new ClientUI();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				setVisible(false);
			}
		});
		btnStartClient.setBounds(97, 400, 100, 25);
		contentPane.add(btnStartClient);

		JButton btnOptions = new JButton("Options");
		btnOptions.setBounds(37, 436, 100, 25);
		contentPane.add(btnOptions);

		JButton btnQuitGame = new JButton("Quit Game");
		btnQuitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnQuitGame.setBounds(157, 436, 100, 25);
		contentPane.add(btnQuitGame);

		setVisible(true);
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}
		new MainMenu();
	}
}