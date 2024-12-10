package controller;

import java.util.ArrayList;
import java.util.List;

import common.Observer;
import common.Point;
import common.PropertyDTO;
import model.Element;

public class Controller {
	ElementManager elementManager = ElementManager.getInstance();
	ElementSelector elementSelector = ElementSelector.getInstance();
	List<Observer> observers = new ArrayList<>();

	public void addElement(Element newElement) {
		elementManager.add(newElement);
		notifyOnCreate(newElement);
	}
	
	public void removeElement(Element element) {
		elementManager.remove(element);
		notifyOnRemove(element);
	}

	public void select(Point p) {
		Element selectedElement = elementSelector.select(p);
		if(selectedElement == null) {
			notifyOnUnselect();
		} else {
			notifyOnSelect(selectedElement);
		}
	}

	public void select(Point p, Point q) {
		
	}
	
	public void translate(String id, double dx, double dy) {
		Element selectedElement = elementManager.getElement(id);
		selectedElement.translate(dx, dy);
		notifyOnChange(selectedElement);
	}
	
	public void resize(String id, Point newQ) {
		Element selectedElement = elementManager.getElement(id);
		selectedElement.setQ(newQ);
		notifyOnChange(selectedElement);
	}
	
	// Observer
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyOnCreate(Element newElement) {
        for (Observer observer : observers) {
            observer.onCreate(newElement);
        }
    }

    public void notifyOnChange(Element element) {
        for (Observer observer : observers) {
            observer.onChange(element);
        }
    }
    
	private void notifyOnSelect(Element selectedElement) {
		for (Observer observer : observers) {
            observer.onSelect(selectedElement);
        }
	}
	
	private void notifyOnUnselect() {
		for (Observer observer : observers) {
            observer.onUnselect();
        }
	}
	
	private void notifyOnRemove(Element element) {
		for (Observer observer : observers) {
            observer.onRemove(element);
        }
	}
}
