package common;

import model.Element;

public interface ModelChangeObserver {
	void onChange(Element element);
	void onRemove();
	void select();
	void unselect();
}
