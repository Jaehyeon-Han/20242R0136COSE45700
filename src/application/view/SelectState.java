package view;

public class SelectState implements ToolState {
	private double startX, startY, endX, endY;
	private static final int CLICK_THRESHOLD = 10; 
	
	@Override
	public void handleMousePressed(double x, double y) {
		startX = x;
		startY = y;
	}

	@Override
	public void handleMouseReleased(double x, double y) {
		endX = x;
		endY = y;
		
		double deltaX = endX - startX;
        double deltaY = endY - startY;

        if (Math.abs(deltaX) > CLICK_THRESHOLD || Math.abs(deltaY) > CLICK_THRESHOLD) {
        	// 다중선택
        } else {
        	// 단일선택
        }
	}
	
	
	// Singleton
	private SelectState() {
	}
	
	private static class SelectStateHelper {
		private static SelectState INSTANCE = new SelectState();
	}
	
	public static SelectState getInstance() {
		return SelectStateHelper.INSTANCE;
	}
}