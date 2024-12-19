package controller;

import java.util.ArrayList;
import java.util.List;

import common.Point;
import model.Composite;
import model.Element;
import model.ElementFactory;
import model.ElementManager;
import observer.SelectedStateObserver;
import observer.SelectedStateSubject;

public class ElementSelector implements SelectedStateSubject {
    private ElementManager elementManager = ElementManager.getInstance();
    private SelectedElementManager selectedElementManager = SelectedElementManager.getInstance();
    private List<SelectedStateObserver> observers = new ArrayList<>();
    
    public boolean select(Point p) {
    	Element currentSelected = selectedElementManager.getSelectedElement();
    	if(currentSelected != null && currentSelected.isHit(p) 
    			&& !elementManager.isInList(currentSelected)) {
    		notifyOnSelect(currentSelected);
    		return true;
    	} // Composite is not in the list
    	
    	List<Element> elements = elementManager.getAllElements();
		for (int i = elements.size() - 1; i >= 0; --i) {
			Element element = elements.get(i);
			if (element.isHit(p)) {
				selectedElementManager.setSelectedElement(element);
				notifyOnSelect(selectedElementManager.getSelectedElement());
				return true;
			}
		}
		
		selectedElementManager.setSelectedElement(null);
		notifyOnUnSelect();
		return false;
	}
	
	public boolean select(Point p, Point q) {
		List<Element> selectedList = new ArrayList<>();
		for(Element element : elementManager.getAllElements()) {
			if(element.intersects(p, q)) {
				selectedList.add(element);
			}
		}
		if(selectedList.size() == 0) {
			selectedElementManager.setSelectedElement(null);
			notifyOnUnSelect();
			return false;
		} else if(selectedList.size() == 1) {
			selectedElementManager.setSelectedElement(selectedList.get(0));
		} else {
			Composite composite = ElementFactory.getInstance().createComposite(selectedList);
			selectedElementManager.setSelectedElement(composite); 
		}
		notifyOnSelect(selectedElementManager.getSelectedElement());
		return true;
	}

	// SelectStateObserver Subject
    public void addObserver(SelectedStateObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(SelectedStateObserver observer) {
        observers.remove(observer);
    }
    
	public void notifyOnSelect(Element selectedElement) {
		for(SelectedStateObserver observer: observers) {
			observer.onSelect(selectedElement);
		}
	}

	public void notifyOnUnSelect() {
		for(SelectedStateObserver observer: observers) {
			observer.onUnSelect();
		}
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
