package viewmodel;

import javafx.scene.Node;
import model.Element;
import model.FxNode;
import view.View;

public class ViewUpdater implements Observer {
	private static ViewUpdater instance = new ViewUpdater();
	public static ViewUpdater getInstance() {
		return instance;
	}
	
	private View view;
	public void setView(View view) {
		this.view = view;
	}

	@Override
	public void onChanged(Element selectedElement) {
		updateViewSelectedBox(selectedElement);
	}

	public void selectNode(FxNode node) {
		node.setOpacity(0.7);
	}
	public void unSelectNode(FxNode node) {
		node.setOpacity(1);
	}
	public void updatePropertyWindow(Element selectedElement) {
		if(selectedElement == null) {
			return;
		}
		
		view.updateX(selectedElement.getX1());
		view.updateY(selectedElement.getY1());
		view.updateWidth(selectedElement.getWidth());
		view.updateHeight(selectedElement.getHeight());
		if(selectedElement.getColor().toFxColor() != null) {
			view.updateColor(selectedElement.getColor().toFxColor());
		}
	}
	
	void updateViewSelectedBox(Element selectedElement) {
		view.detachSelectedBox();
		view.attachSelectedBox(selectedElement.getX1(), selectedElement.getY1(), 
				selectedElement.getWidth(), selectedElement.getHeight());
	}
	
	void select(Element selectedElement) {
		view.changeSelectStatus(true);
		updatePropertyWindow(selectedElement);
		view.attachSelectedBox(selectedElement.getX1(), selectedElement.getY1(), 
				selectedElement.getWidth(), selectedElement.getHeight());
	}
	void unselect() {
		view.changeSelectStatus(false);
		view.detachSelectedBox();
	}
	
	void addNode(FxNode node) {
		view.addNode((Node) node);
	}
	
	void reOrder(FxNode node, int index) {
		view.reOrder((Node) node, index);
	}
}
