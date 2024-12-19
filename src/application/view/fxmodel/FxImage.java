package view.fxmodel;

import java.io.File;

import common.Color;
import common.Point;
import common.ModelInfo;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Element;

public class FxImage extends FxElement {
	private ImageView fxImage;
	private Point topLeft, bottomRight;
	
	public FxImage(String id, Point p, Point q, File imageFile) {
		super(id);
		this.topLeft = p;
		this.bottomRight = q;
		
		fxImage = new ImageView(imageFile.toURI().toString());
		fxImage.setX(p.getX());
		fxImage.setY(p.getY());
		setWidth(q.getX() - p.getX());
		setHeight(q.getY() - p.getY());
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
		fxImage.setX(topLeft.getX());
		fxImage.setY(topLeft.getY());
	}
	
	private void setWidth(double width) {
		fxImage.setFitWidth(width);
	}

	private void setHeight(double height) {
		fxImage.setFitHeight(height);
	}

	@Override
	public void setColor(Color color) {
		// Do nothing
	}

	@Override
	public void onChange(Element element) {
		setP(element.getP());
		setQ(element.getQ());
	}

	@Override
	public Node getNode() {
		return fxImage;
	}

	@Override
	public void highlight() {
		fxImage.setOpacity(0.7);
	}
	
	@Override
	public void unHighlight() {
		fxImage.setOpacity(1);
	}
}
