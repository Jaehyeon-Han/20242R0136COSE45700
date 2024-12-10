package view;

import java.io.File;

import command.CommandInvoker;
import common.Color;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import view.state.CreateState;
import view.state.SelectState;

public class ToolWindow extends GridPane {
	DrawingPane drawingPane;
	
	ComboBox<String> shapeComboBox;
	ColorPicker colorPicker;
	File selectedFile;
	
	public ToolWindow(DrawingPane drawingPane, Stage primaryStage, int width, int height) {
		this.drawingPane = drawingPane;
		
		Button selectButton = createSelectButton();
		Button createButton = createCreateButton();
		Button undoButton = createUndobutton();
		Button redoButton = createRedoButton();
		shapeComboBox = createTypeComboBox(primaryStage);
		colorPicker = createColorPicker();

		this.add(selectButton, 1, 0);
		this.add(createButton, 0, 0);
		this.add(shapeComboBox, 0, 1);
		this.add(colorPicker, 1, 1);
		this.add(undoButton, 0, 2);
		this.add(redoButton, 1, 2);

		this.setStyle("-fx-border-color: #000000; -fx-border-width: 1px;");
		this.setPrefSize(width, height);
	}

	public String getType() {
		return shapeComboBox.getValue();
	}
	
	public Color getColor() {
		return new Color(colorPicker.getValue());
	}
	
	public File getImageFile() {
		return selectedFile;
	}

	private ColorPicker createColorPicker() {
		ColorPicker colorPicker = new ColorPicker();
		colorPicker.setValue(javafx.scene.paint.Color.BLACK);
		return colorPicker;
	}

	private ComboBox<String> createTypeComboBox(Stage primaryStage) {
		ComboBox<String> shapeComboBox = new ComboBox<String>();
		shapeComboBox.getItems().addAll("line", "rectangle", "ellipse", "image", "text");
		shapeComboBox.setValue("line");
		shapeComboBox.setOnAction(actionEvent -> {
			if (shapeComboBox.getValue().equals("image")) {
				FileChooser fileChooser = new FileChooser();
				fileChooser.getExtensionFilters()
						.add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
				selectedFile = fileChooser.showOpenDialog(primaryStage);
			}
		});
		return shapeComboBox;
	}

	private Button createCreateButton() {
		Button createButton = new Button("Create");
		createButton.setPrefSize(100, 40);
		createButton.setOnAction(actionEvent -> {
			drawingPane.setCurrentState(new CreateState(drawingPane));
			HandlerManager.getInstance().detachHandler();
		});
		return createButton;
	}

	private Button createSelectButton() {
		Button selectButton = new Button("Select");
		selectButton.setPrefSize(100, 40);
		selectButton.setOnAction(actionEvent -> {
			System.out.println("Select State");
			drawingPane.setCurrentState(SelectState.getInstance());
		});
		return selectButton;
	}
	
	private Button createRedoButton() {
		Button redoButton = new Button("Redo");
		redoButton.setPrefSize(100, 40);
		redoButton.setOnAction(actionEvent -> {
			CommandInvoker.getInstance().redo();
		});
		return redoButton;
	}

	private Button createUndobutton() {
		Button undoButton = new Button("Undo");
		undoButton.setPrefSize(100, 40);
		undoButton.setOnAction(actionEvent -> {
			CommandInvoker.getInstance().undo();
		});
		return undoButton;
	}
}
