package controller;

import java.util.ArrayList;
import java.util.List;

import model.Element;
import observer.ElementObserver;
import observer.SelectedElementObserver;
import observer.SelectedElementSubject;

public class SelectedElementManager implements ElementObserver, SelectedElementSubject {
	private Element selectedElement;
	private List<SelectedElementObserver> observers = new ArrayList<>();

	public Element getSelectedElement() {
		return selectedElement;
	}

	public void setSelectedElement(Element element) {
		// Sth is already selected
		if(selectedElement != null) {
			selectedElement.removeObserver(this);
		}
		
		selectedElement = element;
		
		if(element != null) {
			selectedElement.addObserver(this);
		}
	}

	public boolean isSelected() {
		return selectedElement != null;
	}

	// SelectedElement Subject
	public void addObserver(SelectedElementObserver observer) {
		observers.add(observer);
	}

	public void removeObserver(SelectedElementObserver observer) {
		observers.remove(observer);
	}

	public void notifyChange(Element element) {
		for (SelectedElementObserver observer : observers) {
			observer.onChange(element);
		}
	}
	
	// Observing Selected Element
	@Override
	public void onChange(Element element) {
		notifyChange(element);
	}

	// Singleton
	private SelectedElementManager() {
	}

	private static class SelectedElementManagerHelper {
		private static final SelectedElementManager INSTANCE = new SelectedElementManager();
	}

	public static SelectedElementManager getInstance() {
		return SelectedElementManagerHelper.INSTANCE;
	}
}
