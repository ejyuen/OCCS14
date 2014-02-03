package utilities;

import java.awt.Polygon;

public class SDLObject extends Polygon{
	
	public String name;
	
	public SDLObject(int[] xpoints, int[]ypoints, String name, int npoints){
		super(xpoints, ypoints, npoints);
		this.name = name;
	}

}
