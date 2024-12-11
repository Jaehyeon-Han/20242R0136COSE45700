package view.fxmodel;

import common.Color;
import common.Point;
import common.PropertyDTO;
import javafx.scene.Node;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import model.Element;

public class FxLine extends FxElement {
	private Line fxLine;
	
	public FxLine(String id, Point p, Point q, Color color) {
		super(id);
		fxLine = new Line(p.getX(), p.getY(), q.getX(), q.getY());
		this.setColor(color);
	}

	@Override
	public void setP(Point p) {
		fxLine.setStartX(p.getX());
		fxLine.setStartY(p.getY());
	}

	@Override
	public void setQ(Point q) {
		fxLine.setEndX(q.getX());
		fxLine.setEndY(q.getY());
	}

	@Override
	public void setColor(Color color) {
		fxLine.setStroke(color.toFxColor());
	}

	@Override
	public void onChange(Element element) {
		setP(element.getP());
		setQ(element.getQ());
		setColor(element.getColor());
	}

	@Override
	void setOpacity(double opacity) {
	}

	@Override
	public Node getNode() {
		return fxLine;
	}	
}
