package view.fxmodel;

import common.ModelChangeObserver;

import java.util.List;

import common.Color;
import common.Point;
import javafx.scene.Node;
import model.Element;

public abstract class FxElement implements ModelChangeObserver {
	private String id;
	
	protected FxElement(String id) {
		this.id = id;
	}
	
	abstract void setP(Point p);
	abstract void setQ(Point q);
	abstract void setColor(Color color);
	abstract void setOpacity(double opacity);
	
	public void onRemove() {
		this.remove();
	}
	
	private void remove() {
		FxElementManager.getInstance().remove(this);
	}
	
	@Override
	public void select() {
		this.setOpacity(0.7);
	}
	
	@Override
	public void unselect() {
		this.setOpacity(1);
	}
	
	public String getId() {
		return id;
	}
	
	public abstract Node getNode();
}