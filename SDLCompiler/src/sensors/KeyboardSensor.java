package sensors;


import utilities.SignalPack;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardSensor implements Sensor, KeyListener, ActionListener{
	
	private SignalPack signal;
	
	public void keyPressed(KeyEvent e){
			signal = SignalPack.Hungry;
			
			switch(e.getKeyCode()){
			case KeyEvent.VK_F:
				signal = SignalPack.Food;
				break;
			case KeyEvent.VK_H:
				signal = SignalPack.Hungry;
				break;
			case KeyEvent.VK_M:
				signal = SignalPack.MoneyRequest;
				break;
			case KeyEvent.VK_E:
				signal = SignalPack.END;
				break;
			case KeyEvent.VK_T:
				signal = SignalPack.TIMERDONE;
				break;
			default: 
				break;
			}
				
	}
	
	public void keyTyped(KeyEvent e){
	}

	public void actionPerformed(ActionEvent e) {
	}
	
	public void keyReleased(KeyEvent e){
	}
	
	
	
	public SignalPack getSignal(){
		return signal; 
	}

}

	
