import view.View;
import viewmodel.Adapter;
import viewmodel.CreationTool;
import viewmodel.SelectionTool;
import viewmodel.ViewModel;

public class Main {
	public static void main(String[] args) {
		ViewModel viewModel = new ViewModel();
		Adapter.getInstance().setViewModel(viewModel);
		CreationTool.getInstance().setViewModel(viewModel);
		SelectionTool.getInstance().setViewModel(viewModel);
		View.begin(args, viewModel);
	}
}
