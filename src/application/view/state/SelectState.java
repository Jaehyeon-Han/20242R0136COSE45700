package view.state;

import command.CommandInvoker;
import command.SingleSelectCommand;
import common.Observer;
import common.Point;
import common.PropertyDTO;
import model.Element;
import view.DrawingPane;

public class SelectState implements ToolState, Observer {
	private double startX, startY;
	private DrawingPane drawingPane;
	private static final int CLICK_THRESHOLD = 5;
	private boolean isSelectCommandExecuted = false;
	
//	public SelectState(DrawingPane drawingPane) {
//		this.drawingPane = drawingPane;
//	}
			
	@Override
	public void handleMousePressed(double x, double y) {
		startX = x;
		startY = y;
		CommandInvoker.getInstance().execute(new SingleSelectCommand(new Point(startX, startY)));
		isSelectCommandExecuted = true;
	}

	@Override
	public void handleMouseDragged(double x, double y) {
		if (!isSelectCommandExecuted) {
	        return;
	    }
		
		double deltaX = Math.abs(x - startX);
		double deltaY = Math.abs(y - startY);

		if (deltaX > CLICK_THRESHOLD || deltaY > CLICK_THRESHOLD) {
			drawingPane.setCurrentState(new MultiSelectState(drawingPane, startX, startY));
		}
	}

	public void setDrawingPane(DrawingPane drawingPane) {
		this.drawingPane = drawingPane;
	}

	@Override
	public void onSelect(Element element) {
		drawingPane.setCurrentState(new TranslateState(startX, startY, drawingPane));
	}
	
	// Singleton
	private SelectState() {
	}

	private static class SelectStateHelper {
		private static SelectState INSTANCE = new SelectState();
	}

	public static SelectState getInstance() {
		return SelectStateHelper.INSTANCE;
	}
}