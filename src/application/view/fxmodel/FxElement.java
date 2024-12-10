package view.fxmodel;

import common.ModelChangeObserver;

import java.util.List;

import common.Color;
import common.Point;
import model.Element;

public interface FxElement extends ModelChangeObserver {
	void setP(Point p);
	void setQ(Point q);
	void setColor(Color color);
	void setOpacity(double opacity);
	
	default void onRemove(Element element) {
		
	}
	
	default void removeSelfFrom(List<FxElement> elements) {
		elements.remove(this);
	}
}