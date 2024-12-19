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
		ElementSelector selector = ElementSelector.getInstance();

		ElementManager.getInstance().addObserver(FxElementManager.getInstance());

		selector.addObserver(FxElementManager.getInstance());
		selector.addObserver(HandlerManager.getInstance());

		SelectedElementManager.getInstance().addObserver(HandlerManager.getInstance());
	}
}
