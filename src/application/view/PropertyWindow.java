package view;

import command.CommandInvoker;
import command.DimensionResizeCommand;
import command.SetColorCommand;
import command.TranslateCommand;
import common.Color;
import common.Observer;
import common.Point;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import model.Element;
import view.fxmodel.FxElementManager;

public class PropertyWindow extends GridPane implements Observer {
	TextField selectedX, selectedY, selectedWidth, selectedHeight;
	ColorPicker selectedColor;
	private double x, y, width, height;
	private Point curPoint = new Point(-5, -5); // initially invalid
	private Color color;
	
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
		// TODO Refactor with higher order functions.
		selectedX = new TextField();
		selectedX.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				String id = FxElementManager.getInstance().getSelectedId();
				double newX = Double.parseDouble(selectedX.getText());
				double dx = newX - x;
				
				CommandInvoker.getInstance().execute(new TranslateCommand(id, dx, 0));
			}
		});
		selectedX.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                // Lost focus
            	selectedX.setText(Double.toString(x));
            }
        });
		
		selectedY = new TextField();
		selectedY.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				String id = FxElementManager.getInstance().getSelectedId();
				double newY = Double.parseDouble(selectedY.getText());
				double dy = newY - y;
				
				CommandInvoker.getInstance().execute(new TranslateCommand(id, 0, dy));

			}
		});
		selectedY.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                // Lost focus
            	selectedY.setText(Double.toString(y));
            }
        });
		
		selectedWidth = new TextField();
		selectedWidth.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				String id = FxElementManager.getInstance().getSelectedId();
				double newWidth = Double.parseDouble(selectedWidth.getText());
				CommandInvoker.getInstance().execute(
						new DimensionResizeCommand(id, newWidth, height));
			}
		});
		selectedWidth.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                // Lost focus
            	selectedWidth.setText(Double.toString(width));
            }
        });

		selectedHeight = new TextField();
		selectedHeight.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				String id = FxElementManager.getInstance().getSelectedId();
				double newHeight = Double.parseDouble(selectedHeight.getText());
				CommandInvoker.getInstance().execute(
						new DimensionResizeCommand(id, width, newHeight));
			}
		});
		selectedHeight.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                // Lost focus
                selectedHeight.setText(Double.toString(height));
            }
        });
		
		selectedColor = new ColorPicker();
		selectedColor.setOnAction(actionEvent -> {
			String id = FxElementManager.getInstance().getSelectedId();
			CommandInvoker.getInstance().execute(
					new SetColorCommand(id, color, new Color(selectedColor.getValue())));
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

	@Override
	public void onSelect(Element selectedElement) {
		updatePrivateValues(selectedElement);
		updateLabels();
	}
	
	@Override
	public void onChange(Element updatedElement) {
		updatePrivateValues(updatedElement);
		updateLabels();
	}
	
	private void updatePrivateValues(Element updatedElement) {
		x = updatedElement.getP().getX();
		y = updatedElement.getP().getY();
		width = updatedElement.getWidth();
		height = updatedElement.getHeight();
		color = updatedElement.getColor();
		curPoint.setX(x);
		curPoint.setY(y);
	}
	
	private void updateLabels() {
		selectedX.setText(Double.toString(x));
		selectedY.setText(Double.toString(y));
		selectedHeight.setText(Double.toString(height));
		selectedWidth.setText(Double.toString(width));
		selectedColor.setValue(color.toFxColor());
	}
}
