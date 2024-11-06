package model;

public class FxLine extends javafx.scene.shape.Line implements FxNode {
	public FxLine(double x1, double y1, double x2, double y2) {
		super(x1, y1, x2, y2);
	}

	@Override
	public void setWidth(double width) {
		setEndX(getStartX() + width);
	}

	@Override
	public void setHeight(double height) {
		setEndY(getStartY() + height);
	}

	@Override
	public void setColor(Color color) {
		setStroke(color.toFxColor());
	}

	@Override
	public void setX(double x) {
		double dx = getEndX() - getStartX();
		setStartX(x);
		setEndX(x+dx);
	}

	@Override
	public void setY(double y) {
		double dy = getEndY() - getStartY();
		setStartY(y);
		setEndY(y+dy);
	}
}
