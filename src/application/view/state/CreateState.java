package view.state;

import java.io.File;

import command.CommandInvoker;
import command.CreateCommand;
import common.Color;
import common.CreateInfo;
import common.Point;

import view.DrawingPane;
import view.ToolWindow;

public class CreateState implements ToolState {
	private DrawingPane drawingPane;
	private double startX, startY, endX, endY;
	
	public CreateState(DrawingPane drawingPane) {
		this.drawingPane = drawingPane;
	}
	
	@Override
	public void handleMousePressed(double x, double y) {
		startX = x;
		startY = y;
	}

	@Override
	public void handleMouseReleased(double x, double y) {
		endX = x;
		endY = y;

		Point p = new Point(startX, startY);
		Point q = new Point(endX, endY);

		CreateCommand createCommand = new CreateCommand(createCreateInfo(p, q));
		CommandInvoker.getInstance().execute(createCommand);
	}
	
	private CreateInfo createCreateInfo(Point p, Point q) {
		ToolWindow toolWindow = drawingPane.getToolWindow();
		String type = toolWindow.getType();
		Color color = toolWindow.getColor();
		String text = toolWindow.getText();
		File selectedImageFile = toolWindow.getImageFile();
		
		CreateInfo.Builder builder = new CreateInfo.Builder(null, type, p, q);
		CreateInfo info = builder.setColor(color)
		.setImageFile(selectedImageFile)
		.setText(text)
		.build();
		
		return info;
	}
}
