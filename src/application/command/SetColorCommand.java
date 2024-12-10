package command;

import common.Color;
import controller.Controller;
import controller.ElementManager;
import model.Element;

public class SetColorCommand implements Command {
	private Color oldColor, newColor;
	private String id;
	Element element;
	
	public SetColorCommand(String id, Color oldColor, Color newColor) {
		this.id = id;
		this.oldColor = oldColor;
		this.newColor = newColor;
		element = ElementManager.getInstance().getElement(id);
	}
	
	@Override
	public void execute() {
		element.setColor(newColor);
	}

	@Override
	public void undo() {
		element.setColor(oldColor);
	}

	@Override
	public void setController(Controller controller) {
	}

}
