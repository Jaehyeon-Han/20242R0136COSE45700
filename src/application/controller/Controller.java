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

	public void createElement(PropertyDTO dto) {
		Element newElement = elementManager.create(dto);
		notifyOnCreate(newElement);
	}
	
	public void select(Point p) {
		PropertyDTO newElementDTO = elementSelector.select(p);
		if(newElementDTO == null) {
			notifyOnUnselect();
		} else {
			notifyOnSelect(newElementDTO);
		}
	}

	public void select(Point p, Point q) {
		
	}
	
	public void translate(double dx, double dy) {
		Element selectedElement = elementManager.getSelectedElement();
		selectedElement.translate(dx, dy);
		notifyOnChange(selectedElement.toDTO());
	}
	
	public void resize(Point newQ) {
		Element selectedElement = elementManager.getSelectedElement();
		selectedElement.setQ(newQ);
		notifyOnChange(selectedElement.toDTO());
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

    public void notifyOnChange(PropertyDTO dto) {
        for (Observer observer : observers) {
            observer.onChange(dto);
        }
    }
    
	private void notifyOnSelect(PropertyDTO dto) {
		for (Observer observer : observers) {
            observer.onSelect(dto);
        }
	}
	
	private void notifyOnUnselect() {
		for (Observer observer : observers) {
            observer.onUnselect();
        }
	}
}
