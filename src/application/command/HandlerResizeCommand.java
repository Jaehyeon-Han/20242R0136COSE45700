package command;

import common.Point;
import controller.Controller;

public class HandlerResizeCommand implements Command {
	private Controller controller;
	private Point oldQ, newQ;
	private String id;
	
	public HandlerResizeCommand(String id, Point oldQ, Point newQ) {
		this.id = id;
		this.oldQ = oldQ;
		this.newQ = newQ;
	}
	
	@Override
	public void execute() {
		controller.resize(id, newQ);
	}
	
	@Override
	public void setController(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void undo() {
		controller.resize(id, oldQ);
	}
}
