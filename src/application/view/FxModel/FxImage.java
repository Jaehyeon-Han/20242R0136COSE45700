package view.FxModel;

import java.io.File;

import common.Color;
import common.Point;

public class FxImage extends javafx.scene.image.ImageView implements FxElement {
	public FxImage(Point p, Point q, File imageFile) {
		setX(p.getX());
		setY(p.getY());
		setWidth(q.getX() - p.getX());
		setHeight(q.getY() - p.getY());
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
