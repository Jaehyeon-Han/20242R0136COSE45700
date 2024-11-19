package view;

import common.Observer;
import common.PropertyDTO;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainView extends Application implements Observer {
	private DrawingPane drawingPane;
	private PropertyWindow propertyWindow;
	private ToolWindow toolWindow;
	private GridPane emptyPropertyWindow;
	private VBox windowsContainer;
	
	@Override
	public void start(Stage primaryStage) {
		drawingPane = new DrawingPane();
		FxElementManager.getInstance().setDrawingPane(drawingPane);
		toolWindow = new ToolWindow(drawingPane, primaryStage, 300, 150);
		CreateState.getInstance().setToolWindow(toolWindow);
		propertyWindow = new PropertyWindow(300, 350);
		emptyPropertyWindow = createEmptyWindow(300, 350);
		
		windowsContainer = createVBox(300, 500);
		windowsContainer.getChildren().addAll(emptyPropertyWindow, toolWindow);

		BorderPane container = new BorderPane();
		container.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 18;");
		container.setLeft(drawingPane);
		container.setRight(windowsContainer);

		// Set up the scene
		Scene scene = new Scene(container, 1010, 500);
		primaryStage.setTitle("Graphics Editor");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@Override
	public void onSelect(PropertyDTO dto) {
		if(dto != null) {
			windowsContainer.getChildren().remove(emptyPropertyWindow);
			windowsContainer.getChildren().add(0, propertyWindow);
		}
		else {
			windowsContainer.getChildren().remove(propertyWindow);
			windowsContainer.getChildren().add(0, emptyPropertyWindow);
		}
		
	}

	private VBox createVBox(int width, int height) {
		VBox vBox = new VBox();
		vBox.setPrefSize(width, height);
		return vBox;
	}

	private GridPane createEmptyWindow(int width, int height) {
		GridPane emptyWindow = new GridPane();
		emptyWindow.setPrefSize(width, height);
		emptyWindow.setStyle("-fx-border-color: #000000; -fx-border-width: 1px;");
		return emptyWindow;
	}


}
