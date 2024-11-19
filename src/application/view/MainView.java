package view;

import common.Observer;
import common.PropertyDTO;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainView extends Application {
	private DrawingPane drawingPane;
	private PropertyWindow propertyWindow;
	private ToolWindow toolWindow;
	private GridPane emptyPropertyWindow; // 도형이 선택되면 propertyWindow로 바꿔줘야 함
	
	@Override
	public void start(Stage primaryStage) {
		initialize(primaryStage);
	}

	
	private void initialize(Stage primaryStage) {
		drawingPane = new DrawingPane();
		FxElementManager.getInstance().setDrawingPane(drawingPane);
		toolWindow = new ToolWindow(drawingPane, primaryStage, 300, 150);
		CreateState.getInstance().setToolWindow(toolWindow);
		propertyWindow = new PropertyWindow(300, 350);
		emptyPropertyWindow = createEmptyWindow(300, 350);
		
		VBox windowsContainer = createVBox(300, 500);
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
