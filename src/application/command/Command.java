package command;

import controller.Controller;

public interface Command {
	void execute();
	void setController(Controller controller);
}
