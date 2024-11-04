package viewmodel;

import model.Composite;
import model.Element;

public interface Visitor {
	void handleLeaf(Element element);
	void handleComposite(Composite element);
}
