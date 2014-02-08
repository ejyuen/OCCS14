package main;

import java.io.File;
import java.io.IOException;

public class SDLCompiler {
	public static void main(String[] args){
		String pathFromCurrentDirectory = "SDLCompiler/src/files/SmartDrive.svg";
		String path = "";
		try {
			path = new File(new File(".").getAbsolutePath()).getCanonicalPath() + "/" + pathFromCurrentDirectory;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(FileReader.readFile(path));
	}
}
