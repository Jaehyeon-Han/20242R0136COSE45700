package command;

import controller.Controller;

public class TranslateCommand implements Command {
	private Controller controller;
	private double dx, dy;
	private String id;
	
	public TranslateCommand(String id, double dx, double dy) {
		this.id = id;
		this.dx = dx;
		this.dy = dy;
	}
	
	@Override
	public void execute() {
		controller.translate(id, dx, dy);
	}

	@Override
	public void setController(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void undo() {
		controller.translate(id, -dx, -dy);
	}

}
