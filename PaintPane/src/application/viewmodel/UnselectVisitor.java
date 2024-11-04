package viewmodel;

import model.Composite;
import model.Element;

public class UnselectVisitor implements Visitor {
	private static UnselectVisitor instance = new UnselectVisitor();
	private Adapter adapter = Adapter.getInstance();
	
	@Override
	public void handleLeaf(Element element) {
		adapter.unselectViewUpdate(element);
	}

	@Override
	public void handleComposite(Composite element) {
		for(Element child : element.getChildren()) {
			adapter.unselectViewUpdate(child);
		}
	}

	public static UnselectVisitor getInstance() {
		return instance;
	}
}
