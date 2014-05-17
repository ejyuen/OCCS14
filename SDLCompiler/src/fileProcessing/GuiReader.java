package fileProcessing;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import fileProcessing.sdlGUI.*;
import sdlNetwork.*;

public class GuiReader {
	private GuiNetwork guiNetwork;
	private SDLNetwork SDLNetwork;
	private GuiAction lastAction;
	
	public GuiReader(GuiNetwork guiNetwork, SDLNetwork SDLNetwork){
		this.guiNetwork = guiNetwork;
		this.SDLNetwork = SDLNetwork;
	}

	public void readStates(){
		for(GuiState gs : guiNetwork.getGuiStates()) {
			SDLNetwork.addState(new State(gs.getName()));
		}

	}

	@SuppressWarnings("null")
	public void readConnections(){
		State startState;
		Signal signal;
		State endState;
		
		GuiConnection actionConnection;
		for (GuiState gs : guiNetwork.getGuiStates()) {
			startState = new State(gs.getName());
			for(GuiConnection gc: guiNetwork.getGuiConnections()){
				if(gs.intersects(gc.getX1() - 20, gc.getY1() - 20, 40, 40)){
					for(GuiSignal gsl : guiNetwork.getGuiSignals()){
						if((gsl.getBounds()).intersects(gc.getX2() - 20, gc.getY2() - 20, 40, 40)){
							signal = new Signal(gsl.getName());
							for(GuiConnection gc2 : guiNetwork.getGuiConnections()){
								if((gsl.getBounds()).intersects(gc2.getX1() - 20, gc2.getY1() - 20, 40, 40)){
									actionConnection = gc2;
									ArrayList<Action> actions = new ArrayList<Action>();
									while(getNextGuiAction(actionConnection).getName() != "null"){
										actions.add(new Action(getNextGuiAction(actionConnection).getName()));
										for(GuiConnection gcA : guiNetwork.getGuiConnections()){
											if(getNextGuiAction(actionConnection).intersects(gcA.getX1() - 20, gcA.getY1() - 20, 40, 40)){
												actionConnection = gcA;
											}
										}
									}
									if(actions.get(actions.size()-1).getName().equals(lastAction.getName())){
										
									}else{
									actions.add(new Action(lastAction.getName()));
									}
									for(GuiState gs2 : guiNetwork.getGuiStates()){
										if(gs2.intersects(actionConnection.getX2() - 20, actionConnection.getY2() - 20, 40, 40)){
											endState = new State(gs2.getName());
											for(int i = 0; i < SDLNetwork.getStates().size(); i++){
												if(SDLNetwork.getStates().get(i).equals(startState)){
													SDLNetwork.getStates().get(i).addConnection(new Connection(startState, endState, signal, actions));
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	public GuiAction getNextGuiAction(GuiConnection gc){
		for(GuiAction ga : guiNetwork.getGuiActions()){
			if(ga.intersects(gc.getX2() - 20, gc.getY2() - 20, 40, 40)){
				lastAction = ga;
				return ga;
			}
		}
		
		return new GuiAction("null");
	}
}
