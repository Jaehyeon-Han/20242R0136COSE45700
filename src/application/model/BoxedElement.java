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
		
		p = new Point(smallX, smallY);
		q = new Point(largeX, largeY);
	}
	
	public double getWidth() {
		return q.getX() - p.getX();
	}

	public void setWidth(double width) {
		if(width <= 0) {
			return;
		}
		
		q.setX(p.getX() + width);
		updateMatchingNode();
	}

	public double getHeight() {
		return q.getY() - p.getY();
	}

	public void setHeight(double height) {
		if(height <= 0) {
			return;
		}
		
		q.setY(p.getY() + height);
		updateMatchingNode();
	}
}
