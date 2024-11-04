package viewmodel;


public class CreationTool implements Tool {
	private static CreationTool instance = new CreationTool();
	private ViewModel viewModel;
	private Adapter adapter = Adapter.getInstance();
	
	private double x1, y1, x2, y2;
	
	public static CreationTool getInstance() {
		return instance;
	}

	@Override
	public void handlePressed(double x, double y) {
		x1 = x;
		y1 = y;
		System.out.println("Pressed");
	}
	
	@Override
	public void handleReleased(double x, double y) {
		x2 = x;
		y2 = y;
		System.out.println("Released");
		adapter.create(viewModel.selectedType, x1, y1, x2, y2, viewModel.selectedColor, null);
	}
	
	
	public void setViewModel(ViewModel viewModel) {
		this.viewModel = viewModel;
	}

	
}
