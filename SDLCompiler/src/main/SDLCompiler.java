package main;

import java.io.File;
import java.io.IOException;

public class SDLCompiler {
	private String xmlText;
	
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
