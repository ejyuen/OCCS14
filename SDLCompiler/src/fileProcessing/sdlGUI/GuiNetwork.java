package fileProcessing.sdlGUI;

import java.util.ArrayList;

import sdlNetwork.Action;
import sdlNetwork.Connection;
import sdlNetwork.Signal;
import sdlNetwork.State;

public class GuiNetwork {

	ArrayList<GuiState> guiStates = new ArrayList<GuiState>();
	ArrayList<GuiConnection> guiConnections = new ArrayList<GuiConnection>();
	ArrayList<GuiAction> guiActions = new ArrayList<GuiAction>();
	ArrayList<GuiSignal> guiSignals = new ArrayList<GuiSignal>();
	
	public void addGuiAction(GuiAction a){
		guiActions.add(a);
	}
	
	public void addGuiSignal(GuiSignal s){
		guiSignals.add(s);
	}
	
	public void addGuiState(GuiState s){
		guiStates.add(s);
	}
	
	public void addGuiConnection(GuiConnection c){
		guiConnections.add(c);
	}
	
	public ArrayList<GuiAction> getGuiActions(){
		return guiActions;
	}
	
	public ArrayList<GuiSignal> getGuiSignals(){
		return guiSignals;
	}
	
	public ArrayList<GuiState> getGuiStates(){
		return guiStates;
	}
	
	public ArrayList<GuiConnection> getGuiConnections(){
		return guiConnections;
	}
	
}
