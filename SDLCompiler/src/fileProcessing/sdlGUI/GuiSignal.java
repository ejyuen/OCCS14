package fileProcessing.sdlGUI;
import java.awt.*;

public class GuiSignal extends Polygon {

	private String name;
	private Color color = Color.RED;
	
	
	public GuiSignal(String name, int[] xpoints, int[] ypoints){
		super(xpoints, ypoints, 5);
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
