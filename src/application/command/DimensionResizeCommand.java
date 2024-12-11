package command;

import controller.Controller;
import model.Element;
import model.ElementManager;

public class DimensionResizeCommand implements Command {
	private double oldWidth, oldHeight, newWidth, newHeight;
	Element element;
	
	public DimensionResizeCommand(String id, double newWidth, double newHeight) {
		this.newWidth = newWidth;
		this.newHeight = newHeight;
		element = ElementManager.getInstance().getElement(id);
		oldWidth = element.getWidth();
		oldHeight = element.getHeight();
	}
	
	@Override
	public void execute() {
		element.setWidth(newWidth);
		element.setHeight(newHeight);
	}

	@Override
	public void undo() {
		element.setWidth(oldWidth);
		element.setHeight(oldHeight);
	}

	@Override
	public void setController(Controller controller) {
	}

}
