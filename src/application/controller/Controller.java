package controller;

import java.util.ArrayList;
import java.util.List;

import common.Observer;
import common.PropertyDTO;
import model.Element;

public class Controller {
	ElementManager shapeManager = ElementManager.getInstance();
	List<Observer> observers = new ArrayList<>();

	public void createElement(PropertyDTO dto) {
		PropertyDTO newElementDTO = shapeManager.create(dto);
		notifyOnCreate(newElementDTO);
	}
	
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
}
