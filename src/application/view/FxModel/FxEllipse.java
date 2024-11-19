package view.FxModel;

import common.Color;
import common.Point;

public class FxEllipse extends javafx.scene.shape.Ellipse implements FxElement {

	public FxEllipse(Point p, Point q, Color color) {
		super((p.getX() + q.getX()) / 2, (p.getY() + q.getY()) / 2, 
				(q.getX() - p.getX()) / 2, (q.getY() - p.getY()) / 2);
	}
	
	@Override
	public void setX(double x) {
		this.setCenterX(x + this.getRadiusX());
	}
	
	@Override
	public void setY(double y) {
		this.setCenterY(y + this.getRadiusY());
	}

	@Override
	public void setWidth(double width) {
		setRadiusX(width / 2);
	}

	@Override
	public void setHeight(double height) {
		setRadiusY(height / 2);
	}

	@Override
	public void setColor(Color color) {
		setFill(color.toFxColor());
	}
}
