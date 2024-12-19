package observer;

import model.Element;

public interface SelectedStateObserver {
	void onSelect(Element element);
	void onUnSelect();
}
