package view;

import java.awt.event.ActionEvent;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import viewmodel.ViewModel;
import javafx.scene.layout.VBox;

public class View extends Application {
	private static ViewModel viewModel; 
	private Pane paintPane;
	
    @Override
    public void start(Stage primaryStage) {
    	paintPane = new Pane();
    	paintPane.setStyle("-fx-border-color: #000000; -fx-border-width: 2px;");
    	paintPane.setPrefSize(700, 500);
    	paintPane.setOnMouseClicked(event -> {
    		double x = event.getX();
    		double y = event.getY();
    		viewModel.handlePaneClick(x, y);
    	});
    	
        VBox windowsContainer = new VBox();
        windowsContainer.setStyle("-fx-border-color: #FF0000; -fx-border-width: 2px;");
        windowsContainer.setPrefSize(300, 500);
        VBox propertyWindow = new VBox();
        propertyWindow.setStyle("-fx-border-color: #0000FF; -fx-border-width: 2px;");
        propertyWindow.setPrefSize(300, 350);
        GridPane toolWindow = new GridPane();
        toolWindow.setStyle("-fx-border-color: #00FF00; -fx-border-width: 2px;");
        toolWindow.setPrefSize(300, 150);
        Button selectButton = new Button("Select");
        selectButton.setPrefSize(150, 75);
        selectButton.setOnAction(actionEvent ->  {
        	viewModel.selectSelectionTool();
        });
        toolWindow.add(selectButton, 1, 0);
        Button createButton = new Button("Create");
        createButton.setPrefSize(150, 75);
        createButton.setOnAction(actionEvent ->  {
        	viewModel.selectCreationTool();
        });
        toolWindow.add(createButton, 0, 0);
        ComboBox<String> shapeComboBox = new ComboBox<String>();
        shapeComboBox.getItems().addAll("line", "rectangle", "ellipse", "image", "text");
        shapeComboBox.setOnAction((event) -> {
            String selectedShape = shapeComboBox.getSelectionModel().getSelectedItem();
            viewModel.setSelectedShape(selectedShape);
        });
        toolWindow.add(shapeComboBox, 0, 1);
        ComboBox<String> colorComboBox = new ComboBox<String>();
        colorComboBox.getItems().addAll("green", "blue", "red", "black");
        colorComboBox.setOnAction((event) -> {
            String SelectedColor = colorComboBox.getSelectionModel().getSelectedItem();
            viewModel.setSelectedColor(SelectedColor);
        });
        toolWindow.add(colorComboBox, 1, 1);
        windowsContainer.getChildren().addAll(propertyWindow, toolWindow);
        
        BorderPane container = new BorderPane();
        container.setLeft(paintPane);
        container.setRight(windowsContainer);

        // Set up the scene
        Scene scene = new Scene(container, 1010, 500);
        primaryStage.setTitle("Vector Graphics Editor");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        viewModel.setView(this);
    }
    
    public static void begin(String[] args, ViewModel viewModel) {
    	View.viewModel = viewModel;
    	View.launch(View.class, args);
    }
    
    public void attachPane(javafx.scene.Node newNode) {
    	newNode.setOnMousePressed(event -> {
    		viewModel.handleSelection();
    	});
    	paintPane.getChildren().add(newNode);
    }
}
