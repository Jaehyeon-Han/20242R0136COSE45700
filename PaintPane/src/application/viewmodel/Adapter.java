package viewmodel;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Node;
import model.Color;
import model.Element;
import model.ElementFactory;
import model.FxNode;
import view.View;

public class Adapter implements Observer {
	private static Adapter instance = new Adapter();
	private Map<Element, FxNode> map = new HashMap<>();
	private ViewModel viewModel;
	private View view;
	private double dx, dy;

	public void select(Element element) {
		viewModel.selectedElement = element;
		Visitor selectVisitor = SelectVisitor.getInstance();
		element.accept(selectVisitor);
		view.changeSelectStatus(true);
		updatePropertyWindow();
		view.attachSelectedBox(element.getX1(), element.getY1(), element.getX2(), element.getY2());
	}
	
	public void selectViewUpdate(Element element) {
		FxNode matchingNode = map.get(element);
		matchingNode.setOpacity(0.7);
	}
	
	public void unselectViewUpdate(Element element) {
		FxNode matchingNode = map.get(element);
		matchingNode.setOpacity(1);
	}
	
	public void unselect() {
		Element selectedElement = viewModel.selectedElement;
		if(selectedElement == null) return;
		Visitor unselectVisitor = UnselectVisitor.getInstance();
		selectedElement.accept(unselectVisitor);
		viewModel.selectedElement = null;
		view.changeSelectStatus(false);
		view.detachSelectedBox();
	}

	public void create(String type, double x1, double y1, double x2, double y2, Color color, String imgsrc) {
		ElementFactory elementFactory = ElementFactory.getInstance();
		elementFactory.setColor(color);
		elementFactory.setImageSource(imgsrc);
		Element element = elementFactory.create(type, x1, y1, x2, y2);
		element.addObserver(instance);

		FxFactory fxFactory = FxFactory.getInstance();
		fxFactory.setColor(color);
		fxFactory.setImageSource(imgsrc);
		FxNode fxNode = fxFactory.create(type, x1, y1, x2, y2);

		viewModel.shapes.add(element);
		map.put(element, fxNode);
		view.addNode((Node) fxNode);
	}

	public static Adapter getInstance() {
		return instance;
	}

	public void setViewModel(ViewModel viewModel) {
		this.viewModel = viewModel;
	}

	public void setView(View view) {
		this.view = view;
	}

	public void move() {
		Element selectedElement = viewModel.selectedElement;
		selectedElement.move(dx, dy);
//		Visitor moveVisitor = MoveVisitor.getInstance();
//		selectedElement.accept(moveVisitor);
		view.moveSelectedBox(dx, dy);
	}

	public void setDeltas(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}

	public void updatePropertyWindow() {
		Element element = viewModel.selectedElement;
		
		if(element == null) {
			return;
		}
		
		view.updateX(element.getX1());
		view.updateY(element.getY1());
		view.updateWidth(element.getX2() - element.getX1());
		view.updateHeight(element.getY2() - element.getY1());
		view.updateColor(element.getColor().toFxColor());
	}

	@Override
	public void onChanged(Element element) {
		FxNode matchingNode = map.get(element);
		matchingNode.setX(element.getX1());
		matchingNode.setY(element.getY1());
		matchingNode.setWidth(element.getX2() - element.getX1());
		matchingNode.setHeight(element.getY2() - element.getY1());
		matchingNode.setColor(element.getColor());
		updatePropertyWindow();
	}
}
