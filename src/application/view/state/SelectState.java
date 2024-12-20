package view.state;

import common.Point;
import controller.ElementSelector;
import view.DrawingPane;

public class SelectState implements ToolState {
	private DrawingPane drawingPane;
	
	public SelectState(DrawingPane drawingPane) {
		this.drawingPane = drawingPane;
	}
			
	@Override
	public void handleMousePressed(double x, double y) {
		boolean isSelected = ElementSelector.getInstance().select(new Point(x, y));
		if(isSelected) {
			drawingPane.setCurrentState(new TranslateState(drawingPane, x, y));
		} else {
			drawingPane.setCurrentState(new MultiSelectState(drawingPane, x, y));
		}
	}
}