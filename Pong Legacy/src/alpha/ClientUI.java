package alpha;

import javax.swing.*;
import javax.swing.border.EmptyBorder;



public class ClientUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	public ClientUI() {
		setTitle("Pong Legacy | Prototype v0.2.1");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 500);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.setBounds(47, 400, 200, 25);
		contentPane.add(btnConnect);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(117, 436, 60, 25);
		contentPane.add(btnBack);
		
		JRadioButton rdbtnSelectAServer = new JRadioButton("Select a server from the list:");
		rdbtnSelectAServer.setBounds(66, 25, 161, 25);
		contentPane.add(rdbtnSelectAServer);
		
		JRadioButton rdbtnManualConnection = new JRadioButton("Manual Connection:");
		rdbtnManualConnection.setBounds(87, 325, 120, 25);
		contentPane.add(rdbtnManualConnection);
		
		textField = new JTextField();
		textField.setBounds(47, 355, 200, 25);
		contentPane.add(textField);
		textField.setColumns(10);
	}
}
