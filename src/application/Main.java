import command.CommandInvoker;
import controller.Controller;
import controller.ElementManager;
import view.FxElementManager;
import view.MainView;
import view.state.SelectState;

public class Main {
	public static void main(String[] args) {
		Controller controller = new Controller();
		CommandInvoker.getInstance().setController(controller);
		controller.addObserver(FxElementManager.getInstance());
		controller.addObserver(SelectState.getInstance());
		MainView.myLaunch(controller, args);
	}
}
