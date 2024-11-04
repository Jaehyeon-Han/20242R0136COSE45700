package viewmodel;

import java.util.ArrayList;

import model.Element;
import model.ElementFactory;

public class SelectionTool implements Tool {
	private static SelectionTool instance = new SelectionTool();
	private Adapter adapter = Adapter.getInstance();
	private ViewModel viewModel;
	private double x1, y1, x2, y2;
	
	public void setViewModel(ViewModel viewModel) {
		this.viewModel = viewModel;
	}

	public static SelectionTool getInstance() {
		return instance;
	}

	@Override
	public void handlePressed(double x, double y) {
		boolean found = false;
		System.out.println(x + ", " + y);
		if(viewModel.selectedElement != null && viewModel.selectedElement.isInHere(x, y)) {
			found = true;
		}
		else {
			for (int i = viewModel.shapes.size() - 1; i >= 0; --i) {
				Element element = viewModel.shapes.get(i);
				if (element.isInHere(x, y)) {
					adapter.unselect();
					adapter.select(element);
					found = true;
					break;
				}
			}
			if (!found) {
				adapter.unselect();
			}
		}
		x1 = x;
		y1 = y;
	}

	@Override
	public void handleReleased(double x, double y) {
		ArrayList<Element> toSelectShapes = new ArrayList<>();
		x2 = x;
		y2 = y;

		if (viewModel.selectedElement != null) {
			adapter.setDeltas(x2 - x1, y2- y1);
			adapter.move();
		}

		else {
			makeBoundBox();
			for (Element element : viewModel.shapes) {
				if (element.intersects(x1, y1, x2, y2)) {
					toSelectShapes.add(element);
				}
			}
			if(toSelectShapes.isEmpty() ) {
				return;
			}
			adapter.select(ElementFactory.getInstance().createComposite(toSelectShapes));
		}
	}

	private void makeBoundBox() {
		double temp;
		if (x1 > x2) {
			temp = x1;
			x1 = x2;
			x2 = temp;
		}

		if (y1 > y2) {
			temp = y1;
			y1 = y2;
			y2 = temp;
		}
	}
}
