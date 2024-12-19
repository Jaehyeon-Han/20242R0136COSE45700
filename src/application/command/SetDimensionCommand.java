package command;

import model.Element;
import model.ElementManager;

public class SetDimensionCommand implements Command {
	private String id;
	private double oldWidth, oldHeight, newWidth, newHeight;
	
	public SetDimensionCommand(String id, double newWidth, double newHeight) {
		this.id = id;
		this.newWidth = newWidth;
		this.newHeight = newHeight;

		Element element = ElementManager.getInstance().getElement(id);
		this.oldWidth = element.getWidth();
		this.oldHeight = element.getHeight();
	}

	@Override
	public void execute() {
		Element element = ElementManager.getInstance().getElement(id);
		element.setWidth(newWidth);
		element.setHeight(newHeight);
	}

	@Override
	public void undo() {
		Element element = ElementManager.getInstance().getElement(id);
		element.setWidth(oldWidth);
		element.setHeight(oldHeight);
	}
}
