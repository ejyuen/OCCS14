package fileProcessing;
import java.util.regex.*;

public class Parser {
	public static Pattern actionSignalPattern = Pattern.compile("([0-9.,]+ ){5}");
	public static Pattern namePattern = Pattern.compile("x=\"[ 0-9a-z.\"=]+>[A-Za-z ]+");
	public static Pattern statePattern = Pattern.compile("L[ 0-9.,LC]+z");
	public static Pattern xPattern = Pattern.compile(" [0-9.]+");
	public static Pattern yPattern = Pattern.compile(",[0-9.]+");
	public static Pattern stringNamePattern = Pattern.compile(">[a-zA-Z]+");

	public static void addSDLObjects(String XMLText){
		Matcher actionSignalMatcher = actionSignalPattern.matcher(XMLText);
		Matcher stateMatcher = statePattern.matcher(XMLText);
		Matcher nameMatcher = namePattern.matcher(XMLText);
		
		while(stateMatcher.find()){
			String rawPoints = stateMatcher.group();
			if(nameMatcher.find(stateMatcher.end())) {
				String rawName = nameMatcher.group();
				Matcher xMatcher = xPattern.matcher(rawPoints);
				Matcher yMatcher = yPattern.matcher(rawPoints);
				Matcher stringNameMatcher = stringNamePattern.matcher(rawName);
				double highestX = 0;
				double lowestX = Double.MAX_VALUE;
				double highestY = 0;
				double lowestY = Double.MAX_VALUE;
				String name = "default";
				while(xMatcher.find()){
					if(Double.parseDouble(xMatcher.group()) > highestX){
						highestX = Double.parseDouble(xMatcher.group());
					}
					if(Double.parseDouble(xMatcher.group()) < lowestX){
						lowestX = Double.parseDouble(xMatcher.group());
					}
				}
				while(yMatcher.find()){
					if(Double.parseDouble(yMatcher.group().substring(1)) > highestY){
						highestY = Double.parseDouble(yMatcher.group().substring(1));
					}
					if(Double.parseDouble(yMatcher.group().substring(1)) < lowestY){
						lowestY = Double.parseDouble(yMatcher.group().substring(1));
					}
				}
				while(stringNameMatcher.find()){
					name = stringNameMatcher.group().substring(1);
				}
				System.out.println("" + name + " " + lowestX + "," + highestX + "," + lowestY + "," + highestY);
				
			}
			
		}
		
	
		while(actionSignalMatcher.find()){
			System.out.println("" + actionSignalMatcher.group());
		}
		
	}

	//TODO takes in the string from file reader and parses it into and SDL network
}
