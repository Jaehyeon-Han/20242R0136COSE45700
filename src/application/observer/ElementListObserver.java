package observer;

import model.Element;

public interface ElementListObserver {
	void onCreate(Element element);
	void onRemove(Element element);
}
