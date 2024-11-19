package view.FxModel;

import common.Color;
import common.Point;

public class FxRectangle extends javafx.scene.shape.Rectangle implements FxElement {
	public FxRectangle(Point p, Point q, Color color) {
		super(p.getX(), p.getY(), q.getX() - p.getX(), q.getY() - p.getY());
		this.setColor(color);
	}

	@Override
	public void setColor(Color color) {
		setFill(color.toFxColor());
	}
}
