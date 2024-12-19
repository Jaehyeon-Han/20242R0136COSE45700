package observer;

import model.Element;

public interface ModelListObserver {
	void onCreate(Element element);
	void onRemove(Element element);
}
