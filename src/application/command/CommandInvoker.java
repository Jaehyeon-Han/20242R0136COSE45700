package command;

import java.util.Stack;

public class CommandInvoker {
	private Stack<Command> undoStack = new Stack<>();
	private Stack<Command> redoStack = new Stack<>();

	public void execute(Command command) {
		command.execute();
		undoStack.push(command);
		redoStack.clear();
	}

	public void undo() {
		if (!undoStack.isEmpty()) {
			Command command = undoStack.pop();
			command.undo();
			redoStack.push(command);
		}
	}

	public void redo() {
		if (!redoStack.isEmpty()) {
			Command command = redoStack.pop();
			execute(command);
		}
	}

	// Singleton
	private CommandInvoker() {
	}

	private static class CommandInvokerHelper {
		private static CommandInvoker INSTANCE = new CommandInvoker();
	}

	public static CommandInvoker getInstance() {
		return CommandInvokerHelper.INSTANCE;
	}
}
