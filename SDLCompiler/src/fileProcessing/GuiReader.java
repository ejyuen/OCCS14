package fileProcessing;

import fileProcessing.sdlGUI.*;
import sdlNetwork.*;

public class GuiReader {
	private GuiNetwork guiNetwork;
	private SDLNetwork SDLNetwork;

	public GuiReader(GuiNetwork guiNetwork, SDLNetwork SDLNetwork){
		this.guiNetwork = guiNetwork;
		this.SDLNetwork = SDLNetwork;
	}

	public void readStates(){
		for (GuiState gs : guiNetwork.getGuiStates()) {
			SDLNetwork.addState(new State(gs.getName()));
		}
	}
	public void readConnections(){
		State startState;
		Signal signal;
		Action action;
		State endState;
		for (GuiState gs : guiNetwork.getGuiStates()) {
			startState = new State(gs.getName());
			for(GuiConnection gc: guiNetwork.getGuiConnections()){
				if(gs.intersects(gc.getX1() - 20, gc.getY1() - 20, 40, 40)){
					//if(new Rectangle2D.Double(gc.getX2() - 20, gc.getY2() - 20 , 40, 40)); 
				}
			}
		}
	}
}
