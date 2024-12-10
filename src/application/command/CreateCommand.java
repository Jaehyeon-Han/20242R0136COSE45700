package command;

import common.PropertyDTO;
import controller.Controller;
import model.Element;
import model.ElementFactory;

public class CreateCommand implements Command {
	private Controller controller;
	private PropertyDTO dto;
	private Element newElement; 
	
	public CreateCommand(PropertyDTO dto) {
		this.dto = dto;
	}
	
	@Override
	public void execute() {
        newElement = ElementFactory.getInstance().create(
    		dto.getType(),
            dto.getP(), 
            dto.getQ(), 
            dto.getColor(), 
            dto.getImageFile(), 
            dto.getText()
        );
		
		controller.addElement(newElement);
	}
	
	public void undo() {
		controller.removeElement(newElement);
	}

	@Override
	public void setController(Controller controller) {
		this.controller = controller;
	}
}
