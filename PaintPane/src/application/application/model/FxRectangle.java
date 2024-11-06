package model;

public class FxRectangle extends javafx.scene.shape.Rectangle implements FxNode {
	public FxRectangle(double x, double y, double width, double height) {
		super(x, y, width, height);
	}

	@Override
	public void setColor(Color color) {
		setFill(color.toFxColor());
	}
	
	
}
