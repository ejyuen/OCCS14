package fileProcessing;
import java.util.regex.*;

import fileProcessing.*;
import fileProcessing.sdlGUI.*;

import sdlNetwork.SDLNetwork;

public class Parser {
	public static Pattern actionSignalPattern = Pattern.compile("([0-9.,]+ ){5}");
	public static Pattern namePattern = Pattern.compile("x=\"[ 0-9a-z.\"=]+>[A-Za-z ]+");
	public static Pattern statePattern = Pattern.compile("L[ 0-9.,LC]+z");
	public static Pattern xPattern = Pattern.compile(" [0-9.]+");
	public static Pattern yPattern = Pattern.compile(",[0-9.]+");
	public static Pattern stringNamePattern = Pattern.compile(">[a-zA-Z]+");
	private static GuiNetwork network = new GuiNetwork();

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
				network.addGuiState(new GuiState(name, lowestX, highestX, lowestY, highestY));
			}
			
		}
		while(actionSignalMatcher.find()){
			int type = 0; // 0 is Signal, 1 is Action, make this an enum im lazy
			String rawPoints = actionSignalMatcher.group();
			Matcher xMatcher = xPattern.matcher(rawPoints);
			Matcher yMatcher = yPattern.matcher(rawPoints);
			double x2 = 0;
			int counter = 0;
			while(xMatcher.find()){
				if(counter == 1){
					x2 = Double.parseDouble(xMatcher.group());
				}
				if(counter == 2){
					if(Double.parseDouble(xMatcher.group()) < x2){
						type = 0;
					}else{
						type = 1;
					}
				}
				counter++;
			}
			if(type == 0){
				/////start coding from here
			}
			
		}
		

		
	
		/*while(actionSignalMatcher.find()){
			System.out.println("" + actionSignalMatcher.group());
		}
		*/
		
	}

	//TODO takes in the string from file reader and parses it into and SDL network
}
