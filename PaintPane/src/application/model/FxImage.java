package model;

import java.io.File;

public class FxImage extends javafx.scene.image.ImageView implements FxNode {
	public FxImage(double x1, double y1, double width, double height, File imageFile) {
		setX(x1);
		setY(y1);
		setFitWidth(width);
		setFitHeight(height);
		setImage(new javafx.scene.image.Image(imageFile.toURI().toString()));
	}
	
	@Override
	public void setWidth(double width) {
		setFitWidth(width);
	}

	@Override
	public void setHeight(double height) {
		setFitHeight(height);
	}

	@Override
	public void setColor(Color color) {
		// Do nothing
	}
	
}
