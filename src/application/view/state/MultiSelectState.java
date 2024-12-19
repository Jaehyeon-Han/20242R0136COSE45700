package view.state;

import common.Point;
import controller.ElementSelector;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import view.DrawingPane;

public class MultiSelectState implements ToolState {
    private DrawingPane drawingPane;
    private double startX, startY;
    private Rectangle selectionBox;

    public MultiSelectState(DrawingPane drawingPane, double startX, double startY) {
        System.out.println("MultiSelect State");
        this.startX = startX;
        this.startY = startY;
        this.drawingPane = drawingPane;

        initializeSelectionBox();

        drawingPane.add(selectionBox);
    }

    @Override
    public void handleMousePressed(double x, double y) {
        // It always transits from SelectState
    }

    @Override
    public void handleMouseDragged(double x, double y) {
        updateSelectionBox(x, y);
    }

    @Override
    public void handleMouseReleased(double endX, double endY) {
        ElementSelector.getInstance().select(new Point(startX, startY), new Point(endX, endY));
        drawingPane.remove(selectionBox);
        
        drawingPane.setCurrentState(new SelectState(drawingPane));
    }
    
	private void initializeSelectionBox() {
		selectionBox = new Rectangle();
        selectionBox.setFill(Color.LIGHTBLUE.deriveColor(0, 1.0, 1.0, 0.3));
        selectionBox.setStroke(Color.BLUE);
        selectionBox.setStrokeWidth(1);
	}
	
	private void updateSelectionBox(double x, double y) {
		double currentX = x;
        double currentY = y;

        double rectX = Math.min(startX, currentX);
        double rectY = Math.min(startY, currentY);
        double rectWidth = Math.abs(currentX - startX);
        double rectHeight = Math.abs(currentY - startY);

        selectionBox.setX(rectX);
        selectionBox.setY(rectY);
        selectionBox.setWidth(rectWidth);
        selectionBox.setHeight(rectHeight);
	}
}