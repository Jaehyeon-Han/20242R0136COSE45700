package view.state;

import java.io.File;

import command.CommandInvoker;
import command.CreateCommand;
import common.Color;
import common.Point;
import common.PropertyDTO;
import view.ToolWindow;

public class CreateState implements ToolState {
	private double startX, startY, endX, endY;
	private ToolWindow toolWindow;

	@Override
	public void handleMousePressed(double x, double y) {
		startX = x;
		startY = y;
	}

	@Override
	public void handleMouseReleased(double x, double y) {
		endX = x;
		endY = y;

		// 책임 분리 필요?
		String type = toolWindow.getType();
		Point p = new Point(startX, startY);
		Point q = new Point(endX, endY);
		// p, q를 바운드 박스 형태로 변환
		Color color = toolWindow.getColor();
		File selectedImageFile = toolWindow.getImageFile();
		
		PropertyDTO.Builder builder = new PropertyDTO.Builder(type, p, q);
		PropertyDTO dto = builder.setColor(color)
		.setImageFile(selectedImageFile)
		//.setText(text);
		.build();

		CreateCommand createCommand = new CreateCommand(dto);
		CommandInvoker.getInstance().execute(createCommand);
	}
	
	@Override
	public void handleMouseDragged(double x, double y) {
		// Do nothing
	}

	public void setToolWindow(ToolWindow toolWindow) {
		this.toolWindow = toolWindow;
	}
	
	// Singleton
	private CreateState() {
	}

	private static class CreateStateHelper {
		private static CreateState INSTANCE = new CreateState();
	}

	public static CreateState getInstance() {
		return CreateStateHelper.INSTANCE;
	}

	

}
