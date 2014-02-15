package main;

import java.io.File;
import java.io.IOException;

import fileProcessing.FileReader;
import fileProcessing.Parser;

public class SDLCompiler {

	public static void main(String[] args){
		String xmlText = getXmlText("SDLCompiler/src/files/SmartDrive.svg");
		Parser.addSDLObjects(xmlText);
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
