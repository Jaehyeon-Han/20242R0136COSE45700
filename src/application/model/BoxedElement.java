package model;

import common.Point;

abstract public class BoxedElement extends Element {
	protected BoxedElement(String id, Point p, Point q) {
		super(id, p, q);
		setTopLeftAndBottomRight();
	}
	
	private void setTopLeftAndBottomRight() {
		double smallX = Math.min(p.getX(), q.getX()), 
				largeX = Math.max(p.getX(), q.getX());
		double smallY = Math.min(p.getY(), q.getY()), 
				largeY = Math.max(p.getY(), q.getY());
		
		p.setX(smallX);
		p.setY(smallY);
		q.setX(largeX);
		q.setY(largeY);
	}
	
	// Getters and Setters
	@Override 
	public void setP(Point p) {
		this.p = p;
		setTopLeftAndBottomRight(); // Should guarantee that P is topLeft when modified
		notifyOnChange();
	}
	
	@Override
	public void setQ(Point q) {
		this.q = q;
		setTopLeftAndBottomRight(); // Should guarantee that P is topLeft when modified
		notifyOnChange();
	} 

	public double getWidth() {
		return q.getX() - p.getX();
	}

	public void setWidth(double width) {
		if(width <= 0) {
			return;
		}
		
		q.setX(p.getX() + width);
		notifyOnChange();
	}

	public double getHeight() {
		return q.getY() - p.getY();
	}

	public void setHeight(double height) {
		if(height <= 0) {
			return;
		}
		
		q.setY(p.getY() + height);
		notifyOnChange();
	}
	
	// Hit Test for Two Boxes
	@Override
	public boolean intersects(Point rectP1, Point rectP2) {
	    double x1 = Math.min(p.getX(), q.getX());
	    double y1 = Math.min(p.getY(), q.getY());
	    double x2 = Math.max(p.getX(), q.getX());
	    double y2 = Math.max(p.getY(), q.getY());

	    double rectX1 = Math.min(rectP1.getX(), rectP2.getX());
	    double rectY1 = Math.min(rectP1.getY(), rectP2.getY());
	    double rectX2 = Math.max(rectP1.getX(), rectP2.getX());
	    double rectY2 = Math.max(rectP1.getY(), rectP2.getY());

	    return !(rectX1 > x2 || 
	             rectX2 < x1 || 
	             rectY1 > y2 || 
	             rectY2 < y1);
	}
}
