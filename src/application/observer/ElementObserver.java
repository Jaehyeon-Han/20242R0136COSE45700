package observer;

import model.Element;

public interface ElementObserver {
	void onChange(Element element);
}
