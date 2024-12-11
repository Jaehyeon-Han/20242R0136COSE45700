import command.CommandInvoker;
import controller.Controller;
import model.ElementManager;
import view.MainView;
import view.fxmodel.FxElementManager;
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
