package viewmodel;

import model.Element;

public interface Observer {
	void onChanged(Element element);
}
