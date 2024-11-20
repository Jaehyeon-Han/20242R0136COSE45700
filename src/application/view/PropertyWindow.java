package view;

import command.TranslateCommand;
import common.Observer;
import common.Point;
import common.PropertyDTO;
import command.Command;
import command.CommandInvoker;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;

public class PropertyWindow extends GridPane implements Observer {
	TextField selectedX, selectedY, selectedWidth, selectedHeight;
	ColorPicker selectedColor;
	
	private double x, y, width, height;
	
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
	
	@Override
	public void onSelect(PropertyDTO dto) {
		updateValues(dto);
	}
	
	@Override
	public void onChange(PropertyDTO dto) {
		updateValues(dto);
	}
	
	private void updateValues(PropertyDTO dto) {
		Point p = dto.getP();
		Point q = dto.getQ();
		
		selectedX.setText(Double.toString(p.getX()));
		selectedY.setText(Double.toString(p.getY()));
		if(dto.getType().equals("line")) {
			selectedWidth.setText(Double.toString(calculateDistance(p, q)));
			selectedHeight.setText("0");
		} else {
			selectedWidth.setText(Double.toString(q.getX() - p.getX()));
			selectedHeight.setText(Double.toString(q.getY() - p.getY()));
		}
		if(dto.getColor() != null) {
			selectedColor.setValue(dto.getColor().toFxColor());
		}
		
		x = Double.parseDouble(selectedX.getText());
		y = Double.parseDouble(selectedY.getText());
		width = Double.parseDouble(selectedWidth.getText());
		height = Double.parseDouble(selectedHeight.getText());
	}
	
	private double calculateDistance(Point p, Point q) {
		return Math.sqrt(Math.pow(q.getX() - p.getX(), 2) + 
				Math.pow(q.getY() - p.getY(), 2));
	}

	private Node[] createValueDisplays() {
		selectedX = new TextField();
		selectedX.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				// 입력값 검증 생략
				double enteredValue = Double.parseDouble(selectedX.getText()); 
				Command translateCommand = new TranslateCommand(enteredValue - x, 0);
				CommandInvoker.getInstance().execute(translateCommand);
			}
		});
		
		selectedY = new TextField();
		selectedY.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				double enteredValue = Double.parseDouble(selectedY.getText()); 
				Command translateCommand = new TranslateCommand(0, enteredValue - y);
				CommandInvoker.getInstance().execute(translateCommand);
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
