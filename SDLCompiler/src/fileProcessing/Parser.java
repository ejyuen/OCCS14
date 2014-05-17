package fileProcessing;
import java.awt.Graphics2D;
import java.util.regex.*;

import fileProcessing.*;
import fileProcessing.sdlGUI.*;


import sdlNetwork.SDLNetwork;

public class Parser {
	
	/*
	 * Used to distinguish Actions from Signals while parsing.
	 * They are initially grouped together because the regex used to find them looks for a series of 5 (x,y) coordinates, which both 
	 * actions and signals have.
	 */
	private enum actionSignal{
		ACTION, SIGNAL
	}
	/* Finds a series of 5 (x,y) coordinates. {f\" points=\"} is used to avoid duplication. Without this part, 
	 * this pattern would find multiple instances of the same set of 5 coordinates.
	 */
	public static Pattern actionSignalPattern = Pattern.compile("f\" points=\"([0-9.,]+ ){5}");
	/* Finds all coordinates proceeding the capital letter L. In the XML, the first coordinates describing 
	 * a state follow the first L in MLCCLCC. {z} is used to avoid duplication. Without this part,
	 * this pattern would find two instances of the same set of coordinates.
	 */
	public static Pattern statePattern = Pattern.compile("L[ 0-9.,LC]+z");
	/*
	 * Finds a double proceeding a comma. All y coordinates follow a comma in the XML.
	 */
	public static Pattern yPattern = Pattern.compile(",[0-9.]+");
	/*
	 * Finds a double proceeding either a space or a double quote. All x coordinates follow one of these
	 * two things in the XML.
	 */
	public static Pattern xPattern = Pattern.compile("([ \"][0-9.]+)(?! )");
	/*
	 * Finds text proceeding a right arrow bracket {>}. The XML always places the text contained within a shape
	 * after this character. 
	 */
	public static Pattern namePattern = Pattern.compile(">[a-zA-Z0-9();]+");
	/*
	 * Finds coordinates following the capital letter M and then the capital letter A. The (x,y) following M is
	 * the start point of the line and the (x,y) following A is the end point. The MA combination is characteristic
	 * of the connections in the XML.
	 */
	public static Pattern connectionPattern = Pattern.compile("M[0-9,. ]+A[0-9,. ]+");
	/*
	 * Finds a set of 4 (x,y) coordinates. These are characteristic of the decision shapes (diamonds). {f\" points=\"} is 
	 * again used to avoid duplication.
	 */
	public static Pattern decisionPattern = Pattern.compile("f\" points=\"([0-9.,]+ ){4}\"/>");
	private static GuiNetwork network = new GuiNetwork();

