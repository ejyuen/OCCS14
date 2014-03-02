package fileProcessing.sdlGUI;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

//Important to note, Rectangle2D's contain's method does not Point parameters, you must use ints (xcoord, ycoord)
public class GuiState extends Rectangle2D.Double {

	private String name;
	private Color color = Color.BLACK;
	
	public GuiState(String name, double x, double y, double w, double h){
		super(x, y, w, h);
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void paint(Graphics2D g){
		g.setColor(color);
		g.draw(this);
	}
}
