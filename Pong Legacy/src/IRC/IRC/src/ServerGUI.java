package IRC.IRC.src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;


public class ServerGUI extends JFrame implements ActionListener, WindowListener {
	
	private static final long serialVersionUID = 1L;

	private JButton stopStartButton;
	
	private JTextArea chat, event;
	
	private JTextField PortNumber;

	private Server server;
	
	private JButton removePerson;
	
	
	ServerGUI(int port) {
		super("Chat Server");
		server = null;
		
		JPanel north = new JPanel();
		north.add(new JLabel("Port number: "));
		PortNumber = new JTextField("  " + port);
		north.add(PortNumber);
		
		stopStartButton = new JButton("Start");
		stopStartButton.addActionListener(this);
		north.add(stopStartButton);
		add(north, BorderLayout.NORTH);
		
	
		removePerson = new JButton("kook");
		removePerson.addActionListener(this);
		north.add(removePerson);
		add(north, BorderLayout.NORTH);
		
		JPanel center = new JPanel(new GridLayout(2,1));
		chat = new JTextArea(80,80);
		chat.setEditable(false);
		appendRoom("Chat room.\n");
		center.add(new JScrollPane(chat));
		event = new JTextArea(80,80);
		event.setEditable(false);
		appendEvent("Events log.\n");
		center.add(new JScrollPane(event));	
		add(center);
		
		
		addWindowListener(this);
		setSize(600, 600);
		setVisible(true);
		
		
	}		

	
	void appendRoom(String str) {
		chat.append(str);
		chat.setCaretPosition(chat.getText().length() - 1);
	}
	void appendEvent(String str) {
		event.append(str);
		event.setCaretPosition(chat.getText().length() - 1);
		
	}
	
	// start or stop where clicked
	public void actionPerformed(ActionEvent e) {
		// if running we have to stop
		if(e.getActionCommand() == "kook"){
			BanMessage frame = new BanMessage();
			frame.setVisible(true);
			Scanner scan = new Scanner(System.in);
			String msg = scan.nextLine();
			server.kook(Integer.parseInt(msg));
			System.out.println("B& from server");
		}
		
		if(e.getActionCommand() == "Start"|| e.getActionCommand() == "Stop"){
			
		if(server != null) {
			server.stop();
			server = null;
			PortNumber.setEditable(true);
			stopStartButton.setText("Start");
			removePerson.setText("kook");
			
			return;
		}
      	// OK start the server	
		int port;
		try {
			port = Integer.parseInt(PortNumber.getText().trim());
		}
		catch(Exception er) {
			appendEvent("Invalid port number");
			return;
		}
		// ceate a new Server
		server = new Server(port, this);
		// and start it as a thread
		new ServerRunning().start();
		stopStartButton.setText("Stop");
		PortNumber.setEditable(false);
		}
	}
	
	// entry point to start the Server
	public static void main(String[] arg) {
		// start server default port 1500
		new ServerGUI(1500);
	}

	/*
	 * If the user click the X button to close the application
	 * I need to close the connmy theory  is that
unalaq has been egged on by some evil spirit
and he can talk to them
and the evil spirit is using him to cause a war
which will let the spirts run wild
idk thats just my theoryection with the server to free the port
	 */
	public void windowClosing(WindowEvent e) {
		// if my Server exist
		if(server != null) {
			try {
				server.stop();			// ask the server to close the conection
			}
			catch(Exception eClose) {
			}
			server = null;
		}
		// dispose the frame
		dispose();
		System.exit(0);
	}
	// I can ignore the other WindowListener method
	public void windowClosed(WindowEvent e) {}
	public void windowOpened(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowActivated(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}

	/*
	 * A thread to run the Server
	 */
	class ServerRunning extends Thread {
		public void run() {
			server.start();         // should execute until if fails
			// the server failed
			stopStartButton.setText("Start");
			PortNumber.setEditable(true);
			appendEvent("Server crashed\n");
			server = null;
		}
	}

}


