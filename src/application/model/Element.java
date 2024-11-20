package model;

import common.Color;
import common.Point;
import common.PropertyDTO;

public abstract class Element {
	protected Point p, q;
	protected Color color; // Image는 색이 없으나 공통 상위 클래스 편의성을 위해
	
	public Element(Point p, Point q) {
		this.p = p;
		this.q = q;
		setTopLeftAndBottomRight();
	}
	
	public Element() {
		// For Line: Do nothing
	}
	
	private void setTopLeftAndBottomRight() {
		double smallX = Math.min(p.getX(), q.getX()), 
				largeX = Math.max(p.getX(), q.getX());
		double smallY = Math.min(p.getY(), q.getY()), 
				largeY = Math.max(p.getY(), q.getY());
		
		p = new Point(smallX, smallY);
		q = new Point(largeX, largeY);
	}
	
	public Point getP() {
		return p;
	}

	public Point getQ() {
		return q;
	}
	
	public void setQ(Point q) {
		this.q = q;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public abstract PropertyDTO toDTO();
	
	public boolean isInHere(Point r) {
		return Math.min(p.getX(), q.getX()) <= r.getX() && 
				r.getX() <= Math.max(p.getX(), q.getX()) && 
				Math.min(p.getY(), q.getY()) <= r.getY() && 
				r.getY() <= Math.max(p.getY(), q.getY());
	}

	public void translate(double dx, double dy) {
		p.setX(p.getX() + dx);
		p.setY(p.getY() + dy);
		q.setX(q.getX() + dx);
		q.setY(q.getY() + dy);
	}
}
