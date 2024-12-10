package command;

import common.Point;
import controller.Controller;
import controller.ElementSelector;
import model.Element;

public class SingleSelectCommand implements Command {
	private Controller controller;
	private Point p;
	
	public SingleSelectCommand(Point p) {
		this.p = p;
	}
	
	@Override
	public void execute() {
		controller.select(p);
	}

	@Override
	public void setController(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void undo() {
		// Not undo-able.
	}
	
	@Override
	public boolean isUndoable() {
        return false;
    }
}
