package view;

import common.Observer;
import common.PropertyDTO;
import controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Element;
import view.fxmodel.FxElementManager;
import view.state.CreateState;
import view.state.SelectState;

public class MainView extends Application implements Observer {
	private static Controller controller; // To add this as an observer
	
	private DrawingPane drawingPane;
	private PropertyWindow propertyWindow;
	private ToolWindow toolWindow;
	private GridPane emptyPropertyWindow;
	private VBox windowsContainer;
	private boolean selected = false;
	
	@Override
	public void start(Stage primaryStage) {
		drawingPane = new DrawingPane();
		toolWindow = new ToolWindow(drawingPane, primaryStage, 300, 150);
		propertyWindow = new PropertyWindow(300, 350);
		emptyPropertyWindow = createEmptyWindow(300, 350);
		
		FxElementManager.getInstance().setDrawingPane(drawingPane);
		HandlerManager.getInstance().setDrawingPane(drawingPane);
		SelectState.getInstance().setDrawingPane(drawingPane);
		drawingPane.setToolWindow(toolWindow);
		
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
		
		controller.addObserver(this);
	}

	@Override
	public void onSelect(Element element) {
		if(!selected) {
			windowsContainer.getChildren().remove(emptyPropertyWindow);
			windowsContainer.getChildren().add(0, propertyWindow);
		}
		selected = true;
		
		propertyWindow.onSelect(element);
	}
	
	@Override 
	public void onChange(Element element) {
		propertyWindow.onSelect(element);
	}
	
	@Override
	public void onUnselect() {
		if(selected) {
			windowsContainer.getChildren().remove(propertyWindow);
			windowsContainer.getChildren().add(0, emptyPropertyWindow);
			selected = false;
		}
	} // OnSelect를 관찰하기 보다는 ToolState가 SelectState로 바뀔 때 설정하는 게 맞다.
	

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

	public static void myLaunch(Controller controller, String[] args) {
		MainView.controller = controller;
		MainView.launch(args);
	}
}
