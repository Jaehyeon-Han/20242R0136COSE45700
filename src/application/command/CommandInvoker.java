package command;

import java.util.Stack;

import controller.Controller;

public class CommandInvoker {
	private Controller controller;
	private Stack<Command> undoStack = new Stack<>();
	
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	public void execute(Command command) {
		command.setController(controller);
		command.execute();
		undoStack.push(command);
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
