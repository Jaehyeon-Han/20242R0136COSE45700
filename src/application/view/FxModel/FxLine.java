package view.FxModel;

import common.Color;
import common.Point;

public class FxLine extends javafx.scene.shape.Line implements FxElement {
	
	
	public FxLine(Point p, Point q, Color color) {
		super(p.getX(), p.getY(), q.getX(), q.getY());
		this.setColor(color);
	}

	@Override
	public void setWidth(double width) {
		// Do nothing
	}

	@Override
	public void setHeight(double height) {
		// Do nothing
	}

	@Override
	public void setColor(Color color) {
		setStroke(color.toFxColor());
	}

	@Override
	public void setX(double x) {
		double dx = getEndX() - getStartX();
		setStartX(x);
		setEndX(x+dx);
	}

	@Override
	public void setY(double y) {
		double dy = getEndY() - getStartY();
		setStartY(y);
		setEndY(y+dy);
	}
}
