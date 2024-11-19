package controller;

public interface Command {
	void execute();
	void setController(Controller controller);
}
