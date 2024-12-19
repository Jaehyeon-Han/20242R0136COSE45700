package view.state;

import command.Command;
import command.CommandInvoker;
import command.TranslateCommand;

import view.DrawingPane;
import view.fxmodel.FxElementManager;

public class TranslateState implements ToolState {
	private double startX, startY;
	private DrawingPane drawingPane;
	
	public TranslateState(DrawingPane drawingPane, double x, double y) {
		System.out.println("Translate State");
		startX = x;
		startY = y;
		this.drawingPane = drawingPane;
	}
	
	@Override
	public void handleMouseReleased(double endX, double endY) {
		String id = FxElementManager.getInstance().getSelectedId();
		
		Command translateCommand = new TranslateCommand(id, endX - startX, endY - startY);
		CommandInvoker.getInstance().execute(translateCommand);
		
		drawingPane.setCurrentState(new SelectState(drawingPane));
	}
}
