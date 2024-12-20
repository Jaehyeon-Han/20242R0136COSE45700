package model;

import java.util.ArrayList;
import java.util.List;

import common.Color;
import common.Point;
import common.ModelInfo;
import observer.ElementObserver;
import observer.ElementSubject;

public abstract class Element implements ElementSubject {
	protected String id;
	protected Point p, q;
	protected Color color = new Color(0, 0, 0); // Image는 색이 없으나 공통 상위 클래스 편의성을 위해
	protected List<ElementObserver> observers = new ArrayList<>();
	
	public Element(String id, Point p, Point q) {
		this.id = id;
		this.p = p;
		this.q = q;
	}
	
	// Getters and Setters
	public String getId() {
		return this.id;
	}
	
	public Point getP() {
		return p;
	}	
	
	public Point getQ() {
		return q;
	}

	public Color getColor() {
		return color;
	}
	
	public void setP(Point p) {
		if(p.getX() < 0 || p.getY() < 0) {
			return;
		}
		this.p = p;
		notifyChange();
	}
	
	public void setQ(Point q) {
		if(q.getX() < 0 || q.getY() < 0) {
			return;
		}
		this.q = q;
		notifyChange();
	}

	public void setColor(Color color) {
		this.color = color;
		notifyChange();
	}
	
	abstract public double getWidth();
	abstract public void setWidth(double width);
	abstract public double getHeight();
	abstract public void setHeight(double height);
	
	// Basic Operations
	public void translate(double dx, double dy) {
		p.setX(p.getX() + dx);
		p.setY(p.getY() + dy);
		q.setX(q.getX() + dx);
		q.setY(q.getY() + dy);
		notifyChange();
	}
	
	// Hit test
	public boolean isHit(Point r) {
		return Math.min(p.getX(), q.getX()) <= r.getX() && 
				r.getX() <= Math.max(p.getX(), q.getX()) && 
				Math.min(p.getY(), q.getY()) <= r.getY() && 
				r.getY() <= Math.max(p.getY(), q.getY());
	}
	
	abstract public boolean intersects(Point rectP1, Point rectP2);
	
	// DTO conversion
	public abstract ModelInfo getModelInfo();
	
	// ModelChange Subject
	public void addObserver(ElementObserver observer) {
		observers.add(observer);
	}
	
	public void removeObserver(ElementObserver observer) {
		observers.remove(observer);
	}
	
	public void notifyChange() {
		for(ElementObserver observer: observers) {
			observer.onChange(this);
		}
	}
}
