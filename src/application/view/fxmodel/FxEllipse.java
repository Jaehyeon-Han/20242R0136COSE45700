package view.fxmodel;

import common.Color;
import common.Point;
import common.ModelInfo;
import javafx.scene.Node;
import javafx.scene.shape.Ellipse;
import model.Element;

public class FxEllipse extends FxElement {
	private Ellipse fxEllipse;
	private Point topLeft, bottomRight;
	private Color color;
	
	public FxEllipse(String id, Point p, Point q, Color color) {
		super(id);
		this.topLeft = p;
		this.bottomRight = q;
		this.color = color;
		
		fxEllipse = new Ellipse((p.getX() + q.getX()) / 2, (p.getY() + q.getY()) / 2, 
				(q.getX() - p.getX()) / 2, (q.getY() - p.getY()) / 2);
		this.setColor(color);
	}
	
	// Setters and Getters
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
	
	// Updating The Real FxObject
	private void update() {
		setWidth(bottomRight.getX() - topLeft.getX());
		setHeight(bottomRight.getY() - topLeft.getY());
		setX(topLeft.getX());
		setY(topLeft.getY());
	}
	
	private void setX(double x) {
		fxEllipse.setCenterX(x + fxEllipse.getRadiusX());
	}
	
	private void setY(double y) {
		fxEllipse.setCenterY(y + fxEllipse.getRadiusY());
	}

	private void setWidth(double width) {
		fxEllipse.setRadiusX(width / 2);
	}

	private void setHeight(double height) {
		fxEllipse.setRadiusY(height / 2);
	}

	@Override
	public void setColor(Color color) {
		fxEllipse.setFill(color.toFxColor());
	}

	// Observing Matching Model Instance
	@Override
	public void onChange(Element element) {
		setP(element.getP());
		setQ(element.getQ());
		setColor(element.getColor());
	}

//	@Override
//	void setOpacity(double opacity) {
//		fxEllipse.setOpacity(opacity);
//	}
	
	@Override
	public void highlight() {
		fxEllipse.setOpacity(0.7);
		fxEllipse.setStroke(javafx.scene.paint.Color.BLUE);
	}
	
	@Override
	public void unHighlight() {
		fxEllipse.setOpacity(1);
		fxEllipse.setStroke(javafx.scene.paint.Color.TRANSPARENT);
	}

	@Override
	public Node getNode() {
		return fxEllipse;
	}
}
