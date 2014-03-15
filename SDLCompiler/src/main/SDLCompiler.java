package main;

import java.io.File;

import java.io.IOException;
import fileProcessing.FileReader;
import fileProcessing.GuiReader;
import fileProcessing.Parser;
import fileProcessing.sdlGUI.GuiNetwork;
import sdlNetwork.Connection;
import sdlNetwork.SDLNetwork;
import sdlNetwork.State;
public class SDLCompiler {
	
	public static void main(String[] args){
		long startTime = System.currentTimeMillis();
		String xmlText = getXmlText("SDLCompiler/src/files/SmartDrive.svg");
		Parser.addSDLObjects(xmlText);
		GuiNetwork.paintNetwork(Parser.getGuiNetwork());
		SDLNetwork sdlNetwork = new SDLNetwork();
		GuiReader guiReader = new GuiReader(Parser.getGuiNetwork(), sdlNetwork);
		guiReader.readStates();
		guiReader.readConnections();
		for(State s: sdlNetwork.getStates()){
			for(Connection c: s.getConnections())
			System.out.println(c);
		}
		System.out.println(System.currentTimeMillis() - startTime);
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
