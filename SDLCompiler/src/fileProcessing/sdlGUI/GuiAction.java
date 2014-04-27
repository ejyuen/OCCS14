package fileProcessing.sdlGUI;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

public class GuiAction extends Polygon implements GuiObject{

	private String name;
	private Color color = Color.BLUE;
	
	public GuiAction(String name, int xpoints[], int ypoints[]){
		super(xpoints, ypoints, 5);
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public String returnType(){
		return this.getClass().getName();
	}
	
	public void paint(Graphics2D g){
		g.setColor(color);
		g.draw(this);
	}
	
	public String toString(){
		return getName();
	}
}
