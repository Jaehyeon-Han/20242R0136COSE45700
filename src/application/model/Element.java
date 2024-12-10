package model;

import java.util.List;

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
	
	public void removeSelfFrom(List<Element> elements) {
        elements.remove(this);
        removeMatchingNode();
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
	
	public void removeMatchingNode() {
		matchingNode.onRemove(this);
	}
	
	abstract public double getWidth();
	abstract public void setWidth(double width);
	abstract public double getHeight();
	abstract public void setHeight(double height);
	
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
