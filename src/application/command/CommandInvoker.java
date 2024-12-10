package command;

import java.util.Stack;

import controller.Controller;

public class CommandInvoker {
	private Controller controller;
	private Stack<Command> undoStack = new Stack<>();
	private Stack<Command> redoStack = new Stack<>();
	
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	public void execute(Command command) {
        command.setController(controller);
        command.execute();
        if (command.isUndoable()) {
            undoStack.push(command);
            redoStack.clear();
        }
	}
	
	public void undo() {
		if(!undoStack.isEmpty()) {
			Command command = undoStack.pop();
			command.undo();
			redoStack.push(command);
		}
	}
	
	public void redo() {
		if(!redoStack.isEmpty()) {
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
