package command;

import common.Color;
import model.Element;
import model.ElementManager;

public class SetColorCommand implements Command {
	private String id;
	private Color oldColor, newColor;
	
	public SetColorCommand(String id, Color oldColor, Color newColor) {
		this.id = id;
		this.oldColor = oldColor;
		this.newColor = newColor;
	}
	
	@Override
	public void execute() {
		Element element = ElementManager.getInstance().getElement(id);
		element.setColor(newColor);
	}

	@Override
	public void undo() {
		Element element = ElementManager.getInstance().getElement(id);
		element.setColor(oldColor);
	}
}
