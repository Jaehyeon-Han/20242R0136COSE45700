package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import common.Point;
import common.PropertyDTO;

import model.Element;
import model.ElementFactory;

public class ElementManager {
    private List<Element> elements = new ArrayList<>();
    private Map<String, Element> idMap = new HashMap<>();
    private Element selectedElement;
    
    public void add(Element newElement) {
    	idMap.put(newElement.getId(), newElement);
        elements.add(newElement);
	}

	public void remove(Element element) {
		idMap.remove(element.getId());
		elements.remove(element);
	}

	public Element getElement(String id) {
		return idMap.get(id);
	}
	
    public List<Element> getAllElements() {
    	return elements;
    }
    
    
    
    
    // Singleton
    private ElementManager() {}

    private static class ShapeManagerHelper {
        private static final ElementManager INSTANCE = new ElementManager();
    }

    public static ElementManager getInstance() {
        return ShapeManagerHelper.INSTANCE;
    }

	public void setSelectedElement(Element element) {
		selectedElement = element;
	}
	
	public Element getSelectedElement() {
		return selectedElement;
	}

	public boolean isSelected() {
		return selectedElement != null;
	}

	
}
