package observer;

import model.Element;

public interface ModelChangeObserver {
	void onChange(Element element);
}
