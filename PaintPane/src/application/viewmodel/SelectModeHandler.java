package viewmodel;

import java.util.ArrayList;

import model.Composite;
import model.Element;
import model.ElementFactory;

public class SelectModeHandler implements MoustEventHandler {
	private static SelectModeHandler instance = new SelectModeHandler();
	public static SelectModeHandler getInstance() {
		return instance;
	}
	
	private NodeEventHandler adapter = NodeEventHandler.getInstance();
	private ProgramStatus status;
	public void setProgramStatus(ProgramStatus status) {
		this.status = status;
	}
	
	private double startX, startY, endX, endY; 
	
	@Override
	public void handlePressed(double x, double y) {
		startX = x;
		startY = y;
		
		Element selectedElement = status.getSelectedElement();
		boolean found = false;

		if(status.isElementSelected() && selectedElement.isInHere(x, y)) {
			found = true;
		} // 선택된 도형 드래그
		else {
			for (int i = status.getShapes().size() - 1; i >= 0; --i) {
				Element element = status.getShapes().get(i);
				if (element.isInHere(x, y)) {
					adapter.unselect();
					adapter.select(element);
					found = true;
					break;
				}
			} // 새로운 도형 선택
			if (!found) {
				adapter.unselect();
			} // 빈 공간 클릭
		}
	}

	@Override
	public void handleReleased(double x, double y) {
		endX = x;
		endY = y;
		
		if (status.isElementSelected()) {
			adapter.move(endX - startX, endY - startY);
		} else {
			ArrayList<Element> toSelectShapes = new ArrayList<>();
			makeBoundBox(); // Make sure startX <= endX, startY <= endY
			for (Element element : status.getShapes()) {
				if (element.intersects(startX, startY, endX, endY)) {
					toSelectShapes.add(element);
				}
			}
			if(toSelectShapes.isEmpty() ) {
				return;
			}
			Composite composite = ElementFactory.getInstance().createComposite(toSelectShapes);
			composite.addObserver(ViewUpdater.getInstance());
			adapter.select(composite);
		}
	}

	private void makeBoundBox() {
		double temp;
		if (startX > endX) {
			temp = startX;
			startX = endX;
			endX = temp;
		}

		if (startY > endY) {
			temp = startY;
			startY = endY;
			endY = temp;
		}
	}
}
