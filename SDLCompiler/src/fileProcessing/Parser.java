package fileProcessing;
import java.util.regex.*;

public class Parser {
	public static Pattern actionSignalPattern = Pattern.compile("([0-9.,]+ ){5}");
	public static Pattern namePattern = Pattern.compile("x=\"[ 0-9a-z.\"=]+>[A-Za-z ]+");
	public static Pattern statePattern = Pattern.compile("(M [0-9.]+ [0-9.]+ L.+z)");

	public static void addSDLObjects(String XMLText){
		Matcher actionSignalMatcher = actionSignalPattern.matcher(XMLText);
		Matcher stateMatcher = statePattern.matcher(XMLText);
		Matcher nameMatcher = namePattern.matcher(XMLText);
		
		while(stateMatcher.find()){
			String rawPoints = stateMatcher.group();
			if(nameMatcher.find(stateMatcher.end())) {
				String rawName = nameMatcher.group();
				System.out.println("" + rawPoints + " " + rawName);
			}
			
		}
		
	
		while(actionSignalMatcher.find()){
			System.out.println("" + actionSignalMatcher.group());
		}
		
	}

	//TODO takes in the string from file reader and parses it into and SDL network
}
