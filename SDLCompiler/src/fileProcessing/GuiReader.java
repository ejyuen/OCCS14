package fileProcessing;

import java.awt.geom.Rectangle2D;

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
		for(GuiState gs : guiNetwork.getGuiStates()) {
			if(gs.getName() == "Start"){
				SDLNetwork.addStartState(new State(gs.getName()));
			}
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
					for(GuiSignal gsl : guiNetwork.getGuiSignals()){
						if(gsl.intersects(gc.getX2() - 20, gc.getY2() - 20 , 40, 40)){
							signal = new Signal(gsl.getName());
							for(GuiConnection gc2 : guiNetwork.getGuiConnections()){
								if(gsl.intersects(gc2.getX1() - 20, gc.getY1() - 20, 40, 40)){
									for(GuiAction ga : guiNetwork.getGuiActions()){
										if(ga.intersects(gc2.getX2() - 20, gc2.getY2() - 20, 40, 40)){
											action = new Action(ga.getName());
											for(GuiConnection gc3 : guiNetwork.getGuiConnections()){
												if(ga.intersects(gc3.getX1() - 20, gc3.getY1() - 20, 40, 40)){
													for(GuiState gs2 : guiNetwork.getGuiStates()){
														if(gs2.intersects(gc3.getX2() - 20, gc3.getY2() - 20, 40, 40)){
															endState = new State(gs2.getName());
															for(State s : SDLNetwork.getStates()){
																if(s.equals(startState)){
																	s.addConnection(new Connection(startState, endState, signal, action));
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
				}
			}
		}
	}
}
