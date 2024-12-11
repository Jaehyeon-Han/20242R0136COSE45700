package view.fxmodel;

import java.util.ArrayList;
import java.util.List;

import common.Color;
import common.Point;
import common.PropertyDTO;
import javafx.scene.Node;
import model.Element;

public class FxComposite extends FxElement {
	List<FxElement> children = new ArrayList<FxElement>();
	
	public FxComposite(String id) {
		super(id);
	}
	
	public void addChild(FxElement fxElement) {
		children.add(fxElement);
	}

	public void removeChild(FxElement fxElement) {
		children.remove(fxElement);
	}
	
	// Not used
	@Override
	public void onChange(Element element) {
	}

	@Override
	void setP(Point p) {
	}

	@Override
	void setQ(Point q) {
	}

	@Override
	void setColor(Color color) {
	}

	@Override
	void setOpacity(double opacity) {
		for(FxElement fxElement: children) {
			fxElement.setOpacity(opacity);
		}
	}

	@Override
	public Node getNode() {
		return null;
	}
}
