import controller.CommandInvoker;
import controller.Controller;
import controller.ElementManager;
import view.FxElementManager;
import view.MainView;

public class Main {
	public static void main(String[] args) {
		Controller controller = new Controller();
		CommandInvoker.getInstance().setController(controller);
		controller.addObserver(FxElementManager.getInstance());
		MainView.launch(MainView.class, args);
	}
}
