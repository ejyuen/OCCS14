package fileProcessing;
import java.util.regex.*;

import main.SDLCompiler;
public class Parser {
	public SDLCompiler sdlCompiler = new SDLCompiler();
	
	public void addSDLObjects(String XMLText){
		XMLText = sdlCompiler.getXmlText();
		
	}
	//TODO takes in the string from file reader and parses it into and SDL network
}
