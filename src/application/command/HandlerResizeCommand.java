package command;

import common.Point;
import model.ElementManager;

public class HandlerResizeCommand implements Command {
	private String id;
	private Point oldQ, newQ;
	
	public HandlerResizeCommand(String id, Point oldQ, Point newQ) {
		this.id = id;
		this.oldQ = oldQ;
		this.newQ = newQ;
	}
	
	@Override
	public void execute() {
		ElementManager.getInstance().getElement(id).setQ(newQ);
	}

	@Override
	public void undo() {
		ElementManager.getInstance().getElement(id).setQ(oldQ);
	}
}
