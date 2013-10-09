import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class BanMessage extends JFrame implements ActionListener{
	private JPanel contentPane;
	private String nameID;
	private JTextArea message;
	
	
	
	
	public BanMessage(){
		
		
		setLayout(null);
		setTitle("Ban Screen");
		setResizable(true);
		setSize(300,300);
		JTextField textField = new JTextField();
		textField.setBounds(97,50,100,25);
		JLabel label = new JLabel("God won't spare you.");
		label.setBounds(100, 100, 100, 15);
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout());
	
		
		
		add(label);
		
		add(textField);
		textField.setColumns(10);
		
		
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
