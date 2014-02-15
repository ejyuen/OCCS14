package main;

import java.io.File;
import java.io.IOException;

import fileProcessing.FileReader;

public class SDLCompiler {
	private String xmlText;

	public static void main(String[] args){
		//todo put main stuff here
	}
	
	public String getXmlText(){
		return xmlText;
	}
	public void setXmlText(){
		String pathFromCurrentDirectory = "SDLCompiler/src/files/SmartDrive.svg";
		String path = "";
		try {
			path = new File(new File(".").getAbsolutePath()).getCanonicalPath() + "/" + pathFromCurrentDirectory;
		} catch (IOException e) {
			e.printStackTrace();
		}
		xmlText = FileReader.readFile(path);
	}
	
}
