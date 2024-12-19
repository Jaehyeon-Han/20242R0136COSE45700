package view.state;

import command.Command;
import command.CommandInvoker;
import command.HandlerResizeCommand;
import common.Point;
import view.DrawingPane;

public class ResizeState implements ToolState {
	private DrawingPane drawingPane;
	private double startX, startY;
	private String id;
	
	public ResizeState(DrawingPane drawingPane, String id, double startX, double startY) {
		System.out.println("Resize State");
		this.drawingPane = drawingPane;
		this.id = id;
		this.startX = startX;
		this.startY = startY;
	}
	
	@Override
	public void handleMouseReleased(double x, double y) {
		Command resizeCommand = new HandlerResizeCommand
				(id, new Point(startX, startY), new Point(x, y));
		CommandInvoker.getInstance().execute(resizeCommand);
		drawingPane.setCurrentState(new SelectState(drawingPane));
	}
}
