package model;

import java.util.ArrayList;
import java.util.List;

import viewmodel.Visitor;

public class Composite extends Element {
	List<Element> children = new ArrayList<Element>();
	private static final double max_val = 99999;
	
	public void add(Element element) {
		x1 = Math.min(x1, element.x1);
		y1 = Math.min(y1, element.y1);
		x2 = Math.max(x2, element.x2);
		y2 = Math.max(y2, element.y2);
		children.add(element);
	}
//	public void remove(Element element) {
//		children.remove(element);
//		if(element.x1 == x1 || element.y1 == y1 || element.x2 == x2 || element.y2 == y2) {
//			update();
//		}
//	}
	public List<Element> getChildren() {
		return this.children;
	}
	
	public Composite() {
		super.x1 = max_val;
		super.y1 = max_val;
		this.color = new Color(0, 0, 0);
	}
	
	@Override
	public void move(double dx, double dy) {
		for(Element element : children) {
			element.move(dx, dy);
		}
		x1 += dx;
		y1 += dy;
		x2 += dx;
		y2 += dy;
		notifyObservers();
	}
	@Override
	public void setColor(Color color) {
		for(Element element : children) {
			element.setColor(color);
		}
	}
	@Override
	public void resize(double width, double height) {
		double originalWidth = x2 - x1;
		double originalHeight = y2 - y1;
		double xWeight = width / originalWidth;
		double yWeight = height / originalHeight;
		
		for(Element child : children) {
			double relativeX = (child.getX1() - x1) / originalWidth;
			double relativeY = (child.getY1() - y1) / originalHeight;
			double targetX1 = x1 + relativeX * width;
			double targetY1 = y1 + relativeY * height;
			child.move(targetX1 - child.getX1(), targetY1 - child.getY1());
			
			double childWidth = child.getX2() - child.getX1();
			double childHeight = child.getY2() - child.getY1();
			child.resize(childWidth * xWeight, childHeight * yWeight);
		}
		
		x2 = x1 + width;
		y2 = y1 + height;
		notifyObservers();
	}
//	private void update() {
//		for(Element element : children) {
//			x1 = Math.min(x1, element.x1);
//			y1 = Math.min(y1, element.y1);
//			x2 = Math.max(x2, element.x2);
//			y2 = Math.max(y2, element.y2);
//		}
//		notifyObservers();
//	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.handleComposite(this);
	}
}
