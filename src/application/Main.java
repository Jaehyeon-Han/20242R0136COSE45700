import controller.ElementSelector;
import controller.SelectedElementManager;
import model.ElementManager;
import view.HandlerManager;
import view.MainView;
import view.fxmodel.FxElementManager;
import view.state.SelectState;

public class Main {
	public static void main(String[] args) {
		setObservers();
		MainView.launch(MainView.class, args);
	}

	private static void setObservers() {
		FxElementManager fxElementManager = FxElementManager.getInstance();
		ElementSelector elementSelector = ElementSelector.getInstance();
		HandlerManager handlerManager = HandlerManager.getInstance();
		
		ElementManager.getInstance().addObserver(fxElementManager);
		
		elementSelector.addObserver(fxElementManager);
		elementSelector.addObserver(handlerManager);

		SelectedElementManager.getInstance().addObserver(handlerManager);
	}
}
