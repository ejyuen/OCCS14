package fileProcessing;
import java.util.regex.*;

public class Parser {
	public static Pattern actionSignalPattern = Pattern.compile("([ 0-9.]+,[ 0-9.]+ ){5}");
	public static Pattern namePattern = Pattern.compile("x=\"[ 0-9a-z.\"=]+>[A-Za-z ]+");

	public static void addSDLObjects(String XMLText){
		System.out.println("Starting");
		Matcher actionSignalMatcher = actionSignalPattern.matcher(XMLText);
		if(actionSignalMatcher.matches()){
			for(int i = 0; i <= actionSignalMatcher.groupCount(); i++){
				System.out.println("" + actionSignalMatcher.group(i));
			}
		}
	}

	//TODO takes in the string from file reader and parses it into and SDL network
}
