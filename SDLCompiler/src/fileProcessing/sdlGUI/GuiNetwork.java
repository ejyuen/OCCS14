package fileProcessing.sdlGUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GuiNetwork extends JFrame{

	private ArrayList<GuiState> guiStates = new ArrayList<GuiState>();
	private ArrayList<GuiConnection> guiConnections = new ArrayList<GuiConnection>();
	private ArrayList<GuiAction> guiActions = new ArrayList<GuiAction>();
	private ArrayList<GuiSignal> guiSignals = new ArrayList<GuiSignal>();
	private ArrayList<GuiDecision> guiDecisions = new ArrayList<GuiDecision>();
	private JPanel j = null;

	private Color backgroundColor = Color.WHITE;
	
	public GuiNetwork(){
		super("GUI Network");
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(backgroundColor);
		j = new NPanel();
		add(j);
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
	
	public void addGuiDecision(GuiDecision d){
		guiDecisions.add(d);
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
	
	public ArrayList<GuiDecision> getGuiDecisions(){
		return guiDecisions;
	}
	
	public static void paintNetwork(GuiNetwork gn){
		gn.setVisible(true);
	}
	
	public class NPanel extends JPanel{
		
		public void paintComponent(Graphics g){
			Graphics2D g2 = (Graphics2D) g;
			
			//paint actions
			for(GuiAction a: guiActions){
				a.paint(g2);
			}
			
			//paint connections
			for(GuiConnection c: guiConnections){
				c.paint(g2);
			}
			
			//paint signals
			for(GuiSignal s: guiSignals){
				s.paint(g2);
			}
			
			//paint states
			for(GuiState s: guiStates){
				s.paint(g2);
			}
			
			//paint decisions
			for(GuiDecision d : guiDecisions){
				d.paint(g2);
			}
		}
	}
}