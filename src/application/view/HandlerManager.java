package view;

import common.Point;
import javafx.scene.shape.Circle;
import model.Element;
import observer.SelectedStateObserver;
import observer.SelectedElementObserver;
import view.state.ResizeState;

public class HandlerManager implements SelectedStateObserver, SelectedElementObserver {
	private DrawingPane drawingPane;
	private Circle handler;
	private static final double HANDLER_RADIUS = 5;
    
	// Setter
	public void setDrawingPane(DrawingPane drawingPane) {
		this.drawingPane = drawingPane;
	}
	
	// Handler Functions
	private void attachHandler(Element selectedElement) {
		Point point = selectedElement.getQ();
		String id = selectedElement.getId();
		
		Circle handler = new Circle(point.getX(), point.getY(), HANDLER_RADIUS);
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

	// Observing Selected State
	@Override
	public void onSelect(Element selectedElement) {
		detachHandler();
		attachHandler(selectedElement);
	}

	@Override
	public void onUnSelect() {
		detachHandler();
	}
	
	// Observing Selected Element 
	@Override
	public void onChange(Element selectedElement) {
		updateHandler(selectedElement.getQ());
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
