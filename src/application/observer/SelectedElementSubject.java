package observer;

import model.Element;

public interface SelectedElementSubject {
	public void addObserver(SelectedElementObserver observer);

	public void removeObserver(SelectedElementObserver observer);

	public void notifyOnChange(Element element);
}
