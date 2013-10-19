package alpha;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import alpha.communicator.Client;

public class ClientUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JRadioButton rdbtnSelectAServer, rdbtnManualConnection;

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

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnExit.setBounds(117, 436, 60, 25);
		contentPane.add(btnExit);

		rdbtnSelectAServer = new JRadioButton("Select a server from the list:");
		rdbtnSelectAServer.setBounds(66, 25, 161, 25);
		contentPane.add(rdbtnSelectAServer);

		rdbtnManualConnection = new JRadioButton("Manual Connection:");
		rdbtnManualConnection.setBounds(87, 325, 120, 25);
		contentPane.add(rdbtnManualConnection);
		
		buttonGroup();

		textField = new JTextField();
		textField.setBounds(47, 355, 200, 25);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String ipAddress = null;
				if (rdbtnManualConnection.isSelected()) {
					ipAddress = textField.getText();
				}else if(rdbtnSelectAServer.isSelected()){
					ipAddress = "127.0.0.1"; //something i will add later
				}

				new Pong(new Client(ipAddress));
				setVisible(false);
			}
		});
		btnConnect.setBounds(47, 400, 200, 25);
		contentPane.add(btnConnect);
	}
	
	private void buttonGroup(){
		ButtonGroup serverSelect = new ButtonGroup();
		serverSelect.add(rdbtnManualConnection);
		serverSelect.add(rdbtnSelectAServer);
	}
}
