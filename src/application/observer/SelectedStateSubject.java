package observer;

import model.Element;

public interface SelectedStateSubject {
	public void addObserver(SelectedStateObserver observer);

    public void removeObserver(SelectedStateObserver observer);
    
	public void notifyOnSelect(Element selectedElement);

	public void notifyOnUnSelect();
}
