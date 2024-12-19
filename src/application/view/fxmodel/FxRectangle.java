package view.fxmodel;

import common.Color;
import common.Point;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import model.Element;

public class FxRectangle extends FxElement {
	private Rectangle fxRectangle;
	private Point topLeft, bottomRight;
	private Color color;
	
	public FxRectangle(String id, Point p, Point q, Color color) {
		super(id);
		this.topLeft = p;
		this.bottomRight = q;
		this.color = color;
		
		fxRectangle = new Rectangle(p.getX(), p.getY(), q.getX() - p.getX(), q.getY() - p.getY());
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
	public void highlight() {
		fxRectangle.setOpacity(0.7);
		fxRectangle.setStroke(javafx.scene.paint.Color.BLUE);
	}
	
	@Override
	public void unHighlight() {
		fxRectangle.setOpacity(1);
		fxRectangle.setStroke(javafx.scene.paint.Color.TRANSPARENT);
	}

	@Override
	public Node getNode() {
		return fxRectangle;
	}	
}
