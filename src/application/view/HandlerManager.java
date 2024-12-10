package view;

import common.Point;
import javafx.scene.shape.Circle;
import view.fxmodel.FxElement;
import view.fxmodel.FxElementManager;
import view.state.ResizeState;

public class HandlerManager {
	private DrawingPane drawingPane;
	private Circle handler;
	private static final double HANDLER_RADIUS = 5;
    
	public void attachHandler(Point point) {
		Circle handler = new Circle(point.getX(), point.getY(), HANDLER_RADIUS);
		String id = FxElementManager.getInstance().getSelectedId();
		this.handler = handler;
		handler.setOnMousePressed(event -> {
			drawingPane.setCurrentState(
					new ResizeState(drawingPane, id, point.getX(), point.getY()));
		});
		drawingPane.add(handler);
	}
	
	public void detachHandler() {
		if(handler != null) {
			drawingPane.remove(handler);
		}
		handler = null;
	}
	
	public void updateHandler(Point newPoint) {
		handler.setCenterX(newPoint.getX());
		handler.setCenterY(newPoint.getY());
	}
	
	public void setDrawingPane(DrawingPane drawingPane) {
		this.drawingPane = drawingPane;
	}
	
	
    // Singleton
    private HandlerManager() {}

    private static class HandlerManagerHelper {
        private static final HandlerManager INSTANCE = new HandlerManager();
    }

    public static HandlerManager getInstance() {
        return HandlerManagerHelper.INSTANCE;
    }

}
