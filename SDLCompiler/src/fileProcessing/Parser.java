package fileProcessing;
import java.util.regex.*;

public class Parser {
	public static Pattern actionSignalPattern = Pattern.compile("([0-9.,]+ ){5}");
	public static Pattern namePattern = Pattern.compile("x=\"[ 0-9a-z.\"=]+>[A-Za-z ]+");

	public static void addSDLObjects(String XMLText){
		
		System.out.println("Starting");
		System.out.println("" + XMLText);
		Matcher actionSignalMatcher = actionSignalPattern.matcher(XMLText);
	
		while(actionSignalMatcher.find()){
			System.out.println("true");
			System.out.println("" + actionSignalMatcher.group());
		}
		
	}

	//TODO takes in the string from file reader and parses it into and SDL network
}
