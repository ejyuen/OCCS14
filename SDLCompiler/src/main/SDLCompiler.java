package main;

import java.io.File;
import java.io.IOException;

import fileProcessing.FileReader;

public class SDLCompiler {

	public static void main(String[] args){
		getxmlText("SDLCompiler/src/files/SmartDrive.svg");
	}
	
	/**
	 * 
	 * @param pathToFile The path to the file from where you are running this program
	 * @return the xml in the file as a string
	 */
	public static String getxmlText(String pathToFile){
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
