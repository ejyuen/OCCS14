package fileProcessing.sdlGUI;

import java.awt.*;

public class GuiState extends Polygon {

	private String name;
	private Color color = Color.BLACK;
	
	public GuiState(String name, int xpoints[], int ypoints[]){
		super(xpoints, ypoints, 4);
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
