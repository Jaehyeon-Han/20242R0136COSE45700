package view.fxmodel;

import common.Color;
import common.Point;
import javafx.scene.Node;
import javafx.scene.shape.Line;
import model.Element;

public class FxLine extends FxElement {
	private Line fxLine;
	private Color color;
	
	public FxLine(String id, Point p, Point q, Color color) {
		super(id);
		
		fxLine = new Line(p.getX(), p.getY(), q.getX(), q.getY());
		this.setColor(color);
	}

	// Setters
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
		this.color = color;
		fxLine.setStroke(color.toFxColor());
	}

	// Observer
	@Override
	public void onChange(Element element) {
		setP(element.getP());
		setQ(element.getQ());
		setColor(element.getColor());
	}

	@Override
	public void highlight() {
		fxLine.setStroke(javafx.scene.paint.Color.BLUE);
	}
	
	@Override
	public void unHighlight() {
		fxLine.setStroke(color.toFxColor());
	}

	@Override
	public Node getNode() {
		return fxLine;
	}
}
