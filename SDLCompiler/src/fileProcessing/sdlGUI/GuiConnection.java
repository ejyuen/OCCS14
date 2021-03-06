package fileProcessing.sdlGUI;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

public class GuiConnection extends Line2D.Double implements GuiObject {
	private Color color = Color.BLACK;
	public GuiConnection(double x1, double y1, double x2, double y2){
		super(x1, y1, x2, y2);
	}
	public void paint(Graphics2D g){
		g.setColor(color);
		g.draw(this);	
	}
	public String returnType(){
		return this.getClass().getName();
	}
	public String toString(){
		return this.getX1() + "," + this.getY1() + " to " + this.getX2() + "," + this.getY2();
	}
}
