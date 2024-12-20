package view;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import view.state.CreateState;
import view.state.ToolState;

public class DrawingPane extends Pane {
	private ToolState currentState = new CreateState(this);
	private ToolWindow toolWindow;
	
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
	
	public void add(Node node) {
		this.getChildren().add(node);
	}
	
	public void remove(Node node) {
		if(node != null) {
			this.getChildren().remove(node);
		}
	}
	
	public void setCurrentState(ToolState state) {
		currentState = state;
	}
	
	public void setToolWindow(ToolWindow toolWindow) {
		this.toolWindow = toolWindow;
	}
	
	public ToolWindow getToolWindow() {
		return this.toolWindow;
	}
}
