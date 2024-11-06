package view;

import java.io.File;

import viewmodel.EventHandler;
import viewmodel.ViewUpdater;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class View extends Application {
	private static EventHandler viewModel;
	private Pane paintPane;
    GridPane emptyWindow = new GridPane();
    GridPane propertyWindow;
    private VBox windowsContainer;
    
    private static final int CLICK_THRESHOLD = 5; 
    // Threshold in pixels to distinguish between click and drag
    private double initialX = 0;
    private double initialY = 0;
    private Rectangle selectionBox;
    private Rectangle selectedBox;
    private boolean isSelected = false;
//    private boolean inTextMode = false; 
    
    private TextField selectedX;
    private TextField selectedY;
    private TextField selectedWidth;
    private TextField selectedHeight;
    private ColorPicker selectedColor;
    
    private double xValue;
    private double yValue;
	private double width;
	private double height;
	private GridPane toolWindow;
	private Button fowardButton;
	private Button backwardButton;
	

	@Override
	public void start(Stage primaryStage) {
		ViewUpdater.getInstance().setView(this);
		Initialize(primaryStage);
	}

	public static void begin(String[] args, EventHandler viewModel) {
		View.viewModel = viewModel;
		View.launch(View.class, args);
	}

	private void Initialize(Stage primaryStage) {
		emptyWindow.setPrefSize(300, 350);
		paintPane = createPaintPane();
		windowsContainer = createVBox(300, 500);
		propertyWindow = createGridPane(300, 350);
		toolWindow = createGridPane(300, 150);
		// TODO: 제네릭과 함수 사용하여 컴포넌트 만드는 과정 리팩터링

		// A window for displaying properties of the selected item
		Label xLabel = new Label("X: ");
		Label yLabel = new Label("Y: ");
		Label widthLabel = new Label("Width: ");
		Label heightLabel = new Label("Height: ");
		Label colorLabel = new Label("Color: ");
		Label[] labelArray = {xLabel, yLabel, widthLabel, heightLabel, colorLabel};
		
        selectedX = new TextField();
        selectedX.setOnKeyPressed(event -> {
        	if (event.getCode() == KeyCode.ENTER) {
                String input = selectedX.getText();
                double value;
                try {
                    value = Double.parseDouble(input);
                } catch (Exception e) {
                	selectedX.setText(Double.toString(xValue));
                    return;
                }
                 viewModel.changeX(value);
            }
        });
        selectedY = new TextField();
        selectedY.setOnKeyPressed(event -> {
        	if (event.getCode() == KeyCode.ENTER) {
                String input = selectedY.getText();
                double value;
                try {
                    value = Double.parseDouble(input);
                } catch (Exception e) {
                	selectedY.setText(Double.toString(yValue));
                    return;
                }
                 viewModel.changeY(value);
            }
        });
        selectedWidth = new TextField();
        selectedWidth.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String input = selectedWidth.getText();
                double value;
                try {
                    value = Double.parseDouble(input);
                } catch (Exception e) {
                    selectedWidth.setText(Double.toString(width));
                    return;
                }
                viewModel.changeWidth(value);
            }
        });

        selectedHeight = new TextField();
        selectedHeight.setOnKeyPressed(event -> {
        	if (event.getCode() == KeyCode.ENTER) {
                String input = selectedHeight.getText();
                double value;
                try {
                    value = Double.parseDouble(input);
                } catch (Exception e) {
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
			viewModel.selectSelectMode();
		});
		toolWindow.add(selectButton, 1, 0);
		Button createButton = new Button("Create");
		createButton.setPrefSize(100, 40);
		createButton.setOnAction(actionEvent -> {
			viewModel.selectCreateMode();
		});
		toolWindow.add(createButton, 0, 0);
		ComboBox<String> shapeComboBox = new ComboBox<String>();
		shapeComboBox.getItems().addAll("line", "rectangle", "ellipse", "image", "text");
		shapeComboBox.setValue("line");
		shapeComboBox.setOnAction(actionEvent -> {
			if(shapeComboBox.getValue().equals("image")) {
				FileChooser fileChooser = new FileChooser();
	            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
	            File selectedFile = fileChooser.showOpenDialog(primaryStage);
	            if (selectedFile != null) {
	                selectedFile.getAbsolutePath();
	                viewModel.setImageFile(selectedFile);
	            }
			}
			viewModel.selectType(shapeComboBox.getValue());
		});
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
		
		fowardButton = new Button("Forward");
		fowardButton.setPrefSize(100, 40);
		fowardButton.setOnAction(actionEvent -> {
			viewModel.forward();
		});
		
		backwardButton = new Button("Backward");
		backwardButton.setPrefSize(100, 40);
		backwardButton.setOnAction(actionEvent -> {
			viewModel.backward();
		});
		
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
			toolWindow.add(fowardButton, 0, 2);
			toolWindow.add(backwardButton, 1, 2);
		} else {
			windowsContainer.getChildren().remove(propertyWindow);
			windowsContainer.getChildren().add(0, emptyWindow);
			toolWindow.getChildren().remove(fowardButton);
			toolWindow.getChildren().remove(backwardButton);
		}
	}
	
	public void attachSelectedBox(double x1, double y1, double width, double height) {
		selectedBox = new Rectangle(x1, y1, width, height);
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

	public void updateX(double value) {
		xValue = value;
		selectedX.setText(Double.toString(value));
	}
	public void updateY(double value) {
		yValue = value;
		selectedY.setText(Double.toString(value));
	}
	public void updateWidth(double value) {
		width = value;
		selectedWidth.setText(Double.toString(value));
	}
	public void updateHeight(double value) {
		height = value;
		selectedHeight.setText(Double.toString(value));
	}
	public void updateColor(Color color) {
		selectedColor.setValue(color);
	}
	
	public void reOrder(Node node, int index) {
		paintPane.getChildren().remove(node);
		paintPane.getChildren().add(index, node);
	}
}
