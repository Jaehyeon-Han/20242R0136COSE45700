package viewmodel;

import java.util.ArrayList;
import java.util.List;

import model.Composite;
import model.Element;

public class ReorderVisitor implements Visitor {
	private static ReorderVisitor instance = new ReorderVisitor();
	private NodeEventHandler adapter = NodeEventHandler.getInstance();
	private int direction;
	private ArrayList<Element> shapes;
	static int lastCheckedIndex;
	
	@Override
	public void handleLeaf(Element element) {
		// Forward
		if(direction == 1) {
			for (int i = lastCheckedIndex; i >= 0 ; --i) {
				if(element == shapes.get(i)) {
					adapter.reOrderViewUpdate(element, i+1);
					swap(i, i+1);
					lastCheckedIndex = i;
					return;
				}
			}
			
		} else {
			for (int i = lastCheckedIndex; i <= shapes.size() - 1; ++i) {
				if(element == shapes.get(i)) {
					adapter.reOrderViewUpdate(element, i-1);
					swap(i, i-1);
					lastCheckedIndex = i;
					return;
				}
			}
		}
	}

	@Override
	// This does not properly handle if one of the composite
	// elements is at the front or back.
	public void handleComposite(Composite element) {
		// the elements in the composite follow the same partial order as those in shapes.
		List<Element> children = element.getChildren();
		if(direction == 1) {
			for(int i = children.size() - 1; i >= 0; --i) {
				children.get(i).accept(this);
			}
			
		} else {
			for(int i = 0; i < children.size() - 1; ++i) {
				children.get(i).accept(this);
			}
		}
	}
	
	public static ReorderVisitor getInstance() {
		return instance;
	}

	public void setShapes(ArrayList<Element> shapes) {
		this.shapes = shapes;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	private void swap(int i, int j) {
		Element temp = shapes.get(j);
		shapes.set(j, shapes.get(i));
		shapes.set(i, temp);
	}
}
