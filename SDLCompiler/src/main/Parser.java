package main;
import java.util.regex.*;
public class Parser {
	public SDLCompiler sdlCompiler = new SDLCompiler();
	
	public void addSDLObjects(String XMLText){
		XMLText = sdlCompiler.getXmlText();
		
	}
	//TODO takes in the string from file reader and parses it into and SDL network
}
