package model;

import common.Color;
import common.ModelChangeObserver;
import common.Point;
import common.PropertyDTO;

public abstract class Element {
	protected Point p, q;

	protected Color color = new Color(0, 0, 0); // Image는 색이 없으나 공통 상위 클래스 편의성을 위해
	protected String id;
	protected ModelChangeObserver matchingNode;
	
	public Element(String id, Point p, Point q) {
		this.id = id;
		this.p = p;
		this.q = q;
	}
	
	public Point getP() {
		return p;
	}
	
	public void setP(Point p) {
		if(p.getX() < 0 || p.getY() < 0) {
			return;
		}
		this.p = p;
		updateMatchingNode();
	}

	public Point getQ() {
		return q;
	}
	
	public void setQ(Point q) {
		if(q.getX() < 0 || q.getY() < 0) {
			return;
		}
		this.q = q;
		updateMatchingNode();
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
		updateMatchingNode();
	}
	
	public String getId() {
		return this.id;
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
		updateMatchingNode();
	}
	
	public void setMatchingNode(ModelChangeObserver matchingNode) {
		this.matchingNode = matchingNode;
	}
	
	public void updateMatchingNode() {
		matchingNode.onChange(this);
	}
	
	abstract public double getWidth();
	abstract public void setWidth(double width);
	abstract public double getHeight();
	abstract public void setHeight(double height);
}
