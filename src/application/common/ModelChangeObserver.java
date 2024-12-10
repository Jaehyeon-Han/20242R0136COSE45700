package common;

import model.Element;

public interface ModelChangeObserver {
	void onChange(Element element);

	void onRemove(Element element);
}
