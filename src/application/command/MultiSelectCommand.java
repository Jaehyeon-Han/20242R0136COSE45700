package command;

import common.Point;
import controller.Controller;

public class MultiSelectCommand implements Command {
	private Point p, q;
	private Controller controller;

	public MultiSelectCommand(Point p, Point q) {
		this.p = p;
		this.q = q;
	}
	
	@Override
	public void execute() {
		controller.select(p, q);
	}

	@Override
	public void undo() {
		// Not undo-able
	}
	
	@Override
	public boolean isUndoable() {
        return false;
    }

	@Override
	public void setController(Controller controller) {
		this.controller = controller;
	}

}
