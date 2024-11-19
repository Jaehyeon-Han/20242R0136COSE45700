package view;


import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;

public class PropertyWindow extends GridPane {
	TextField selectedX, selectedY, selectedWidth, selectedHeight;
	ColorPicker selectedColor;
	
	public PropertyWindow(int width, int height) {
		Label[] propertyLabelArray = createPropertyLabels();
		Node[] valueDisplayArray = createValueDisplays();
		
		for (int i = 0; i < propertyLabelArray.length; ++i) {
			this.add(propertyLabelArray[i], 0, i);
			this.add(valueDisplayArray[i], 1, i);
		}
		
		this.setStyle("-fx-border-color: #00FF00; -fx-border-width: 1px;");
		this.setPrefSize(width, height);
	}

	private Node[] createValueDisplays() {
		selectedX = new TextField();
		selectedX.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				// TODO 
			}
		});
		
		selectedY = new TextField();
		selectedY.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				// TODO
			}
		});
		
		selectedWidth = new TextField();
		selectedWidth.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				// TODO
			}
		});

		selectedHeight = new TextField();
		selectedHeight.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				// TODO
			}
		});
		
		selectedColor = new ColorPicker();
		selectedColor.setOnAction(actionEvent -> {
			// TODO
		});
		
		Node[] valueArray = 
			{ selectedX, selectedY, selectedWidth, selectedHeight, selectedColor };
		return valueArray;
	}

	private Label[] createPropertyLabels() {
		Label xLabel = new Label("X: ");
		Label yLabel = new Label("Y: ");
		Label widthLabel = new Label("Width: ");
		Label heightLabel = new Label("Height: ");
		Label colorLabel = new Label("Color: ");
		Label[] labelArray = { xLabel, yLabel, widthLabel, heightLabel, colorLabel };
		return labelArray;
	}

}
