package fileProcessing.sdlGUI;
import java.awt.*;

public class Signal extends Polygon {

	private String name;
	
	public Signal(String n, int x1, int x2, int y1, int y2, int xmid, int ymid){
		super.xpoints[0] = x1; super.xpoints[1] = x2; super.xpoints[2] = xmid;
		super.ypoints[0] = y1; super.ypoints[1] = y2; super.ypoints[2] = ymid;
		super.npoints = 5;
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
	
	public int getx3(){
		return xpoints[2];
	}
	
	public int gety1(){
		return ypoints[0];
	}
	
	public int gety2(){
		return ypoints[1];
	}
	
	public int gety3(){
		return ypoints[2];
	}

}
