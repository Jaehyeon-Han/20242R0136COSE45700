package view.fxmodel;

import common.Color;
import common.Point;
import common.PropertyDTO;
import javafx.scene.Node;
import javafx.scene.text.Text;
import model.Element;

public class FxText extends FxElement {
	private Text fxText;
	private Point topLeft, bottomRight;
	
	public FxText(String id, Point p, Point q, Color color, String text) {
		super(id);
		fxText = new Text(text);
		fxText.setX(p.getX());
		fxText.setY(p.getY());
		setColor(color);
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
		fxText.setX(topLeft.getX());
		fxText.setY(topLeft.getY());
	}

	public void setWidth(double width) {
		fxText.setWrappingWidth(width);
	}

	public void setHeight(double height) {
		// Do nothing
	}

	@Override
	public void setColor(Color color) {
		fxText.setFill(color.toFxColor());
	}

	@Override
	public void onChange(Element element) {
		setP(element.getP());
		setQ(element.getQ());
		setColor(element.getColor());
	}

	@Override
	void setOpacity(double opacity) {
		fxText.setOpacity(opacity);
	}

	@Override
	public Node getNode() {
		return fxText;
	}	
}
