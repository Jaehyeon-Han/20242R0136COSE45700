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
		PropertyDTO newElementDTO = elementManager.create(dto);
		notifyOnCreate(newElementDTO);
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
		// 게속 가져와야 되는데 그냥 ElementManager에서 하게 바꿀까?
		Element selectedElement = elementManager.getSelectedElement();
		selectedElement.translate(dx, dy);
		notifyOnChange(elementManager.getDTO(selectedElement));
	}
	
	public void resize(Point newQ) {
		Element selectedElement = elementManager.getSelectedElement();
		selectedElement.setQ(newQ);
		notifyOnChange(elementManager.getDTO(selectedElement));
	}
	
	// Observer
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyOnCreate(PropertyDTO dto) {
        for (Observer observer : observers) {
            observer.onCreate(dto);
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
