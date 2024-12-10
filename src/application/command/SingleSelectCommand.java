package command;

import common.Point;
import controller.Controller;

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
	
}