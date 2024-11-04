package view;

import java.awt.event.ActionEvent;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import viewmodel.Adapter;
import viewmodel.ViewModel;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class View extends Application {
	private static ViewModel viewModel;
	private Pane paintPane;
    private static final int CLICK_THRESHOLD = 5; 
    // Threshold in pixels to distinguish between click and drag
    private double initialX = 0;
    private double initialY = 0;
    private Rectangle selectionBox;
    private Rectangle selectedBox;
    private boolean isSelected = false;
    private double xValue;
    GridPane emptyWindow = new GridPane();
    GridPane propertyWindow;
    private TextField selectedX;
    private TextField selectedY;
    private TextField selectedWidth;
    private TextField selectedHeight;
    private ColorPicker selectedColor;
	private Double yValue;
	private Double width;
	private Double height;
	private VBox windowsContainer;
    
	@Override
	public void start(Stage primaryStage) {
		Adapter.getInstance().setView(this);
		Initialize(primaryStage);
	}

	public static void begin(String[] args, ViewModel viewModel) {
		View.viewModel = viewModel;
		View.launch(View.class, args);
	}

	private void Initialize(Stage primaryStage) {
		emptyWindow.setPrefSize(300, 350);
		paintPane = createPaintPane();
		windowsContainer = createVBox(300, 500);
		propertyWindow = createGridPane(300, 350);
		GridPane toolWindow = createGridPane(300, 150);
		// TODO: 제네릭과 함수 사용하여 컴포넌트 만드는 과정 리팩터링

		// A window for displaying properties of the selected item
		Label xLabel = new Label("X: ");
		Label yLabel = new Label("Y: ");
		Label widthLabel = new Label("Width: ");
		Label heightLabel = new Label("Height: ");
		Label colorLabel = new Label("Color: ");
		Label[] labelArray = {xLabel, yLabel, widthLabel, heightLabel, colorLabel};
		
        selectedX = new TextField();
        selectedX.focusedProperty().addListener((observable, oldValue, newValue) -> {
        	String input = selectedX.getText();
        	double value;
        	if (!newValue) {
            	try {
            		value = Double.parseDouble(input);
            	} catch(Exception e) {
            		selectedX.setText(Double.toString(xValue));
            		return;
            	}
                 viewModel.changeX(value);
            }
        });
        selectedY = new TextField();
        selectedY.focusedProperty().addListener((observable, oldValue, newValue) -> {
        	String input = selectedY.getText();
        	double value;
        	if (!newValue) {
            	try {
            		value = Double.parseDouble(input);
            	} catch(Exception e) {
            		selectedY.setText(Double.toString(yValue));
            		return;
            	}
                 viewModel.changeY(value);
            }
        });
        selectedWidth = new TextField();
        selectedWidth.focusedProperty().addListener((observable, oldValue, newValue) -> {
        	String input = selectedWidth.getText();
        	double value;
        	if (!newValue) {
            	try {
            		value = Double.parseDouble(input);
            	} catch(Exception e) {
            		selectedWidth.setText(Double.toString(width));
            		return;
            	}
                 viewModel.changeWidth(value);
            }
        });
        selectedHeight = new TextField();
        selectedHeight.focusedProperty().addListener((observable, oldValue, newValue) -> {
        	String input = selectedHeight.getText();
        	double value;
        	if (!newValue) {
            	try {
            		value = Double.parseDouble(input);
            	} catch(Exception e) {
            		selectedHeight.setText(Double.toString(height));
            		return;
            	}
                 viewModel.changeHeight(value);
            }
        });
        selectedColor = new ColorPicker();
        selectedColor.setOnAction(actionEvent -> {
			Color color = selectedColor.getValue();
			double r = color.getRed() * 255;
			double g = color.getGreen() * 255;
			double b = color.getBlue() * 255;
			viewModel.changeColor((int) r, (int) g, (int) b);
		});
        Node[] valueArray = {selectedX, selectedY, selectedWidth, selectedHeight, selectedColor};
        for(int i = 0; i < labelArray.length; ++i) {
        	propertyWindow.add(labelArray[i], 0, i);
        	propertyWindow.add(valueArray[i], 1, i);
        }
        
		Button selectButton = new Button("Select");
		selectButton.setPrefSize(100, 40);
		selectButton.setOnAction(actionEvent -> {
			viewModel.selectSelectionTool();
		});
		toolWindow.add(selectButton, 1, 0);
		Button createButton = new Button("Create");
		createButton.setPrefSize(100, 40);
		createButton.setOnAction(actionEvent -> {
			viewModel.selectCreationTool();
		});
		toolWindow.add(createButton, 0, 0);
		ComboBox<String> shapeComboBox = new ComboBox<String>();
		shapeComboBox.getItems().addAll("line", "rectangle", "ellipse", "image", "text");
		shapeComboBox.setValue("line");
		shapeComboBox.setOnAction(actionEvent -> {
			viewModel.selectType(shapeComboBox.getValue());
		});
		Button fowardButton = new Button("Forward");
		fowardButton.setPrefSize(100, 40);
		fowardButton.setOnAction(actionEvent -> {
			viewModel.foward();
		});
		Button backwardButton = new Button("Backward");
		backwardButton.setPrefSize(100, 40);
		backwardButton.setOnAction(actionEvent -> {
			viewModel.foward();
		});
		
		// TODO: image and text

		toolWindow.add(shapeComboBox, 0, 1);
		ColorPicker colorPicker = new ColorPicker();
		colorPicker.setValue(Color.BLACK);
		colorPicker.setOnAction(actionEvent -> {
			Color color = colorPicker.getValue();
			double r = color.getRed() * 255;
			double g = color.getGreen() * 255;
			double b = color.getBlue() * 255;
			viewModel.selectColor((int) r, (int) g, (int) b);
		});
		toolWindow.add(colorPicker, 1, 1);
		windowsContainer.getChildren().addAll(emptyWindow, toolWindow);

		BorderPane container = new BorderPane();
		container.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 18;");
		container.setLeft(paintPane);
		container.setRight(windowsContainer);

		// Set up the scene
		Scene scene = new Scene(container, 1010, 500);
		primaryStage.setTitle("Vector Graphics Editor");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private GridPane createGridPane(double width, double height) {
		GridPane toolWindow = new GridPane();
		toolWindow.setStyle("-fx-border-color: #00FF00; -fx-border-width: 1px;");
		toolWindow.setPrefSize(width, height);
		return toolWindow;
	}

	private VBox createVBox(double width, double height) {
		VBox windowsContainer = new VBox();
		windowsContainer.setStyle("-fx-border-color: #FF0000; -fx-border-width: 1px;");
		windowsContainer.setPrefSize(width, height);
		return windowsContainer;
	}

	private Pane createPaintPane() {
		Pane paintPane = new Pane();
		paintPane.setStyle("-fx-border-color: #000000; -fx-border-width: 1px;");
		paintPane.setPrefSize(700, 500);
		paintPane.setOnMousePressed(event -> {
			initialX = event.getX();
	        initialY = event.getY();
			viewModel.handleMousePressed(event.getX(), event.getY());
			System.out.println("pane: X - " + initialX + ", Y - " + initialY);
			if(!isSelected) {
				selectionBox = new Rectangle(initialX, initialY, 0, 0);
	            selectionBox.setStroke(Color.BLUE);
	            selectionBox.setFill(Color.TRANSPARENT);
	            selectionBox.getStrokeDashArray().addAll(5.0, 5.0);
	            paintPane.getChildren().add(selectionBox);
			}
		});
		paintPane.setOnMouseDragged(event -> {
			if(!isSelected) {
				double endX = event.getX();
	            double endY = event.getY();

	            selectionBox.setX(Math.min(initialX, endX));
	            selectionBox.setY(Math.min(initialY, endY));
	            selectionBox.setWidth(Math.abs(endX - initialX));
	            selectionBox.setHeight(Math.abs(endY - initialY));;
			}
        });
		
		paintPane.setOnMouseReleased(event -> {
			double deltaX = event.getX() - initialX;
	        double deltaY = event.getY() - initialY;

	        if (Math.abs(deltaX) > CLICK_THRESHOLD || Math.abs(deltaY) > CLICK_THRESHOLD) {
	        	viewModel.handleMouseReleased(event.getX(), event.getY());
	        }
	        paintPane.getChildren().remove(selectionBox);
		});
		return paintPane;
	}
	
	public void addNode(Node node) {
		paintPane.getChildren().add(node);
	}
	
	public void changeSelectStatus(boolean selected) {
		isSelected = selected;
		if(selected) {
			windowsContainer.getChildren().remove(emptyWindow);
			windowsContainer.getChildren().add(0, propertyWindow);
		} else {
			windowsContainer.getChildren().remove(propertyWindow);
			windowsContainer.getChildren().add(0, emptyWindow);
		}
	}
	
	public void attachSelectedBox(double x1, double y1, double x2, double y2) {
		selectedBox = new Rectangle(x1, y1, x2-x1, y2-y1);
		selectedBox.setStroke(Color.BLUE);
		selectedBox.setFill(Color.TRANSPARENT);
        paintPane.getChildren().add(selectedBox);
	}
	public void detachSelectedBox() {
		paintPane.getChildren().remove(selectedBox);
	}
	public void moveSelectedBox(double dx, double dy) {
		selectedBox.setLayoutX(selectedBox.getLayoutX() + dx);
		selectedBox.setLayoutY(selectedBox.getLayoutY() + dy);
	}

	public void updateX(Double value) {
		xValue = value;
		selectedX.setText(Double.toString(value));
	}
	public void updateY(Double value) {
		yValue = value;
		selectedY.setText(Double.toString(value));
	}
	public void updateWidth(Double value) {
		width = value;
		selectedWidth.setText(Double.toString(value));
	}
	public void updateHeight(Double value) {
		height = value;
		selectedHeight.setText(Double.toString(value));
	}
	
	public void updateColor(Color color) {
		selectedColor.setValue(color);
	}
}
