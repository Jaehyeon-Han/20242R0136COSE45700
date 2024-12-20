package view.fxmodel;

import common.Color;
import common.Point;
import javafx.scene.Node;
import observer.ElementObserver;

public abstract class FxElement implements ElementObserver {
	private final String id;
	
	protected FxElement(String id) {
		this.id = id;
	}
	
	abstract void setP(Point p);
	abstract void setQ(Point q);
	abstract void setColor(Color color);
	
	public String getId() {
		return id;
	}
	
	public abstract Node getNode();

	abstract public void unHighlight();
	abstract public void highlight();
}