package sensors;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import graphics.Graphics;
import utilities.SignalPack;


public class KeyboardSensor implements Sensor, KeyListener{
	
	private SignalPack signal;
	private int key;
	private boolean keyPressed = false;
	
	public KeyboardSensor(int key, SignalPack signal){
		this.key = key;
		this.signal = signal;
	}
	
	public KeyboardSensor(int key, SignalPack signal, Graphics graphics){
		this(key, signal);
		graphics.addKeyboardSensor(this);
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == key){
			keyPressed = true;
		}
	}
	
	public int getKey(){
		return key;
	}
	
	public SignalPack getSignal(){
		return signal;
	}
	
	public void keyReleased(KeyEvent e) {
		// TODO not implemented
		
	}

	public void keyTyped(KeyEvent e) {
		// TODO not implemented
	}

	/**
	 * returns signal after the key has been pressed only once
	 * blocks until key is pressed
	 * 
	 * @return signal
	 */
	public SignalPack checkSignal() {
		while(!keyPressed){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		keyPressed = false;
		return signal;
	}

}

	
