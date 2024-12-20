package observer;

import model.Element;

public interface SelectedStateSubject {
	public void addObserver(SelectedStateObserver observer);

    public void removeObserver(SelectedStateObserver observer);
    
	public void notifySelect(Element selectedElement);

	public void notifyUnSelect();
}
