package view.state;

import command.Command;
import command.CommandInvoker;
import command.TranslateCommand;
import view.DrawingPane;

public class TranslateState implements ToolState {
	private double startX, startY, endX, endY;
	private DrawingPane drawingPane;
	
	@Override
	public void handleMouseReleased(double x, double y) {
		endX = x;
		endY = y;
		
		Command translateCommand = new TranslateCommand(endX - startX, endY - startY);
		CommandInvoker.getInstance().execute(translateCommand);
		drawingPane.setSelectState();
	}

	public TranslateState(double x, double y, DrawingPane drawingPane) {
		startX = x;
		startY = y;
		this.drawingPane = drawingPane;
	}
}
