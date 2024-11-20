package command;

import common.Point;
import controller.Controller;

public class ResizeCommand implements Command {
	private Controller controller;
	private Point newQ;
	
	public ResizeCommand(Point newQ) {
		this.newQ = newQ;
	}
	
	@Override
	public void execute() {
		controller.resize(newQ);
	}

	@Override
	public void setController(Controller controller) {
		this.controller = controller;
	}
}
