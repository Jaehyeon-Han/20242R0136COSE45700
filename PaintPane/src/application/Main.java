import view.View;
import viewmodel.EventHandler;

public class Main {
	public static void main(String[] args) {
		EventHandler viewModel = new EventHandler();
		View.begin(args, viewModel);
	}
}
