package view;

import common.Observer;
import common.Point;
import common.PropertyDTO;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import view.fxmodel.FxElement;
import view.state.CreateState;
import view.state.MultiSelectState;
import view.state.ResizeState;
import view.state.SelectState;
import view.state.ToolState;

public class DrawingPane extends Pane {
	private ToolState currentState = CreateState.getInstance();
	private Circle handler;
	private static final double HANDLER_RADIUS = 5;
	
	public DrawingPane() {
		this.setStyle("-fx-border-color: #000000; -fx-border-width: 1px;");
		this.setPrefSize(700, 500);
		initializeMouseEventListeners();
	}

	private void initializeMouseEventListeners() {
		this.setOnMousePressed(event -> {
			currentState.handleMousePressed(event.getX(), event.getY());
		});
		this.setOnMouseDragged(event -> {
			currentState.handleMouseDragged(event.getX(), event.getY());
		});
		this.setOnMouseReleased(event -> {
			currentState.handleMouseReleased(event.getX(), event.getY());
		});
	}
	
	public void add(FxElement element) {
		this.getChildren().add((Node) element);
	}
	
	public void attachHandler(Point point) {
		Circle handler = new Circle(point.getX(), point.getY(), HANDLER_RADIUS);
		this.handler = handler;
		handler.setOnMousePressed(event -> {
			System.out.println("To Resize State");
			currentState = new ResizeState(this);
		});
		this.getChildren().add(handler);
	}
	
	public void detachHandler() {
		if(handler != null) {
			this.getChildren().remove(handler);
		}
	}
	
	public void updateHandler(Point newPoint) {
		handler.setCenterX(newPoint.getX());
		handler.setCenterY(newPoint.getY());
	}

	// State Control -> To a separate class?
	public void setCreateState() {
		currentState = CreateState.getInstance();
		detachHandler();
		System.out.println("To Create state");
	}
	public void setSelectState() {
		currentState = SelectState.getInstance();
		System.out.println("To Select state");
	}
	public void setMultiSelectState() {
		currentState = MultiSelectState.getInstance();
		System.out.println("To MultiSelect state");
	}
	public ToolState getCurrentState() {
		return currentState;
	}
	public void setCurrentState(ToolState state) {
		currentState = state;
	}
}
