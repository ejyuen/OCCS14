package fileProcessing.sdlGUI;

import java.awt.*;

public class State extends Polygon {

	private String name;
	
	public State(String n, int x1, int x2, int y1, int y2){
		super.xpoints[0] = x1; super.xpoints[1] = x2;
		super.ypoints[0] = y1; super.ypoints[1] = y2;
		super.npoints = 4;
		name = n;
	}
	
	public String getName(){
		return name;
	}
	
	public int getx1(){
		return xpoints[0];
	}
	
	public int getx2(){
		return xpoints[1];
	}
	
	public int gety1(){
		return ypoints[0];
	}
	
	public int gety2(){
		return ypoints[1];
	}
	
	/*public int[][] getCoordinates() {
		int[] coord1 = new int[2]; coord1[0] = xpoints[0]; coord1[1] = ypoints[0];
		int[] coord2 = new int[2]; coord2[0] = xpoints[0]; coord2[1] = ypoints[1];
		int[] coord3 = new int[2]; coord3[0] = xpoints[1]; coord3[1] = ypoints[0];
		int[] coord4 = new int[2]; coord4[0] = xpoints[1]; coord4[1] = ypoints[1];
		int[][] coords = new int[0][0];
		return coords;
	}*/
}
