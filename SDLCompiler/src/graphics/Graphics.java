package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import sensors.KeyboardSensor;
import sdlNetwork.*;

public class Graphics extends JPanel{
	JFrame frame;
	ArrayList<KeyboardSensor> keySensors;
	private Graphics2D g2 = null;
	private State currentState = null;
	
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
	
	public void paintState() {
		g2.drawString("Current State = " + currentState.toString(), 300, 0);
	}
	
	public void setState(State s) {
		currentState = s;
	}
	
	public void paintKeys() {
		for (KeyboardSensor ks : keySensors) {
			int ydisplace = 20;
			g2.drawString("Key", 0, 0);
			g2.drawString("Signal", 50, 0);
			g2.drawString(String.valueOf((char) ks.getKey()), 0, ydisplace);
			g2.drawString(ks.getSignal().toString(), 50, ydisplace);
			ydisplace += 20;
		}
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
