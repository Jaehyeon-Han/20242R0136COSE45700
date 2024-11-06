package model;

import java.util.ArrayList;
import java.util.List;

import viewmodel.Observer;
import viewmodel.Visitor;

public class Element {
	protected double x1;
	protected double y1;
	protected double x2;
	protected double y2;
	protected Color color;
	private List<Observer> observers = new ArrayList<>();
	
	public Element(double x1, double y1, double x2, double y2) {
		this.x1 = Math.min(x1, x2);
		this.y1 = Math.min(y1, y2);
		this.x2 = Math.max(x1, x2);
		this.y2 = Math.max(y1, y2);
	}
	
	public Element() {
		// Do nothing
	}
	
	public double getX1() {
		return x1;
	}

	public double getY1() {
		return y1;
	}

	public double getX2() {
		return x2;
	}

	public double getY2() {
		return y2;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
		notifyObservers();
	}
	
	public double getWidth() {
		return Math.abs(x1 - x2);
	}
	
	public double getHeight() {
		return Math.abs(y1 - y2);
	}
	
	public void addObserver(Observer observer) {
		observers.add(observer);
	}
	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}
	
	public void notifyObservers() {
		for(Observer observer: observers) {
			observer.onChanged(this);
		}
	}
	
	
	public void accept(Visitor visitor) {
		visitor.handleLeaf(this);
	}
	
	public void move(double dx, double dy) {
		x1 += dx;
		y1 += dy;
		x2 += dx;
		y2 += dy;
		notifyObservers();
	}
	
	public void resize(double width, double height) {
		x2 = x1 + width;
		y2 = y1 + height;
		notifyObservers();
	}
	
//	public String toString() {
//		return "x1: " + x1 + ", y1: " + y1 + ", x2: " + x2 + ", y2: " + y2; 
//	}
	
	public boolean isInHere(double x, double y) {
		System.out.println(toString());
		return Math.min(x1, x2) <= x && x <= Math.max(x1, x2)
				&& Math.min(y1, y2) <= y && y <= Math.max(y1, y2);
	}
	
	public boolean intersects(double x1, double y1, double x2, double y2) {
		return !(x1 > this.x2 || 
                 x2 < this.x1 || 
                 y1 > this.y2 || 
                 y2 < this.y1);
    }
}
