package view.fxmodel;

import java.util.ArrayList;
import java.util.List;

import common.Color;
import common.Point;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import model.Element;

public class FxComposite extends FxElement {
	private List<FxElement> children = new ArrayList<FxElement>();
	private Point topLeft, bottomRight;
	private Rectangle boundBox;
	
	public FxComposite(String id, Point p, Point q) {
		super(id);
		this.topLeft = p;
		this.bottomRight = q;
		this.boundBox = new Rectangle(p.getX(), p.getY(), 
				q.getX() - p.getX(), 
				q.getY() - p.getY());
		boundBox.setFill(javafx.scene.paint.Color.TRANSPARENT);
	}
	
	public void addChild(FxElement fxElement) {
		children.add(fxElement);
	}

	public void removeChild(FxElement fxElement) {
		children.remove(fxElement);
	}
	
	// Setters
	@Override
	public void setP(Point p) {
		this.topLeft = p;
	}

	@Override
	public void setQ(Point q) {
		this.bottomRight = q;
	}

	@Override
	void setColor(Color color) {
	} // Not used
	
	//Observer
	@Override
	public void onChange(Element element) {
		setP(element.getP());
		setQ(element.getQ());
		update();
	}

	private void update() {
		boundBox.setWidth(bottomRight.getX() - topLeft.getX());
		boundBox.setHeight(bottomRight.getY() - topLeft.getY());
		boundBox.setX(topLeft.getX());
		boundBox.setY(topLeft.getY());
	}

	@Override
	public void highlight() {
		boundBox.setOpacity(0.7);
		boundBox.setStroke(javafx.scene.paint.Color.BLUE);
	}
	
	@Override
	public void unHighlight() {
		boundBox.setOpacity(0);
		boundBox.setStroke(javafx.scene.paint.Color.TRANSPARENT);
	}

	@Override
	public Node getNode() {
		return boundBox;
	}
}
