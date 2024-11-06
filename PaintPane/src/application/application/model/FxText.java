package model;

public class FxText extends javafx.scene.text.Text implements FxNode {
	public FxText(double x1, double y1, String text) {
		setX(x1);
		setY(y1);
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
