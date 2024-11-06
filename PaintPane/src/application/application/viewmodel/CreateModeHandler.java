package viewmodel;


public class CreateModeHandler implements MoustEventHandler {
	private static CreateModeHandler instance = new CreateModeHandler();
	public static CreateModeHandler getInstance() {
		return instance;
	}
	
	private NodeEventHandler adapter = NodeEventHandler.getInstance();
	
	private double startX, startY, endX, endY;
	
	@Override
	public void handlePressed(double x, double y) {
		startX = x;
		startY = y;
	}
	
	@Override
	public void handleReleased(double x, double y) {
		endX = x;
		endY = y;
		adapter.create(startX, startY, endX, endY);
	}
}
