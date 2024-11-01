package viewmodel;

import model.Element;
import model.Point;

public class CreationTool implements Tool {
	private static CreationTool instance = new CreationTool();
	private static ElementFactory elementFactory = ElementFactory.getInstance();
	private String color = null;

	@Override
	public void handle(Point a, Point b, ViewModel viewmodel) {
		javafx.scene.Node fxNode = elementFactory.getNode(a, b, viewmodel.selectedShape.toString(), color);
		Element element = elementFactory.getElement(a, b, viewmodel.selectedShape.toString(), color);
		viewmodel.model.put(element, fxNode);
		viewmodel.attachPane(fxNode);
	}

	public static CreationTool getInstance() {
		return instance;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
