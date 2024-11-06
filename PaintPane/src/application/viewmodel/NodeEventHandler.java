package viewmodel;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import model.Color;
import model.Element;
import model.ElementFactory;
import model.FxFactory;
import model.FxNode;
//import model.FxText;
import model.Type;

public class NodeEventHandler implements Observer {
	private static NodeEventHandler instance = new NodeEventHandler();
	public static NodeEventHandler getInstance() {
		return instance;
	}

	private ProgramStatus status;
	public void setProgramStatus (ProgramStatus status) {
		this.status = status;
	}

	ViewUpdater viewUpdater = ViewUpdater.getInstance();
	
	private Map<Element, FxNode> map = new HashMap<>();

	public void select(Element element) {
		status.setSelectedElement(element);
		element.addObserver(viewUpdater);
		Visitor selectVisitor = SelectVisitor.getInstance();
		element.accept(selectVisitor);
		
		viewUpdater.select(element);
	}
	public void unselect() {
		Element selectedElement = status.getSelectedElement();
		if(selectedElement == null) return;
		
		selectedElement.removeObserver(viewUpdater);
		Visitor unselectVisitor = UnselectVisitor.getInstance();
		selectedElement.accept(unselectVisitor);
		status.setSelectedElement(null);
		viewUpdater.unselect();
	}
	public void selectViewUpdate(Element element) {
		FxNode matchingNode = map.get(element);
		viewUpdater.selectNode(matchingNode);
	}
	public void unselectViewUpdate(Element element) {
		FxNode matchingNode = map.get(element);
		viewUpdater.unSelectNode(matchingNode);
	}

	public void create(double startX, double startY, double endX, double endY) {
		Type type = status.getSelectedType();
		Color color = status.getSelectedColor();
		File imageFile = status.getSelectedImageFile();
		
		ElementFactory elementFactory = ElementFactory.getInstance();
		elementFactory.setColor(color);
		elementFactory.setImageSource(imageFile);
		Element element = elementFactory.create(type, startX, startY, endX, endY);
		element.addObserver(this);

		FxFactory fxFactory = FxFactory.getInstance();
		fxFactory.setColor(color);
		fxFactory.setImageSource(imageFile);
		FxNode fxNode = fxFactory.create(type, startX, startY, endX, endY);

		status.getShapes().add(element);
		map.put(element, fxNode);
		viewUpdater.addNode(fxNode);
	}

	public void move(double dx, double dy) {
		Element selectedElement = status.getSelectedElement();
		selectedElement.move(dx, dy);
		viewUpdater.updateViewSelectedBox(selectedElement);
	}

	@Override
	public void onChanged(Element element) {
		FxNode matchingNode = map.get(element);
		matchingNode.setX(element.getX1());
		matchingNode.setY(element.getY1());
		matchingNode.setWidth(element.getWidth());
		matchingNode.setHeight(element.getHeight());
		matchingNode.setColor(element.getColor());
	}
	
	public void reOrder(Element element, int direction) {
		ReorderVisitor reorderVisitor = ReorderVisitor.getInstance();
		reorderVisitor.setShapes(status.getShapes());
		reorderVisitor.setDirection(direction);
		ReorderVisitor.lastCheckedIndex = direction == 1 ? 
				status.getShapes().size() - 2 : 1;
		element.accept(reorderVisitor);
	}
	
	public void reOrderViewUpdate(Element element, int index) {
		FxNode matchingNode = map.get(element);
		viewUpdater.reOrder(matchingNode, index);
	}
}
