package view.state;

import command.Command;
import command.CommandInvoker;
import command.ResizeCommand;
import common.Point;
import view.DrawingPane;

public class ResizeState implements ToolState {
	private DrawingPane drawingPane;
	
	public ResizeState(DrawingPane drawingPane) {
		this.drawingPane = drawingPane;
	}
	
	@Override
	public void handleMouseReleased(double x, double y) {
		Command resizeCommand = new ResizeCommand(new Point(x, y));
		CommandInvoker.getInstance().execute(resizeCommand);
		drawingPane.setCurrentState(SelectState.getInstance());
	}
}
