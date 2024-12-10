package view.fxmodel;

import common.Color;
import common.Point;
import common.PropertyDTO;
import model.Element;

public class FxText extends javafx.scene.text.Text implements FxElement {
	private Point topLeft, bottomRight;
	
	public FxText(Point p, Point q, Color color, String text) {
		setX(p.getX());
		setY(p.getY());
		setColor(color);
		setText(text);
		this.topLeft = p;
		this.bottomRight = q;
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

	public void setWidth(double width) {
		// Do nothing
	}

	public void setHeight(double height) {
		// Do nothing
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
