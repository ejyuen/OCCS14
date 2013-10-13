package IRC.IRC.src;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;



public class BanMessage extends JFrame implements ActionListener{
//	private JPanel contentPane;
//	private String nameID;
//	private JTextArea message;
	  private JFrame thisWindow;
	  private JTextArea nameID;
	  private JButton ban;
	
	
	
	public BanMessage(){
		
		super("Ban Message");
	    thisWindow = this;

		nameID = new JTextArea(10, 20);
		ban = new JButton("BANISH FROM KINGDOM!");
	    ban.addActionListener(this);
	    
	    Box box1 = Box.createVerticalBox();
	    box1.add(Box.createVerticalStrut(10));
	    box1.add(nameID);
	    
	    Box box2 = Box.createVerticalBox();
	    box2.add(Box.createVerticalStrut(10));
	    box2.add(ban);
	    
		
		thisWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Ban Screen");
		setResizable(true);
		setSize(300,300);
	    
	    thisWindow.setVisible(true);
		
	    
	    Container c = thisWindow.getContentPane();
	    c.setLayout(new FlowLayout());
	    c.add(box1);
	    c.add(box2);

	    nameID.setText("Who is being a hobag?");

//		setLayout(null);
//		setTitle("Ban Screen");
//		setResizable(true);
//		setSize(300,300);
//		JTextField textField = new JTextField();
//		textField.setBounds(97,50,100,25);
//		JLabel label = new JLabel("God won't spare you.");
//		label.setBounds(100, 100, 100, 15);
//		
//		setLocationRelativeTo(null);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5,5,5,5));
//		setContentPane(contentPane);
//		contentPane.setLayout(new FlowLayout());
//	
//		
//		
//		add(label);
//		
//		add(textField);
//		textField.setColumns(10);
		
		
	}
	private void setUpGui()
	{

	    
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
