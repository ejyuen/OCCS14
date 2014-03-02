package fileProcessing.sdlGUI;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JFrame;

public class GuiNetwork extends JFrame{

	private ArrayList<GuiState> guiStates = new ArrayList<GuiState>();
	private ArrayList<GuiConnection> guiConnections = new ArrayList<GuiConnection>();
	private ArrayList<GuiAction> guiActions = new ArrayList<GuiAction>();
	private ArrayList<GuiSignal> guiSignals = new ArrayList<GuiSignal>();
	
	private Color backgroundColor = Color.WHITE;
	
	public GuiNetwork(){
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(backgroundColor);
	}
	
	public void addGuiAction(GuiAction a){
		guiActions.add(a);
	}
	
	public void addGuiConnection(GuiConnection c){
		guiConnections.add(c);
	}
	
	public void addGuiSignal(GuiSignal s){
		guiSignals.add(s);
	}
	
	public void addGuiState(GuiState s){
		guiStates.add(s);
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
	
	public void paint(Graphics2D g){
		//paint actions
		for(GuiAction a: guiActions){
			a.paint(g);
		}
		
		//paint connections
		for(GuiConnection c: guiConnections){
			c.paint(g);
		}
		
		//paint signals
		for(GuiSignal s: guiSignals){
			s.paint(g);
		}
		
		//paint states
		for(GuiState s: guiStates){
			s.paint(g);
		}
	}
	
	public static void paintNetwork(GuiNetwork gn){
		gn.setVisible(true);
		gn.repaint();
	}
}