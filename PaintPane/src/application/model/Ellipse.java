package model;

public class Ellipse extends Shape {
	public Ellipse(Point a, Point b, String color) {
		double x1, x2, y1, y2;
		if(a.getX() > b.getX()) {
			x1 = b.getX();
			x2 = a.getX();
		}
		else {
			x1 = a.getX();
			x2 = b.getX();
		}
		
		if(a.getY() > b.getY()) {
			y1 = b.getY();
			y2 = a.getY();
		}
		else {
			y1 = a.getY();
			y2 = b.getY();
		}
		
		this.topLeft = new Point(x1, y1);
		this.bottomRight = new Point(x2, y2);
		this.color = color;
	}
}
