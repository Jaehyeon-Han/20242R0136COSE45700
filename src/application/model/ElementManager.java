package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.CreateInfo;
import controller.SelectedElementManager;
import observer.ModelListObserver;
import observer.ModelListSubject;

public class ElementManager implements ModelListSubject {
    private List<Element> elements = new ArrayList<>();
    private Map<String, Element> idMap = new HashMap<>();
    private List<ModelListObserver> observers = new ArrayList<>();
    
    public void add(CreateInfo info) {
    	 Element newElement = ElementFactory.getInstance().create(
    			 info.getId(),
    			 info.getType(),
    			 info.getP(), 
    			 info.getQ(), 
    			 info.getColor(), 
    			 info.getImageFile(), 
    			 info.getText()
    	        );
    	idMap.put(newElement.getId(), newElement);
        elements.add(newElement);
        notifyOnCreate(newElement);
	}

	public void remove(String id) {
		Element toRemove = idMap.get(id);
		elements.remove(toRemove);
		notifyOnRemove(toRemove);
	}

	public Element getElement(String id) {
		Element selectedElement = SelectedElementManager.getInstance().getSelectedElement();
		if(selectedElement != null && selectedElement.getId().equals(id)) {
			return selectedElement;
		} // Composite element is not in the list
		return idMap.get(id);
	}
	
	public boolean isInList(Element element) {
		return elements.contains(element);
	}
	
    public List<Element> getAllElements() {
    	return elements;
    }
    
    // ModelListObserver Subject
    public void addObserver(ModelListObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(ModelListObserver observer) {
        observers.remove(observer);
    }
    
	public void notifyOnCreate(Element element) {
		for(ModelListObserver observer: observers) {
			observer.onCreate(element);
		}
	}

	public void notifyOnRemove(Element element) {
		for(ModelListObserver observer: observers) {
			observer.onRemove(element);
		}
	}
    
    // Singleton
    private ElementManager() {}

    private static class ShapeManagerHelper {
        private static final ElementManager INSTANCE = new ElementManager();
    }

    public static ElementManager getInstance() {
        return ShapeManagerHelper.INSTANCE;
    }
}
