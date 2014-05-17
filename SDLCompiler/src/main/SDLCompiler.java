package main;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Queue;

import fileProcessing.FileReader;
import fileProcessing.GuiReader;
import fileProcessing.Parser;
import fileProcessing.sdlGUI.GuiNetwork;
import sdlNetwork.Action;
import sdlNetwork.Connection;
import sdlNetwork.SDLNetwork;
import sdlNetwork.State;
import sdlRunner.SDLRunner;
import sensors.*;
import utilities.ActionPack;
import utilities.SignalPack;
public class SDLCompiler {
	
	public static void main(String[] args){
		long startTime = System.currentTimeMillis();
		String xmlText = getXmlText("SDLCompiler/src/files/hungryman.svg");
		Parser.addSDLObjects(xmlText);
		GuiNetwork.paintNetwork(Parser.getGuiNetwork());
		SDLNetwork sdlNetwork = new SDLNetwork();
		GuiReader guiReader = new GuiReader(Parser.getGuiNetwork(), sdlNetwork);
		guiReader.readStates();
		guiReader.readConnections();
		//guiReader.actions.add(new Action("look"));
		for(State s: sdlNetwork.getStates()){
			for(Connection c: s.getConnections())
			System.out.println(c);
		}
		System.out.println("Start State is : " + sdlNetwork.getStartState());
		
		//switch(e.getKeyCode()){
		//case KeyEvent.VK_F:
//			signal = SignalPack.Food;
//			break;
		//case KeyEvent.VK_H:
//			signal = SignalPack.Hungry;
//			break;
		//case KeyEvent.VK_M:
//			signal = SignalPack.MoneyRequest;
//			break;
		//case KeyEvent.VK_E:
//			signal = SignalPack.END;
//			break;
		//case KeyEvent.VK_T:
//			signal = SignalPack.TIMERDONE;
//			break;

		
		ArrayList<Sensor> sensors = new ArrayList<Sensor>();
		sensors.add(new KeyboardSensor(KeyEvent.VK_F, SignalPack.Food));
		sensors.add(new KeyboardSensor(KeyEvent.VK_H, SignalPack.Hungry));
		sensors.add(new KeyboardSensor(KeyEvent.VK_M, SignalPack.MoneyRequest));
		sensors.add(new KeyboardSensor(KeyEvent.VK_E, SignalPack.END));
		sensors.add(new KeyboardSensor(KeyEvent.VK_T, SignalPack.TIMERDONE));
		
		//TODO add more signals to SignalPack, add more ActionPack
		SDLRunner sr = new SDLRunner(sdlNetwork, sensors);
	}
	
	/**
	 * 
	 * @param pathToFile The path to the file from where you are running this program
	 * @return the xml in the file as a string
	 */
	public static String getXmlText(String pathToFile){
		String path = "";
		try {
			path = new File(new File(".").getAbsolutePath()).getCanonicalPath()
					+ "/" + pathToFile;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return FileReader.readFile(path);
	}
}
