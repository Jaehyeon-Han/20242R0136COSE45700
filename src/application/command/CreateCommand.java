package command;

import common.PropertyDTO;
import controller.Controller;

public class CreateCommand implements Command {
	private Controller controller;
	private PropertyDTO dto;
	
	public CreateCommand(PropertyDTO dto) {
		this.dto = dto;
	}
	
	@Override
	public void execute() {
		controller.createElement(dto);
	}

	@Override
	public void setController(Controller controller) {
		this.controller = controller;
	}
}
