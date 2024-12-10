package view.state;

import view.DrawingPane;

public class MultiSelectState implements ToolState {
	private DrawingPane drawingPane;
	private double startX, startY, endX, endY;
	
	public MultiSelectState(DrawingPane drawingPane, double startX, double startY) {
		this.startX = startX;
		this.startY = startY;
		this.drawingPane = drawingPane;
	}
	
	@Override
	public void handleMousePressed(double x, double y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleMouseDragged(double x, double y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleMouseReleased(double x, double y) {
		// TODO Auto-generated method stub

	}
}
