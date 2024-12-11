package view.fxmodel;

import common.Color;
import common.Point;
import common.PropertyDTO;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import model.Element;

public class FxRectangle extends FxElement {
	private Rectangle fxRectangle;
	private Point topLeft, bottomRight;
	
	public FxRectangle(String id, Point p, Point q, Color color) {
		super(id);
		fxRectangle = new Rectangle(p.getX(), p.getY(), q.getX() - p.getX(), q.getY() - p.getY());
		this.setColor(color);
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
		fxRectangle.setWidth(bottomRight.getX() - topLeft.getX());
		fxRectangle.setHeight(bottomRight.getY() - topLeft.getY());
		fxRectangle.setX(topLeft.getX());
		fxRectangle.setY(topLeft.getY());
	}

	@Override
	public void setColor(Color color) {
		fxRectangle.setFill(color.toFxColor());
	}
	

	@Override
	public void onChange(Element element) {
		setP(element.getP());
		setQ(element.getQ());
		setColor(element.getColor());
	}

	@Override
	void setOpacity(double opacity) {
		fxRectangle.setOpacity(opacity);
	}

	@Override
	public Node getNode() {
		return fxRectangle;
	}	
}
