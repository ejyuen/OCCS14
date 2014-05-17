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
import graphics.Graphics;
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
		
		System.out.println("Start State is : " + sdlNetwork.getStartState());
		Graphics g = new Graphics();
		
		ArrayList<Sensor> sensors = new ArrayList<Sensor>();
		sensors.add(new KeyboardSensor(KeyEvent.VK_F, SignalPack.Food, g));
		sensors.add(new KeyboardSensor(KeyEvent.VK_H, SignalPack.Hungry, g));
		sensors.add(new KeyboardSensor(KeyEvent.VK_M, SignalPack.MoneyRequest, g));
		sensors.add(new KeyboardSensor(KeyEvent.VK_E, SignalPack.END, g));
		sensors.add(new KeyboardSensor(KeyEvent.VK_T, SignalPack.TIMERDONE, g));
		
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
