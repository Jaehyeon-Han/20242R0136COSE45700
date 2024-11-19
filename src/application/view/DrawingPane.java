package view;

import common.Observer;
import common.PropertyDTO;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import view.FxModel.FxElement;

public class DrawingPane extends Pane implements Observer {
	private ToolState currentState = CreateState.getInstance();
	private Circle handler;
	
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
