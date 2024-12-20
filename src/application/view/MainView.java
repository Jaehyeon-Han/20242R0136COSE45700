package view;

import controller.ElementSelector;
import controller.SelectedElementManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.fxmodel.FxElementManager;

public class MainView extends Application {
	private DrawingPane drawingPane;
	private PropertyWindow propertyWindow;
	private ToolWindow toolWindow;
	
	@Override
	public void start(Stage primaryStage) {
		drawingPane = new DrawingPane();
		toolWindow = new ToolWindow(drawingPane, primaryStage, 300, 150);
		propertyWindow = new PropertyWindow(300, 350);
		
		setModelDependencies();
		setViewDependencies();
		
		VBox windowsContainer = createVBox(300, 500);
		windowsContainer.getChildren().addAll(propertyWindow, toolWindow);

		BorderPane container = new BorderPane();
		container.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 18;");
		container.setLeft(drawingPane);
		container.setRight(windowsContainer);

		Scene scene = new Scene(container, 1010, 500);
		primaryStage.setTitle("Vector Graphic Editor");
		primaryStage.setScene(scene);
		primaryStage.show();
	}


	private void setModelDependencies() {
		ElementSelector.getInstance().addObserver(propertyWindow);
		SelectedElementManager.getInstance().addObserver(propertyWindow);
	}

	private void setViewDependencies() {
		FxElementManager.getInstance().setDrawingPane(drawingPane);
		HandlerManager.getInstance().setDrawingPane(drawingPane);
		drawingPane.setToolWindow(toolWindow);
	}

	private VBox createVBox(int width, int height) {
		VBox vBox = new VBox();
		vBox.setPrefSize(width, height);
		return vBox;
	}
}
