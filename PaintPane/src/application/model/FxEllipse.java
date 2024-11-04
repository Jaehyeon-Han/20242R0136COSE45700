package model;

public class FxEllipse extends javafx.scene.shape.Ellipse implements FxNode {

	public FxEllipse(double centerX, double centerY, double rx, double ry) {
		super(centerX, centerY, rx, ry);
	}
	
	@Override
	public void setX(double x) {
		this.setCenterX(x + this.getRadiusX());
	}
	
	@Override
	public void setY(double y) {
		this.setCenterY(y + this.getRadiusY());
	}

	@Override
	public void setWidth(double width) {
		setRadiusX(width / 2);
	}

	@Override
	public void setHeight(double height) {
		setRadiusY(height / 2);
	}

	@Override
	public void setColor(Color color) {
		setFill(color.toFxColor());
	}
}
