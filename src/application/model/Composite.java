package model;

import java.util.ArrayList;
import java.util.List;

import common.Color;
import common.Point;
import common.ModelInfo;

public class Composite extends BoxedElement {
	List<Element> children = new ArrayList<Element>();
	private static final double max_val = 99999;
	private boolean isFirstColor = true;
	private boolean isColorMixed = false;
	
	public Composite(String id) {
		super(id, new Point(max_val, max_val), new Point(0, 0));
		initializePQ();
	}
	
	@Override
	public void setColor(Color color) {
		for(Element element : children) {
			element.setColor(color);
		}
		this.color = color;
		notifyChange();
	}
	
	private void updateColor(Element element) {
		if(isFirstColor) {
			this.color = element.color;
			isFirstColor = false;
		} else {
			if(!element.getColor().equals(this.color)) {
				this.color = new Color(0, 0, 0);
				isColorMixed = true;
			}
		}
	}
	
	@Override
	public void translate(double dx, double dy) {
		for(Element element : children) {
			element.translate(dx, dy);
		}
		p.setX(p.getX() + dx);
		p.setY(p.getY() + dy);
		q.setX(q.getX() + dx);
		q.setY(q.getY() + dy);
		notifyChange();
	}
	
	public void setWidth(double width) {
		if(width <= 0) {
			return;
		}
		
		q.setX(p.getX() + width);
		resize(width, getHeight());
	}

	public void setHeight(double height) {
		if(height <= 0) {
			return;
		}
		
		q.setY(p.getY() + height);
		resize(getWidth(), height);
	}
	
	public void resize(double width, double height) {
		setQ(new Point(p.getX() + width, p.getY() + height));
		notifyChange();
	}
	
	public void setQ(Point q) {
		if(q.getX() < 0 || q.getY() < 0) {
			return;
		}
		double originalWidth = getWidth();
		double originalHeight = getHeight();
		this.q = q;
		adjustChildren(originalWidth, originalHeight);
		notifyChange();
	}
	
	private void adjustChildren(double originalWidth, double originalHeight) {
		double x1 = p.getX(), y1 = p.getY();
		double width = getWidth(), height = getHeight();
		
		double xWeight = width / originalWidth;
		double yWeight = height / originalHeight;
		
		for(Element child : children) {
			double relativeX = (child.getP().getX() - x1) / originalWidth;
			double relativeY = (child.getP().getY() - y1) / originalHeight;
			double targetX1 = x1 + relativeX * width;
			double targetY1 = y1 + relativeY * height;
			child.translate(targetX1 - child.getP().getX(), targetY1 - child.getP().getY());
			
			double childWidth = child.getQ().getX() - child.getP().getX();
			double childHeight = child.getQ().getY() - child.getP().getY();
			child.setWidth(childWidth * xWeight);
			child.setHeight(childHeight * yWeight);
		}
	}

	private void updateBound(Element element) {
		p.setX(Math.min(p.getX(), element.p.getX()));
		p.setY(Math.min(p.getY(), element.p.getY()));
		q.setX(Math.max(q.getX(), element.q.getX()));
		q.setY(Math.max(q.getY(), element.q.getY()));
	}
	
	private void recalculateBound() {
		initializePQ();
		for(Element element : children) {
			updateBound(element);
		}
	}

	private void initializePQ() {
		p.setX(max_val);
		p.setY(max_val);
		q.setX(0);
		q.setY(0);
	}
	
	public void addChild(Element element) {
		updateBound(element);
		if(!isColorMixed) {
			updateColor(element);			
		}
		children.add(element);
	}
	
	public void removeChild(Element element) {
		double x1 = p.getX(), y1 = p.getY();
		double x2 = q.getX(), y2 = q.getY();
		
		children.remove(element);
		if(element.getP().getX() == x1 || element.getP().getY() == y1 || 
				element.getQ().getX() == x2 || element.getQ().getY() == y2) {
			recalculateBound();
		}
	}
	
	public List<Element> getChildren() {
		return this.children;
	}

	@Override
	public ModelInfo getModelInfo() {
		return null;
	}
}
