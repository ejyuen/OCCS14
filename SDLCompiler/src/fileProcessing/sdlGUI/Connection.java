package fileProcessing.sdlGUI;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Connection extends Line2D {

	double x1; double x2; double y1; double y2;
	
	public Connection(int ix1, int ix2, int iy1, int iy2){
		x1=ix1;
		x2=ix2;
		y1=iy1;
		y2=iy2;
	}
	
	@Override
	public Rectangle2D getBounds2D() {
		return null;
	}

	@Override
	public double getX1() {
		return x1;
	}

	@Override
	public double getY1() {
		return y1;
	}

	@Override
	public double getX2() {
		// TODO Auto-generated method stub
		return x2;
	}

	@Override
	public double getY2() {
		return y2;
	}

	@Override
	public Point2D getP2() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLine(double x1, double y1, double x2, double y2) {
		// TODO Auto-generated method stub
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}

	@Override
	public Point2D getP1() {
		// TODO Auto-generated method stub
		return null;
	}

}
