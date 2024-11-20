package view.state;

public class MultiSelectState implements ToolState {
	@Override
	public void handleMousePressed(double x, double y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleMouseDragged(double x, double y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleMouseReleased(double x, double y) {
		// TODO Auto-generated method stub

	}

	// Singleton
	private MultiSelectState() {
	}

	private static class MultiSelectStateHelper {
		private static MultiSelectState INSTANCE = new MultiSelectState();
	}

	public static MultiSelectState getInstance() {
		return MultiSelectStateHelper.INSTANCE;
	}
}
