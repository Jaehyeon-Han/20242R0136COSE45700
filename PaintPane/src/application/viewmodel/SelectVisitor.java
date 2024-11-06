package viewmodel;

import model.Composite;
import model.Element;

public class SelectVisitor implements Visitor {
	private static SelectVisitor instance = new SelectVisitor();
	private NodeEventHandler adapter = NodeEventHandler.getInstance();
	
	@Override
	public void handleLeaf(Element element) {
		adapter.selectViewUpdate(element);
	}

	@Override
	public void handleComposite(Composite element) {
		for(Element child : element.getChildren()) {
			adapter.selectViewUpdate(child);
		}
	}

	public static SelectVisitor getInstance() {
		return instance;
	}
}
