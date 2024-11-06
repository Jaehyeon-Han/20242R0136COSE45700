package viewmodel;

import model.Composite;
import model.Element;

public class UnselectVisitor implements Visitor {
	private static UnselectVisitor instance = new UnselectVisitor();
	private NodeEventHandler nodeEventHandler = NodeEventHandler.getInstance();
	
	@Override
	public void handleLeaf(Element element) {
		nodeEventHandler.unselectViewUpdate(element);
	}

	@Override
	public void handleComposite(Composite element) {
		for(Element child : element.getChildren()) {
			nodeEventHandler.unselectViewUpdate(child);
		}
	}

	public static UnselectVisitor getInstance() {
		return instance;
	}
}
