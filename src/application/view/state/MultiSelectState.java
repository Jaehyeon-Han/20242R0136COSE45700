package view.state;

import command.CommandInvoker;
import command.MultiSelectCommand;
import common.Point;
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

        // Initialize the selection box
        selectionBox = new Rectangle();
        selectionBox.setFill(Color.LIGHTBLUE.deriveColor(0, 1.0, 1.0, 0.3));
        selectionBox.setStroke(Color.BLUE);
        selectionBox.setStrokeWidth(1);

        // Add selection box to the DrawingPane
        drawingPane.add(selectionBox);
    }

    @Override
    public void handleMousePressed(double x, double y) {
        // It always transits from SelectState
    }

    @Override
    public void handleMouseDragged(double x, double y) {
        // Update the selection box dimensions
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

    @Override
    public void handleMouseReleased(double x, double y) {
        // Perform selection logic: Check which elements fall within the selection box
        double x1 = selectionBox.getX();
        double y1 = selectionBox.getY();
        double x2 = x1 + selectionBox.getWidth();
        double y2 = y1 + selectionBox.getHeight();
     
        CommandInvoker.getInstance().execute(
        		new MultiSelectCommand(new Point(x1, y1), new Point(x2, y2)));

        // Detach the selection box
        drawingPane.remove(selectionBox);
        
        drawingPane.setCurrentState(SelectState.getInstance());
    }
}