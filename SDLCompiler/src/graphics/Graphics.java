package graphics;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import sensors.KeyboardSensor;

public class Graphics extends JPanel{
	JFrame frame;
	ArrayList<KeyboardSensor> keySensors;
	
	public Graphics(){
		this(new ArrayList<KeyboardSensor>());
	}
	
	public Graphics(ArrayList<KeyboardSensor> keySensors){
		this.keySensors = keySensors;
		frame = new JFrame("Test");
		init();
		for(KeyboardSensor s : keySensors){
			frame.addKeyListener(s);
		}
	}
	
	public void addKeyboardSensor(KeyboardSensor keySensor){
		keySensors.add(keySensor);
		frame.addKeyListener(keySensor);
	}
	
	private void init() {
		frame.setMinimumSize(new Dimension(480, 480));
		frame.setSize(600, 600);
		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setFocusable(true);
		this.setFocusable(true);
	}
}
