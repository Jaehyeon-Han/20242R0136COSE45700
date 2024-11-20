package command;

import controller.Controller;

public class TranslateCommand implements Command {
	private Controller controller;
	private double dx, dy;
	
	public TranslateCommand(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	@Override
	public void execute() {
		controller.translate(dx, dy);
	}

	@Override
	public void setController(Controller controller) {
		this.controller = controller;
	}

}
