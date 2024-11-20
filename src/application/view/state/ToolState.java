package view.state;

public interface ToolState {
	default void handleMousePressed(double x, double y) {};
	default void handleMouseDragged(double x, double y) {};
	default void handleMouseReleased(double x, double y) {};
}
