package controller;

import java.util.ArrayList;
import java.util.List;

import common.Point;
import common.PropertyDTO;
import model.Element;
import model.ElementFactory;
import model.ElementManager;

public class ElementSelector {
	public Element select(Point p) {
		ElementManager elementManager = ElementManager.getInstance();
		
		List<Element> elements = elementManager.getAllElements();
		for (int i = elements.size() - 1; i >= 0; --i) {
			Element element = elements.get(i);
			if (element.isInHere(p)) {
				elementManager.setSelectedElement(element);
				return elementManager.getSelectedElement();
			}
		}
		return null;
	}
	
	public Element select(Point p, Point q) {
		ElementManager elementManager = ElementManager.getInstance();
		
		Element selectedElement;
		List<Element> selected = new ArrayList<>();
		for(Element element : elementManager.getAllElements()) {
			if(element.intersects(p, q)) {
				selected.add(element);
			}
		}
		if(selected.size() == 0) {
			selectedElement = null;
		} else if(selected.size() == 1) {
			selectedElement = selected.get(0);
		} else {
			selectedElement= ElementFactory.getInstance().createComposite(selected);
		}
		elementManager.setSelectedElement(selectedElement);
		return selectedElement;
	}

	// Singleton
	private ElementSelector() {
	}

	private static class ElementSelectorHelper {
		private static final ElementSelector INSTANCE = new ElementSelector();
	}

	public static ElementSelector getInstance() {
		return ElementSelectorHelper.INSTANCE;
	}
}
