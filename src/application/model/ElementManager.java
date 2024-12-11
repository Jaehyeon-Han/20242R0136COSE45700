package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import common.Point;
import common.PropertyDTO;

public class ElementManager {
    public List<Element> elements = new ArrayList<>();
    public Map<String, Element> idMap = new HashMap<>();
    public Element selectedElement;	
    
    public void add(Element newElement) {
    	idMap.put(newElement.getId(), newElement);
        elements.add(newElement);
	}

	public void remove(Element element) {
		element.remove();
	}

	public Element getElement(String id) {
		if(selectedElement != null && selectedElement.getId().equals(id)) {
			return selectedElement;
		}
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
