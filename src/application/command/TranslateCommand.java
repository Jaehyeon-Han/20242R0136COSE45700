package command;

import model.Element;
import model.ElementManager;

public class TranslateCommand implements Command {
	private String id;
	private double dx, dy;
	
	public TranslateCommand(String id, double dx, double dy) {
		this.id = id;
		this.dx = dx;
		this.dy = dy;
	}
	
	@Override
	public void execute() {
		Element element = ElementManager.getInstance().getElement(id);
		element.translate(dx, dy);
	}

	@Override
	public void undo() {
		Element element = ElementManager.getInstance().getElement(id);
		element.translate(-dx, -dy);
	}

}
