package view.FxModel;

import common.Color;
import common.Point;

public class FxText extends javafx.scene.text.Text implements FxElement {
	public FxText(Point p, Point q, Color color, String text) {
		setX(p.getX());
		setY(p.getY());
		setColor(color);
		setText(text);
	}

	@Override
	public void setWidth(double width) {
		// Do nothing
	}

	@Override
	public void setHeight(double height) {
		// Do nothing
	}

	@Override
	public void setColor(Color color) {
		setFill(color.toFxColor());
	}
	
}
