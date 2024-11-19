package view;

import java.io.File;

import common.Color;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ToolWindow extends GridPane {
	DrawingPane drawingPane;
	
	ComboBox<String> shapeComboBox;
	ColorPicker colorPicker;
	File selectedFile;
	
	public ToolWindow(DrawingPane drawingPane, Stage primaryStage, int width, int height) {
		this.drawingPane = drawingPane;
		
		Button selectButton = createSelectButton();
		Button createButton = createCreateButton();
		shapeComboBox = createTypeComboBox(primaryStage);
		colorPicker = createColorPicker();

		this.add(selectButton, 1, 0);
		this.add(createButton, 0, 0);
		this.add(shapeComboBox, 0, 1);
		this.add(colorPicker, 1, 1);

		this.setStyle("-fx-border-color: #000000; -fx-border-width: 1px;");
		this.setPrefSize(width, height);
	}
	
	public String getType() {
		return shapeComboBox.getValue();
	}
	
	public Color getColor() {
		javafx.scene.paint.Color color = colorPicker.getValue();
		int r = (int) (color.getRed() * 255);
		int g = (int) (color.getGreen() * 255);
		int b = (int) (color.getBlue() * 255);
		return new Color(r, g, b);
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
			drawingPane.setCreateState();
		});
		return createButton;
	}

	private Button createSelectButton() {
		Button selectButton = new Button("Select");
		selectButton.setPrefSize(100, 40);
		selectButton.setOnAction(actionEvent -> {
			drawingPane.setSelectState();
		});
		return selectButton;
	}
}