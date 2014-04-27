package fileProcessing.sdlGUI;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Color;

public class GuiDecision extends Polygon{

	public Color color = Color.GREEN;
	
	public GuiDecision(int[] xpoints, int[] ypoints){
		super(xpoints, ypoints, 4);
	}
	
	public void paint(Graphics2D g){
		g.setColor(color);
		g.draw(this);
	}
	
	public String returnType(){
		return this.getClass().getName();
	}
	
	public String toString(){
		return "Decision!";
	}
}
