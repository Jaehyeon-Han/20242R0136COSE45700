package view;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import view.FxModel.FxElement;

public class DrawingPane extends Pane {
	ToolState currentState = CreateState.getInstance();
	
	public DrawingPane() {
		this.setStyle("-fx-border-color: #000000; -fx-border-width: 1px;");
		this.setPrefSize(700, 500);
		
		this.setOnMousePressed(event -> {
			currentState.handleMousePressed(event.getX(), event.getY());
		});
		this.setOnMouseReleased(event -> {
			currentState.handleMouseReleased(event.getX(), event.getY());
		});
	}
	
	public void add(FxElement element) {
		this.getChildren().add((Node) element);
	}
	
	public void setCreateState() {
		currentState = CreateState.getInstance();
	}
	
	public void setSelectState() {
		currentState = SelectState.getInstance();
	}
}
