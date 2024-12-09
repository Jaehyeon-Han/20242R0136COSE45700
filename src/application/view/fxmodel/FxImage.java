package view.fxmodel;

import java.io.File;

import common.Color;
import common.Point;
import common.PropertyDTO;

public class FxImage extends javafx.scene.image.ImageView implements FxElement {
	private Point topLeft, bottomRight;
	
	public FxImage(Point p, Point q, File imageFile) {
		setX(p.getX());
		setY(p.getY());
		setWidth(q.getX() - p.getX());
		setHeight(q.getY() - p.getY());
		setImage(new javafx.scene.image.Image(imageFile.toURI().toString()));
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
	
	private void update() {
		setWidth(bottomRight.getX() - topLeft.getX());
		setHeight(bottomRight.getY() - topLeft.getY());
		setX(topLeft.getX());
		setY(topLeft.getY());
	}

	public Point getP() {
		return this.topLeft;
	}
	
	public Point getQ() {
		return this.bottomRight;
	}
	
	private void setWidth(double width) {
		setFitWidth(width);
	}

	private void setHeight(double height) {
		setFitHeight(height);
	}

	@Override
	public void setColor(Color color) {
		// Do nothing
	}

	@Override
	public void onChange(PropertyDTO dto) {
		setP(dto.getP());
		setQ(dto.getQ());
		setColor(dto.getColor());
	}
}
