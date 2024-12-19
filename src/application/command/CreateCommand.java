package command;

import java.util.UUID;

import common.CreateInfo;
import model.ElementManager;

public class CreateCommand implements Command {
	private CreateInfo info; 
	
	public CreateCommand(CreateInfo info) {
		this.info = info;
	}
	
	@Override
	public void execute() {
		String id = UUID.randomUUID().toString();
		info.setId(id);
		ElementManager.getInstance().add(info);
	}
	
	public void undo() {
		ElementManager.getInstance().remove(info.getId());
	}
}
