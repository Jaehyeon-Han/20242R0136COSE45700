package controller;

import java.util.List;

import common.Point;
import common.PropertyDTO;
import model.Element;

public class ElementSelector {
	public PropertyDTO select(Point p) {
		List<Element> elements = ElementManager.getInstance().getAllElements();
		for (int i = elements.size() - 1; i >= 0; --i) {
			Element element = elements.get(i);
			if (element.isInHere(p)) {
				return element.toDTO();
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
