package command;

import controller.Controller;

public interface Command {
	void execute();
	void undo();
	void setController(Controller controller);
	
	default boolean isUndoable() {
        return true;
    }
}
