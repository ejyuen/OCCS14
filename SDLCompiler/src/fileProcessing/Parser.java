package fileProcessing;
import java.awt.Graphics2D;
import java.util.regex.*;

import fileProcessing.*;
import fileProcessing.sdlGUI.*;


import sdlNetwork.SDLNetwork;

public class Parser {
	
	private enum actionSignal{
		ACTION, SIGNAL
	}
	
	public static Pattern actionSignalPattern = Pattern.compile("([0-9.,]+ ){5}");
	public static Pattern namePattern = Pattern.compile("x=\"[ 0-9a-z.\"=]+>[A-Za-z ]+");
	public static Pattern statePattern = Pattern.compile("L[ 0-9.,LC]+z");
	public static Pattern xPattern = Pattern.compile("( [0-9.]+)(?! )");
	public static Pattern yPattern = Pattern.compile(",[0-9.]+");
	public static Pattern stringNamePattern = Pattern.compile(">[a-zA-Z]+");
	public static Pattern connectionPattern = Pattern.compile("M[0-9,. ]+A[0-9,. ]+");
	private static GuiNetwork network = new GuiNetwork();

	public static void addSDLObjects(String XMLText){
		Matcher actionSignalMatcher = actionSignalPattern.matcher(XMLText);
		Matcher stateMatcher = statePattern.matcher(XMLText);
		Matcher nameMatcher = namePattern.matcher(XMLText);
		Matcher connectionMatcher = connectionPattern.matcher(XMLText);
		
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
				while(stringNameMatcher.find()){
					name = stringNameMatcher.group().substring(1);
				}
				
				network.addGuiState(new GuiState(name, lowestX, lowestY, highestX-lowestX, highestY-lowestY));
			}
			
		}
		
		while(actionSignalMatcher.find()){
			actionSignal type = actionSignal.SIGNAL; // 0 is Signal, 1 is Action, make this an enum im lazy
			String rawPoints = actionSignalMatcher.group();
			Matcher xMatcher = xPattern.matcher(rawPoints);
			double x2 = 0;
			int counter = 0;
			while(xMatcher.find()){
				if(counter == 1){
					x2 = Double.parseDouble(xMatcher.group());
				}
				if(counter == 2){
					if(Double.parseDouble(xMatcher. group()) < x2){
						type = actionSignal.SIGNAL;
					}else{
						type = actionSignal.ACTION;
					}
				}
				counter++;
			}
			if(type == actionSignal.SIGNAL){
				//System.out.println("yoooo");
				int[] yPoints = new int[5];
				Matcher xMatcher2 = xPattern.matcher(rawPoints);
				int xCounter = 0;
				int xone = 0;
				int xtwo = 0;
				int xthree = 0;
				while(xMatcher2.find()){
					switch(xCounter){
					case 0:
						xtwo = (int)(Double.parseDouble(xMatcher2.group().substring(1)));
						break;
					case 1:
						xthree = (int)(Double.parseDouble(xMatcher2.group().substring(1)));
						break;
					case 3:
						xone  = (int)(Double.parseDouble(xMatcher2.group().substring(1)));
						break;
					default:
						break;
					}
					xCounter++;
				}
				//System.out.println(xone + " " +  xtwo + " " + xthree + " " + xtwo + " " + xone);
				int[] xPoints = {xone, xtwo, xthree, xtwo, xone};
				Matcher yMatcher2 = yPattern.matcher(rawPoints);
				int yIndex = 0;
				while(yMatcher2.find()){
					yPoints[yIndex] = (int)(Double.parseDouble(yMatcher2.group().substring(1)));
					//System.out.println((int)(Double.parseDouble(yMatcher2.group().substring(1))));
					yIndex++;
				}
				//System.out.println(yPoints[0] + " " + yPoints[1] + " " + yPoints[2] + " " + yPoints[3] + " " + yPoints[4]);
				String name = "default";
				if(nameMatcher.find(actionSignalMatcher.end())) {
					String rawName = nameMatcher.group();
					Matcher stringNameMatcher = stringNamePattern.matcher(rawName);
					while(stringNameMatcher.find()){
						name = stringNameMatcher.group().substring(1);
					}
					boolean before = false;
					for(GuiAction ga: network.getGuiActions()){
						if(ga.getName().equals(name)){
							before = true;
						}
					}
					if(!before){
					network.addGuiAction(new GuiAction(name, xPoints, yPoints));
					}
				}
				/////start coding from here
			}
			if(type == actionSignal.ACTION){
				int[] yPoints = new int[5];
				Matcher xMatcher2 = xPattern.matcher(rawPoints);
				int xCounter = 0;
				int xone = 0;
				int xtwo = 0;
				int xthree = 0;
				while(xMatcher2.find()){
					switch(xCounter){
					case 0:
						xtwo = (int)(Double.parseDouble(xMatcher2.group().substring(1)));
						break;
					case 1:
						xthree = (int)(Double.parseDouble(xMatcher2.group().substring(1)));
						break;
					case 3:
						xone  = (int)(Double.parseDouble(xMatcher2.group().substring(1)));
						break;
					default:
						break;
					}
					xCounter++;
				}
				int[] xPoints = {xone, xtwo, xthree, xtwo, xone};
				Matcher yMatcher2 = yPattern.matcher(rawPoints);
				int yIndex = 0;
				while(yMatcher2.find()){
					yPoints[yIndex] = (int)(Double.parseDouble(yMatcher2.group().substring(1)));
					yIndex++;
				}
				String name = "default";
				if(nameMatcher.find(actionSignalMatcher.end())) {
					String rawName = nameMatcher.group();
					Matcher stringNameMatcher = stringNamePattern.matcher(rawName);
					while(stringNameMatcher.find()){
						name = stringNameMatcher.group().substring(1);
					}
					boolean befor3 = false;
					for(GuiSignal ga: network.getGuiSignals()){
						if(ga.getName().equals(name)){
							befor3 = true;
						}
					}
					if(!befor3){
					network.addGuiSignal(new GuiSignal(name, xPoints, yPoints));
					}
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
		
		

	}
	public static GuiNetwork getGuiNetwork(){
		return network;
	}

	//TODO takes in the string from file reader and parses it into and SDL network
}
