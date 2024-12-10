package view.fxmodel;

import common.Color;
import common.Point;
import common.PropertyDTO;
import model.Element;

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

	@Override
	public void onChange(Element element) {
		setP(element.getP());
		setQ(element.getQ());
		setColor(element.getColor());
	}	
}
