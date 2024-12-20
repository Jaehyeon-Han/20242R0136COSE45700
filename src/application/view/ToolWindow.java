package view;

import java.io.File;
import java.util.Optional;

import command.CommandInvoker;
import common.Color;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextInputDialog;
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
	String inputText;
	
	public ToolWindow(DrawingPane drawingPane, Stage primaryStage, int width, int height) {
		this.drawingPane = drawingPane;
		
		Button selectButton = createSelectButton();
		Button createButton = createCreateButton();
		Button undoButton = createUndoButton();
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

	public String getText() {
		return inputText;
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
			} else if (shapeComboBox.getValue().equals("text")) {
				getTextInput();
			}
			// 분기를 안 타려면 전략이나 상태 패턴 사용
			// 아니면 ComboBox 대신 버튼을 나누고 다형성 이용
		});
		return shapeComboBox;
	}

	private void getTextInput() {
		TextInputDialog dialog = new TextInputDialog("Default Value");
		dialog.setTitle("Input Dialog");
		dialog.setHeaderText("Enter your text:");
		dialog.setContentText("Text:");

		Optional<String> result = dialog.showAndWait();

		result.ifPresent(input -> inputText = input);
	}

	private Button createButton(String text, int width, int height, EventHandler<ActionEvent> actionHandler) {
	    Button button = new Button(text);
	    button.setPrefSize(width, height);
	    button.setOnAction(actionHandler);
	    return button;
	}

	private Button createCreateButton() {
	    return createButton("Create", 100, 40, actionEvent -> {
	        drawingPane.setCurrentState(new CreateState(drawingPane));
	        HandlerManager.getInstance().detachHandler();
	    });
	}

	private Button createSelectButton() {
	    return createButton("Select", 100, 40, actionEvent -> {
	        drawingPane.setCurrentState(new SelectState(drawingPane));
	    });
	}

	private Button createRedoButton() {
	    return createButton("Redo", 100, 40, actionEvent -> {
	        CommandInvoker.getInstance().redo();
	    });
	}

	private Button createUndoButton() {
	    return createButton("Undo", 100, 40, actionEvent -> {
	        CommandInvoker.getInstance().undo();
	    });
	}

}
