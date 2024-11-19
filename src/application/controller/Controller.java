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
		notifyOnSelect(newElementDTO);
	} // 선택 안 된 경우 null 반환
	


	public void select(Point p, Point q) {
		
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
    
	private void notifyOnSelect(PropertyDTO dto) {
		for (Observer observer : observers) {
            observer.onSelect(dto);
        }
	}
	
}
