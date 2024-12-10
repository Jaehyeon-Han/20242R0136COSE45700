package view.state;

import command.Command;
import command.CommandInvoker;
import command.TranslateCommand;
import view.DrawingPane;
import view.fxmodel.FxElementManager;

public class TranslateState implements ToolState {
	private double startX, startY, endX, endY;
	private DrawingPane drawingPane;
	private static final int CLICK_THRESHOLD = 5;
	
	public TranslateState(double x, double y, DrawingPane drawingPane) {
		System.out.println("Translate State");
		startX = x;
		startY = y;
		this.drawingPane = drawingPane;
	}
	
	@Override
	public void handleMouseReleased(double x, double y) {
		endX = x;
		endY = y;
		
		double deltaX = Math.abs(x - startX);
		double deltaY = Math.abs(y - startY);

		if (deltaX > CLICK_THRESHOLD || deltaY > CLICK_THRESHOLD) {
			String id = FxElementManager.getInstance().getSelectedId();
			
			Command translateCommand = new TranslateCommand(id, endX - startX, endY - startY);
			CommandInvoker.getInstance().execute(translateCommand);
		}
		
		drawingPane.setCurrentState(SelectState.getInstance());
	}
}