	public static void addSDLObjects(String XMLText){
		Matcher actionSignalMatcher = actionSignalPattern.matcher(XMLText);
		Matcher stateMatcher = statePattern.matcher(XMLText);
		Matcher nameMatcher = namePattern.matcher(XMLText);
		Matcher connectionMatcher = connectionPattern.matcher(XMLText);
		Matcher decisionMatcher = decisionPattern.matcher(XMLText);
		
		while(stateMatcher.find()){
			String rawPoints = stateMatcher.group();
			if(nameMatcher.find(stateMatcher.end())) {
				String name = nameMatcher.group().substring(1);
				Matcher xMatcher = xPattern.matcher(rawPoints);
				Matcher yMatcher = yPattern.matcher(rawPoints);
				double highestX = 0;
				double lowestX = Double.MAX_VALUE;
				double highestY = 0;
				double lowestY = Double.MAX_VALUE;
				while(xMatcher.find()){
					if(Double.parseDouble(xMatcher.group().substring(1)) > highestX){
						highestX = Double.parseDouble(xMatcher.group().substring(1));
					}
					if(Double.parseDouble(xMatcher.group().substring(1)) < lowestX){
						lowestX = Double.parseDouble(xMatcher.group().substring(1));
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
				
				network.addGuiState(new GuiState(name, lowestX, lowestY, highestX-lowestX, highestY-lowestY));
			}
			
		}
		
		while(actionSignalMatcher.find()){
			actionSignal type = null; // 0 is Signal, 1 is Action, make this an enum im lazy
			String rawPoints = actionSignalMatcher.group();
			Matcher xMatcher = xPattern.matcher(rawPoints);
			Matcher yMatcher = yPattern.matcher(rawPoints);
			double outerXPoint = 0;
			double innerXPoint = 0;
			int counter = 0;
			while(xMatcher.find()){
				if(counter == 1){
					outerXPoint = Double.parseDouble(xMatcher.group());
				}
				if(counter == 2){
					innerXPoint = Double.parseDouble(xMatcher.group());
				}
				counter++;
			}
			if(innerXPoint < outerXPoint)
				type = actionSignal.ACTION;
			else
				type = actionSignal.SIGNAL;
			xMatcher.reset();
			
			if(type == actionSignal.SIGNAL){
				int[] yPoints = new int[5];
				int[] xPoints = new int[5];
				int xIndex = 0;
				while(xMatcher.find()){
					xPoints[xIndex] = (int)(Double.parseDouble(xMatcher.group().substring(1)));
					xIndex++;
				}
				int yIndex = 0;
				while(yMatcher.find()){
					yPoints[yIndex] = (int)(Double.parseDouble(yMatcher.group().substring(1)));
					yIndex++;
				}
				if(nameMatcher.find(actionSignalMatcher.end())) {
					String name = nameMatcher.group().substring(1);
					network.addGuiAction(new GuiAction(name, xPoints, yPoints));
				}
			}
			if(type == actionSignal.ACTION){
				int[] yPoints = new int[5];
				int[] xPoints = new int[5];
				int xIndex = 0;
				int yIndex = 0;
				while(xMatcher.find()){
					xPoints[xIndex] = (int)(Double.parseDouble(xMatcher.group().substring(1)));
					xIndex++;
				}
				while(yMatcher.find()){
					yPoints[yIndex] = (int)(Double.parseDouble(yMatcher.group().substring(1)));
					yIndex++;
				}
				if(nameMatcher.find(actionSignalMatcher.end())) {
					String name = nameMatcher.group().substring(1);
					network.addGuiSignal(new GuiSignal(name, xPoints, yPoints));
				}
			}
		}
		while(connectionMatcher.find()){
			double x1 = 0;
			double x2 = 0;
			double y1 = 0;
			double y2 = 0;
			int xCounter = 0;
			int yCounter = 0;
			String rawPoints = connectionMatcher.group();
			Matcher xMatcher = xPattern.matcher(rawPoints);
			Matcher yMatcher = yPattern.matcher(rawPoints);
			while(xMatcher.find()){
				if (xCounter == 0)
					x1 = Double.parseDouble(xMatcher.group().substring(1));
				if (xCounter == 2)
					x2 = Double.parseDouble(xMatcher.group().substring(1));
				xCounter++;
			}
			while(yMatcher.find()){
				if (yCounter == 0)
					y1 = Double.parseDouble(yMatcher.group().substring(1));
				if (yCounter == 2)
					y2 = Double.parseDouble(yMatcher.group().substring(1));
				yCounter++;
			}
			network.addGuiConnection(new GuiConnection(x1, y1, x2, y2));
		}
		
		while(decisionMatcher.find()){
			String rawPoints = decisionMatcher.group();
			Matcher xMatcher = xPattern.matcher(rawPoints);
			Matcher yMatcher = yPattern.matcher(rawPoints);
			int[] xPoints = new int[4];
			int[] yPoints = new int[4];
			int xIndex = 0;
			int yIndex = 0;
			while(xMatcher.find()){
				xPoints[xIndex] = (int)(Double.parseDouble(xMatcher.group().substring(1)));
				xIndex++;
			}
			while(yMatcher.find()){
				yPoints[yIndex] = (int)(Double.parseDouble(yMatcher.group().substring(1)));
				yIndex++;
			}
			network.addGuiDecision(new GuiDecision(xPoints, yPoints));
		}

	}
	public static GuiNetwork getGuiNetwork(){
		return network;
	}
}
