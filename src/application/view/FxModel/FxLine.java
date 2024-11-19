package view.FxModel;

import common.Color;
import common.Point;

public class FxLine extends javafx.scene.shape.Line implements FxElement {
	public FxLine(Point p, Point q, Color color) {
		super(p.getX(), p.getY(), q.getX(), q.getY());
		this.setColor(color);
	}

	@Override
	public void setP(Point p) {
		setStartX(p.getX());
		setStartY(p.getY());
	}

	@Override
	public void setQ(Point q) {
		setEndX(q.getX());
		setEndY(q.getY());
	}

	@Override
	public void setColor(Color color) {
		setStroke(color.toFxColor());
	}
}
