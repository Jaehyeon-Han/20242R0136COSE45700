package controller;

import java.util.List;

import common.Point;
import common.PropertyDTO;
import model.Element;

public class ElementSelector {
	public PropertyDTO select(Point p) {
		ElementManager elementManager = ElementManager.getInstance();
		
		List<Element> elements = elementManager.getAllElements();
		for (int i = elements.size() - 1; i >= 0; --i) {
			Element element = elements.get(i);
			if (element.isInHere(p)) {
				elementManager.setSelectedElement(element);
				PropertyDTO dto = element.toDTO();
				dto.setId(elementManager.getElementId(element));
				return dto;
			}
		}
		return null;
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
