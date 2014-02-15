package fileProcessing;
import java.util.regex.*;

import main.SDLCompiler;
public class Parser {
	public SDLCompiler sdlCompiler = new SDLCompiler();
	public static Pattern actionSignalPattern = Pattern.compile("([ 0-9.]+,[ 0-9.]+ ){5}");
	public static Pattern namePattern = Pattern.compile("x=\"[ 0-9a-z.\"=]+>[A-Za-z ]+");
	
	public void addSDLObjects(String XMLText){
		XMLText = sdlCompiler.getXmlText();
		
	}
	//TODO takes in the string from file reader and parses it into and SDL network
}
