package view.fxmodel;

import common.Color;
import common.Point;
import common.PropertyDTO;
import model.Element;

public class FxEllipse extends javafx.scene.shape.Ellipse implements FxElement {
	private Point topLeft, bottomRight;
	
	public FxEllipse(Point p, Point q, Color color) {
		super((p.getX() + q.getX()) / 2, (p.getY() + q.getY()) / 2, 
				(q.getX() - p.getX()) / 2, (q.getY() - p.getY()) / 2);
		this.topLeft = p;
		this.bottomRight = q;
		this.setColor(color);
	}
	
	@Override
	public void setP(Point p) {
		this.topLeft = p;
		update();
	}

	@Override
	public void setQ(Point q) {
		this.bottomRight = q;
		update();
	}
	
	public Point getP() {
		return this.topLeft;
	}
	
	public Point getQ() {
		return this.bottomRight;
	}
	
	private void update() {
		setWidth(bottomRight.getX() - topLeft.getX());
		setHeight(bottomRight.getY() - topLeft.getY());
		setX(topLeft.getX());
		setY(topLeft.getY());
	}
	
	private void setX(double x) {
		this.setCenterX(x + this.getRadiusX());
	}
	
	private void setY(double y) {
		this.setCenterY(y + this.getRadiusY());
	}

	private void setWidth(double width) {
		setRadiusX(width / 2);
	}

	private void setHeight(double height) {
		setRadiusY(height / 2);
	}

	@Override
	public void setColor(Color color) {
		setFill(color.toFxColor());
	}

	@Override
	public void onChange(Element element) {
		setP(element.getP());
		setQ(element.getQ());
		setColor(element.getColor());
	}	
}
