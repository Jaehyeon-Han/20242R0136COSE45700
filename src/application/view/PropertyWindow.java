package view;

import java.util.function.Consumer;
import java.util.function.Supplier;

import command.CommandInvoker;
import command.SetDimensionCommand;
import command.SetColorCommand;
import command.TranslateCommand;
import common.Color;

import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import model.Element;
import observer.SelectedStateObserver;
import observer.SelectedElementObserver;
import view.fxmodel.FxElementManager;

public class PropertyWindow extends GridPane implements SelectedStateObserver, SelectedElementObserver {
    private TextField selectedX, selectedY, selectedWidth, selectedHeight;
    private ColorPicker selectedColor;
    private double x, y, width, height;
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
        this.setVisible(false);
    }

    private Node[] createValueDisplays() {
        selectedX = createTextField(() -> x, (newValue) -> {
            double dx = newValue - x;
            executeTranslateCommand(dx, 0);
        });

        selectedY = createTextField(() -> y, (newValue) -> {
            double dy = newValue - y;
            executeTranslateCommand(0, dy);
        });

        selectedWidth = createTextField(() -> width, (newValue) -> {
            executeSetDimensionCommand(newValue, height);
        });

        selectedHeight = createTextField(() -> height, (newValue) -> {
            executeSetDimensionCommand(width, newValue);
        });

        selectedColor = new ColorPicker();
        selectedColor.setOnAction(actionEvent -> {
            String id = FxElementManager.getInstance().getSelectedId();
            CommandInvoker.getInstance().execute(new SetColorCommand(id, color, new Color(selectedColor.getValue())));
        });

        return new Node[]{selectedX, selectedY, selectedWidth, selectedHeight, selectedColor};
    }

    private TextField createTextField(Supplier<Double> getter, Consumer<Double> onEnter) {
        TextField textField = new TextField();
        textField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                double newValue = Double.parseDouble(textField.getText());
                onEnter.accept(newValue);
            }
        });
        textField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                textField.setText(Double.toString(getter.get()));
            }
        });
        return textField;
    }

    private Label[] createPropertyLabels() {
        return new Label[]{
            new Label("X: "),
            new Label("Y: "),
            new Label("Width: "),
            new Label("Height: "),
            new Label("Color: ")
        };
    }

    private void updatePrivateValues(Element updatedElement) {
        x = updatedElement.getP().getX();
        y = updatedElement.getP().getY();
        width = updatedElement.getWidth();
        height = updatedElement.getHeight();
        color = updatedElement.getColor();
    }

    private void updateDisplayValues() {
        selectedX.setText(Double.toString(x));
        selectedY.setText(Double.toString(y));
        selectedHeight.setText(Double.toString(height));
        selectedWidth.setText(Double.toString(width));
        selectedColor.setValue(color.toFxColor());
    }

    private void executeTranslateCommand(double dx, double dy) {
        String id = FxElementManager.getInstance().getSelectedId();
        CommandInvoker.getInstance().execute(new TranslateCommand(id, dx, dy));
    }

    private void executeSetDimensionCommand(double newWidth, double newHeight) {
        String id = FxElementManager.getInstance().getSelectedId();
        CommandInvoker.getInstance().execute(new SetDimensionCommand(id, newWidth, newHeight));
    }
    
	// Observing SelectState
	@Override
	public void onSelect(Element selectedElement) {
		updatePrivateValues(selectedElement);
		updateDisplayValues();
		setVisible(true);
	}
	
	@Override
	public void onUnSelect() {
		setVisible(false);
	}
	
	// SelectedElementObserver
	@Override
	public void onChange(Element updatedElement) {
		updatePrivateValues(updatedElement);
		updateDisplayValues();
	}
}
